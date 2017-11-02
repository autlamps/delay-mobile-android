package com.example.izaac.delayed.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.AllRoutes;
import com.example.izaac.delayed.models.AllRoutesRespsonse;
import com.example.izaac.delayed.models.DelayResponse;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.NotificationDetails;
import com.example.izaac.delayed.models.NotificationResponse;
import com.example.izaac.delayed.models.NotificationToken;
import com.example.izaac.delayed.models.RouteDetails;
import com.example.izaac.delayed.models.StopInfo;
import com.example.izaac.delayed.models.StopTime;
import com.example.izaac.delayed.models.Subscription;
import com.example.izaac.delayed.models.TotalSubscriptionsResponse;
import com.example.izaac.delayed.models.Trip;
import static com.example.izaac.delayed.pages.LoginPage.DelayTotal;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.izaac.delayed.pages.TripPage.StopInfoDetails;
import static com.example.izaac.delayed.pages.TripPage.StopTimeDetails;
import static com.example.izaac.delayed.pages.TripPage.SubscriptionDetails;

public class homePage extends AppCompatActivity {

    private EditText Trip;
    private TextView DelayedButtonText;
    private Button SearchButton;
    private Button DelayButton;
    private Button LogOutButton;
    private Button SubscriptionsButton;
    private Boolean DelaysActive;
    RouteDetails routeDetails = new RouteDetails();
    /*Next Stop Details ArrayList*/
    public static ArrayList<NextStopDetails> NSDetails = new ArrayList<NextStopDetails>();
    /*Base Trip Details Arraylist*/
    public static ArrayList<Trip> BaseTripDetails = new ArrayList<Trip>();
    /*Trip Location In Array Arraylist, used for storing the selected trip objects in a smaller list*/
    public static ArrayList<Integer> tripLocationInArray = new ArrayList<Integer>();
    /*Stores the users selected trip, stored as a string*/
    public static ArrayList<AllRoutes> AllRoutes = new ArrayList<AllRoutes>();
    public static boolean SubscriptionData;
    public static boolean DelayButtonPress;
    public static String selectedTrip;
    /*Total number of services active*/
    private int numberOfServices;
    public static String NotificationID;
    public static boolean AllRoutesSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        System.out.println("jello");
        PostNotificationToken();

        DelayTotal = false;
        AllRoutesSearch = false;
        DelaysActive = false;

        Trip = (EditText) findViewById(R.id.trip1text);
        DelayedButtonText = (TextView) findViewById(R.id.DelayedNameText);
        //DelayedButtonText.setEnabled(false);

        SearchButton = (Button) findViewById(R.id.nextButton);

        DelayButton = (Button) findViewById(R.id.delayListButton);

        LogOutButton = (Button) findViewById(R.id.LogoutButton);

