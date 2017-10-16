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
import com.example.izaac.delayed.models.DelayResponse;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.RouteDetails;
import com.example.izaac.delayed.models.Trip;
import static com.example.izaac.delayed.pages.LoginPage.DelayTotal;
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

import static com.example.izaac.delayed.pages.LoginPage.token;

public class homePage extends AppCompatActivity {

    private EditText Trip;
    private EditText AmountOfTrips;
    private Button NextButton;
    private Button DelayButton;
    private Button LogOutButton;
    private Boolean DelaysActive;
    RouteDetails routeDetails = new RouteDetails();
    /*Next Stop Details ArrayList*/
    public static ArrayList<NextStopDetails> NSDetails = new ArrayList<NextStopDetails>();
    /*Base Trip Details Arraylist*/
    public static ArrayList<Trip> BaseTripDetails = new ArrayList<Trip>();
    /*Trip Location In Array Arraylist, used for storing the selected trip objects in a smaller list*/
    public static ArrayList<Integer> tripLocationInArray = new ArrayList<Integer>();
    /*Stores the users selected trip, stored as a string*/
    public static String selectedTrip;
    /*Total number of services active*/
    private int numberOfServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        System.out.println("jello");

        DelayTotal = false;

        Trip = (EditText) findViewById(R.id.trip1text);
        AmountOfTrips = (EditText) findViewById(R.id.tripAmount);

        AmountOfTrips.setText("AT Delays" ,TextView.BufferType.EDITABLE);

        NextButton = (Button) findViewById(R.id.nextButton);

        DelayButton = (Button) findViewById(R.id.delayListButton);

        LogOutButton = (Button) findViewById(R.id.LogoutButton);
        //Toast.makeText(homePage.this, sharedPreferences.getString("AUTH_TOKEN", "N/A"), Toast.LENGTH_SHORT).show();

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelaysActive = false;
                selectTrip();
            }
        });

        DelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelaysActive = true;
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
                    Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
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
                        nextStopDetails.setLat(response.body().getResult().getTrips().get(i).getNextStop().getLat());
                        nextStopDetails.setLon(response.body().getResult().getTrips().get(i).getNextStop().getLon());
                        nextStopDetails.setScheduled_arrival(response.body().getResult().getTrips().get(i).getNextStop().getScheduledArrival());
                        NSDetails.add(nextStopDetails);

                        if(BaseTripDetails.get(i).getRoute_short_name().equalsIgnoreCase(selectedTrip) || BaseTripDetails.get(i).getRoute_long_name().equalsIgnoreCase(selectedTrip))
                        {
                            tripLocationInArray.add(i);
                        }


                    }

                  //  checkNumberOfServices();

                    System.out.println("h");

                    if(DelaysActive == true) {
                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
                        startActivity(intent);
                        //finish();
                        DelayTotal = true;

                    }
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

}
