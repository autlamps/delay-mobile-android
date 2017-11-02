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
import com.example.izaac.delayed.models.CreateSubscription;
import com.example.izaac.delayed.models.Login;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.RouteDetails;
import com.example.izaac.delayed.models.StopInfo;
import com.example.izaac.delayed.models.StopTime;
import com.example.izaac.delayed.models.Subscription;
import com.example.izaac.delayed.models.SubscriptionsResponse;
import com.example.izaac.delayed.models.TokenResponse;
import com.example.izaac.delayed.models.TotalSubscriptionsResponse;
import com.example.izaac.delayed.models.Trip;
import com.example.izaac.delayed.models.TripDetails;

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

import static com.example.izaac.delayed.pages.DelayListActivity.recyclerViewUserSelection;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.DelayButtonPress;
import static com.example.izaac.delayed.pages.homePage.NSDetails;
import static com.example.izaac.delayed.pages.homePage.NotificationID;
import static com.example.izaac.delayed.pages.homePage.selectedTrip;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class TripPage extends AppCompatActivity {

    public static ArrayList<Subscription> SubscriptionDetails = new ArrayList<Subscription>();
    public static ArrayList<StopTime> StopTimeDetails = new ArrayList<StopTime>();
    public static ArrayList<StopInfo> StopInfoDetails = new ArrayList<StopInfo>();
    private EditText TripHeading;
    private EditText Trip_long_name;
    private EditText Trip_delay;
    private EditText Trip_next_stop;
    private EditText Trip_eta;
    private Button BackButton;
    private Button SubscribeButton;
    //TripDetails tripDetails = new TripDetails();
    RouteDetails routeDetails = new RouteDetails();
    Trip trip = new Trip();
    private String trip_text;
    private String trip_delay_text;
    private String trip_ns_text;
    private String trip_eta_text;
    //private int minutes = 60;
    private int tripNumber;
    private float delayInMinutes;
    private float delay;
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
    private String[] notifiication_ids;
    public static boolean SubscriptionData;
    NextStopDetails nextStopDetails = new NextStopDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

       // System.out.println("Hello");

        TripDetails tripDetails = new TripDetails();

        SharedPreferences sharedPreferences = getSharedPreferences("Notification Tokens", Context.MODE_PRIVATE);
        notifiication_ids = new String[]{NotificationID};

        if(DelayButtonPress == true) {
            checkSelectedTripFromTotalTrips();
            tripLocationInArray.add(recyclerViewUserSelection);
            tripDetails.getTripLocationInArray();
            convertDelayToMinutes();
        }
        else {
            checkSelectedTrip();
            tripDetails.getTripLocationInArray();
            convertDelayToMinutes();
        }

        System.out.println("stop");
        trip_text = "Trip ID: " + BaseTripDetails.get(recyclerViewUserSelection).getRoute_short_name();

        /*Heading Text*/

        TripHeading = (EditText) findViewById(R.id.trip_short_name);
        TripHeading.setText(trip_text, TextView.BufferType.EDITABLE);
        TripHeading.setEnabled(false);

        /*Setting the trips long name*/

        Trip_long_name = (EditText) findViewById(R.id.trip_long_name);
        Trip_long_name.setText(BaseTripDetails.get(recyclerViewUserSelection).getRoute_long_name(), TextView.BufferType.EDITABLE);

        BackButton = (Button) findViewById(R.id.BackButton);

        SubscribeButton = (Button) findViewById(R.id.SubscribeButton);

        /*Setting the Delays in Text*/

        if(delayInMinutes < 0) {

            Trip_delay = (EditText) findViewById(R.id.trip_delay);
            delay = Math.abs(delayInMinutes);
            trip_delay_text = "Trip Ahead: " + Float.toString(delay) + " Minutes";
            Trip_delay.setText(trip_delay_text, TextView.BufferType.EDITABLE);
            Trip_delay.setEnabled(false);
            delay = 0;


        }
        else  {

            Trip_delay = (EditText) findViewById(R.id.trip_delay);
            delay = Math.abs(delayInMinutes);
            trip_delay_text = "Trip Delay: " + Float.toString(delay) + " Minutes";
            Trip_delay.setText(trip_delay_text, TextView.BufferType.EDITABLE);
            Trip_delay.setEnabled(false);
            delay = 0;

        }

        /*Setting the Next Stop trip text*/

        Trip_next_stop = (EditText) findViewById(R.id.trip_next_stop);
        trip_ns_text = "Next Stop: " + NSDetails.get(tripNumber).getName();
        Trip_next_stop.setText(trip_ns_text, TextView.BufferType.EDITABLE);
        Trip_next_stop.setEnabled(false);

        /*Setting the Next Stop ETA text*/

        Trip_eta = (EditText) findViewById(R.id.trip_eta);
        trip_eta_text = "Eta: " + NSDetails.get(tripNumber).getEta();
        Trip_eta.setText(trip_eta_text, TextView.BufferType.EDITABLE);
        Trip_eta.setEnabled(false);

        /*Subscribe Button Click Listener*/

        SubscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostToApi();
                setSubscriptions();

            }
        });

        /*Button on click listener*/

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripPage.this, homePage.class);
                startActivity(intent);
            }
        });

    }

    /*converting the delay from seconds to minutes*/

    public float convertDelayToMinutes() {

        delayInMinutes = NSDetails.get(tripNumber).getDelay() / 60;

        return delayInMinutes;
    }

    //returns the tripNumber variable

    public int checkSelectedTrip() {

        tripNumber = tripLocationInArray.get(recyclerViewUserSelection);

        return tripNumber;

    }

    public int checkSelectedTripFromTotalTrips() {

        tripNumber = recyclerViewUserSelection;

        return tripNumber;
    }
    /*Method Posts Selected Trip To API Service*/

    public void PostToApi() {

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

        System.out.println("stop here");

        CreateSubscription createSubscription = new CreateSubscription(BaseTripDetails.get(recyclerViewUserSelection).getTrip_id(),
                NSDetails.get(tripNumber).getStoptime_id(),days, notifiication_ids);

        Call<SubscriptionsResponse> call = delayApi.createSubscription(createSubscription);

        call.enqueue(new Callback<SubscriptionsResponse>() {
            @Override
            public void onResponse(Call<SubscriptionsResponse> call, Response<SubscriptionsResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(TripPage.this, "Trip Subscribed", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(TripPage.this, "Subscription Incorrect", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SubscriptionsResponse> call, Throwable t) {
                Toast.makeText(TripPage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });
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
                    Intent intent = new Intent(TripPage.this, SubscriptionListActivity.class);
                    startActivity(intent);
                    // finish();

                }
                else {
                    Toast.makeText(TripPage.this, "Invalid Subscription", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TotalSubscriptionsResponse> call, Throwable t) {
                Toast.makeText(TripPage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