        SubscriptionsButton = (Button) findViewById(R.id.SubscriptionsButton);
        //Toast.makeText(homePage.this, sharedPreferences.getString("AUTH_TOKEN", "N/A"), Toast.LENGTH_SHORT).show();

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DelaysActive = false;
                AllRoutesSearch = true;
               // selectTrip();
                SelectAllTrips();
            }
        });

        DelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelaysActive = true;
                DelayButtonPress = true;
                selectTrip();
            }
        });

        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                Intent intent = new Intent(homePage.this, LoginPage.class);
                startActivity(intent);
                finish();

            }
        });

        SubscriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSubscriptions();
            }
        });
    }


    public void SelectAllTrips() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        tripLocationInArray.clear();

        selectedTrip = Trip.getText().toString().trim();

        Call<AllRoutesRespsonse> call = delayApi.allRoutes();

        call.enqueue(new Callback<AllRoutesRespsonse>() {
            @Override
            public void onResponse(Call<AllRoutesRespsonse> call, Response<AllRoutesRespsonse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    // Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                    System.out.println("test");

                    for(int x = 0; x < response.body().getResult().getRoutes().size(); x++) {
                        AllRoutes allRoutes = new AllRoutes();

                        allRoutes.setId(response.body().getResult().getRoutes().get(x).getId());
                        allRoutes.setGtfs_id(response.body().getResult().getRoutes().get(x).getGtfsId());
                        allRoutes.setAgency_id(response.body().getResult().getRoutes().get(x).getAgencyId());
                        allRoutes.setShort_name(response.body().getResult().getRoutes().get(x).getShortName());
                        allRoutes.setLong_name(response.body().getResult().getRoutes().get(x).getLongName());
                        allRoutes.setRoute_type(response.body().getResult().getRoutes().get(x).getRouteType());
                        AllRoutes.add(allRoutes);


                        if(selectedTrip.equalsIgnoreCase(AllRoutes.get(x).getShort_name()) || selectedTrip.equalsIgnoreCase(AllRoutes.get(x).getLong_name())) {
                            tripLocationInArray.add(x);
                        }

                    }

                    System.out.println("S");

                     if(AllRoutesSearch == true) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                else {
                    Toast.makeText(homePage.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllRoutesRespsonse> call, Throwable t) {
                Toast.makeText(homePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void selectTrip() {

       OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        selectedTrip = Trip.getText().toString().trim();

        Call<DelayResponse> call = delayApi.trip();

        call.enqueue(new Callback<DelayResponse>() {
            @Override
            public void onResponse(Call<DelayResponse> call, Response<DelayResponse> response) {

              System.out.println("Hello");
                if (response.isSuccessful()) {
                   // Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                    System.out.println("test");

                    for (int i = 0; i < response.body().getResult().getTrips().size(); i++) {
                        NextStopDetails nextStopDetails = new NextStopDetails();
                        Trip trip = new Trip();

                        trip.setTrip_id(response.body().getResult().getTrips().get(i).getTripId());
                        trip.setRoute_id(response.body().getResult().getTrips().get(i).getRouteId());
                        trip.setRoute_short_name(response.body().getResult().getTrips().get(i).getRouteShortName());
                        trip.setRoute_long_name(response.body().getResult().getTrips().get(i).getRouteLongName());
                        trip.setVehicle_id(response.body().getResult().getTrips().get(i).getVehicleId());
                        trip.setLatitude(response.body().getResult().getTrips().get(i).getLat());
                        trip.setLongitude(response.body().getResult().getTrips().get(i).getLon());
                        BaseTripDetails.add(trip);

                        nextStopDetails.setName(response.body().getResult().getTrips().get(i).getNextStop().getName());
                        nextStopDetails.setDelay(response.body().getResult().getTrips().get(i).getNextStop().getDelay());
                        nextStopDetails.setEta(response.body().getResult().getTrips().get(i).getNextStop().getEta());
                        nextStopDetails.setId(response.body().getResult().getTrips().get(i).getNextStop().getId());
                        nextStopDetails.setStoptime_id(response.body().getResult().getTrips().get(i).getNextStop().getStoptimeId());
                        nextStopDetails.setLat(response.body().getResult().getTrips().get(i).getNextStop().getLat());
                        nextStopDetails.setLon(response.body().getResult().getTrips().get(i).getNextStop().getLon());
                        nextStopDetails.setScheduled_arrival(response.body().getResult().getTrips().get(i).getNextStop().getScheduledArrival());
                        NSDetails.add(nextStopDetails);

                        if(BaseTripDetails.get(i).getRoute_short_name().equalsIgnoreCase(selectedTrip) || BaseTripDetails.get(i).getRoute_long_name().equalsIgnoreCase(selectedTrip))
                        {
                            tripLocationInArray.add(i);
                        }


                    }

                  //checkNumberOfServices();

                    System.out.println("h");

//                    if(BaseTripDetails.size() == 0) {
//                        Toast.makeText(homePage.this, "No Trips Delayed At This Time", Toast.LENGTH_SHORT).show();
//                    }

                    if(DelaysActive == true) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        //finish();
                        DelayTotal = true;

                    }

                    //problem occuring!!!!!!!
                    else if(tripLocationInArray.size() == 1) {
                        Intent intent = new Intent(homePage.this, TripPage.class);
                        startActivity(intent);
                        finish();

                    }
                    else if(tripLocationInArray.size() > 1) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(homePage.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DelayResponse> call, Throwable t) {
                Toast.makeText(homePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void PostNotificationToken() {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();   


        DelayApi delayApi = retrofit.create(DelayApi.class);

        NotificationToken notificationToken = new NotificationToken("p", "bob phone", FirebaseInstanceId.getInstance().getToken());

        Call<NotificationResponse> call = delayApi.notificationToken(notificationToken);

        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    NotificationDetails notificationDetails = new NotificationDetails();
                    notificationDetails.setId(response.body().getResult().getId());
                    notificationDetails.setUser_id(response.body().getResult().getUserId());
                    notificationDetails.setType(response.body().getResult().getType());
                    notificationDetails.setName(response.body().getResult().getName());
                    notificationDetails.setValue(response.body().getResult().getValue());

                    NotificationID = response.body().getResult().getId();

                    SharedPreferences sharedPreferences = getSharedPreferences("Notification Tokens", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("NOTIFICATION_TOKEN", FirebaseInstanceId.getInstance().getToken());
                    editor.commit();

                }
                else {
                    Toast.makeText(homePage.this, "Notification Token Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(homePage.this, "Notification Token Error......", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public int checkNumberOfServices() {

        if(tripLocationInArray.size() == 1) {
            numberOfServices = 1;
        }
        else if(tripLocationInArray.size() > 1) {
            numberOfServices = tripLocationInArray.size();
        }
        else if(tripLocationInArray.size() == 0)
        {
            numberOfServices = 0;
        }

        return numberOfServices;
    }

    public void setSubscriptions() {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        System.out.println("you shall not pass");

        Call<TotalSubscriptionsResponse> call = delayApi.subscription();

        call.enqueue(new Callback<TotalSubscriptionsResponse>() {
            @Override
            public void onResponse(Call<TotalSubscriptionsResponse> call, Response<TotalSubscriptionsResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {

                    System.out.println("h");

                    //replace at a later date!!!!!!!
                    for(int x = 0; x < response.body().getResult().size(); x++) {

                        System.out.println("stop here");
                        Subscription subscription = new Subscription();
                        StopTime stopTime = new StopTime();
                        StopInfo stopInfo = new StopInfo();

                        subscription.setId(response.body().getResult().get(x).getId());
                        subscription.setTrip_id(response.body().getResult().get(x).getTripId());
                        subscription.setUser_id(response.body().getResult().get(x).getUserId());
                        subscription.setArchived(response.body().getResult().get(x).getArchived());
                        subscription.setMonday(response.body().getResult().get(x).getMonday());
                        subscription.setTuesday(response.body().getResult().get(x).getTuesday());
                        subscription.setWednesday(response.body().getResult().get(x).getWednesday());
                        subscription.setThursday(response.body().getResult().get(x).getThursday());
                        subscription.setFriday(response.body().getResult().get(x).getFriday());
                        subscription.setSaturday(response.body().getResult().get(x).getSaturday());
                        subscription.setSunday(response.body().getResult().get(x).getSunday());
                        subscription.setDatecreated(response.body().getResult().get(x).getCreated());

                        for(int i = 0; i < response.body().getResult().get(x).getNotificationIds().size(); i ++) {

                            subscription.setNotification_ids(response.body().getResult().get(x).getNotificationIds().get(i));
                        }
                        SubscriptionDetails.add(subscription);


                        stopTime.setId(response.body().getResult().get(x).getStopTime().getId());
                        stopTime.setTrip_id(response.body().getResult().get(x).getStopTime().getTripId());
                        stopTime.setStop_sequence(response.body().getResult().get(x).getStopTime().getStopSequence());
                        stopTime.setDeparture(response.body().getResult().get(x).getStopTime().getDeparture());
                        stopTime.setArrival(response.body().getResult().get(x).getStopTime().getArrival());
                        StopTimeDetails.add(stopTime);


                        stopInfo.setId(response.body().getResult().get(x).getStopTime().getStopInfo().getId());
                        stopInfo.setName(response.body().getResult().get(x).getStopTime().getStopInfo().getName());
                        stopInfo.setLat(response.body().getResult().get(x).getStopTime().getStopInfo().getLat());
                        stopInfo.setLon(response.body().getResult().get(x).getStopTime().getStopInfo().getLon());
                        StopInfoDetails.add(stopInfo);

                    }


                    System.out.println("sdsdsds");
                    SubscriptionData = true;
                    Intent intent = new Intent(homePage.this, SubscriptionListActivity.class);
                    startActivity(intent);
                    // finish();

                }
                else {
                    Toast.makeText(homePage.this, "Invalid Subscription", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TotalSubscriptionsResponse> call, Throwable t) {
                Toast.makeText(homePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void searchAllRoutes() {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        selectedTrip = Trip.getText().toString().trim();

        Call<AllRoutesRespsonse> call = delayApi.allRoutes();

        call.enqueue(new Callback<AllRoutesRespsonse>() {
            @Override
            public void onResponse(Call<AllRoutesRespsonse> call, Response<AllRoutesRespsonse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    // Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                    System.out.println("test");

                    for (int i = 0; i < response.body().getResult().getRoutes().size(); i++) {
                        AllRoutes allRoutes = new AllRoutes();

                        allRoutes.setId(response.body().getResult().getRoutes().get(i).getId());
                        allRoutes.setGtfs_id(response.body().getResult().getRoutes().get(i).getGtfsId());
                        allRoutes.setAgency_id(response.body().getResult().getRoutes().get(i).getGtfsId());
                        allRoutes.setShort_name(response.body().getResult().getRoutes().get(i).getShortName());
                        allRoutes.setLong_name(response.body().getResult().getRoutes().get(i).getLongName());
                        allRoutes.setRoute_type(response.body().getResult().getRoutes().get(i).getRouteType());



                        if(BaseTripDetails.get(i).getRoute_short_name().equalsIgnoreCase(selectedTrip) || BaseTripDetails.get(i).getRoute_long_name().equalsIgnoreCase(selectedTrip))
                        {
                            tripLocationInArray.add(i);
                        }


                    }

                    //checkNumberOfServices();

                    System.out.println("h");

//                    if(BaseTripDetails.size() == 0) {
//                        Toast.makeText(homePage.this, "No Trips Delayed At This Time", Toast.LENGTH_SHORT).show();
//                    }

                    if(DelaysActive == true) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        //finish();
                        DelayTotal = true;

                    }

                    //problem occuring!!!!!!!
                    else if(tripLocationInArray.size() == 1) {
                        Intent intent = new Intent(homePage.this, TripPage.class);
                        startActivity(intent);
                        finish();

                    }
                    else if(tripLocationInArray.size() > 1) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(homePage.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllRoutesRespsonse> call, Throwable t) {
                Toast.makeText(homePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
