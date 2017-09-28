package com.example.izaac.delayed.pages;

import android.content.Intent;
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
    RouteDetails routeDetails = new RouteDetails();
    public static ArrayList<NextStopDetails> NSDetails = new ArrayList<NextStopDetails>();
    public static ArrayList<Trip> BaseTripDetails = new ArrayList<Trip>();
    public static ArrayList<Integer> tripLocationInArray = new ArrayList<Integer>();
    public static String selectedTrip;
    private int numberOfServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        System.out.println("jello");

        Trip = (EditText) findViewById(R.id.trip1text);
        AmountOfTrips = (EditText) findViewById(R.id.tripAmount);

        AmountOfTrips.setText("AT Delays" ,TextView.BufferType.EDITABLE);

        NextButton = (Button) findViewById(R.id.nextButton);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTrip();
            }
        });


    }

    public void selectTrip() {

       OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", token);

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

        //tripDetails.setTrip(Trip.getText().toString().trim());
        //routeDetails.setTrip(Trip.getText().toString().trim());

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

                        if(BaseTripDetails.get(i).getRoute_short_name().equalsIgnoreCase(selectedTrip))
                        {
                            tripLocationInArray.add(i);
                        }

                    }

                  //  checkNumberOfServices();

                    System.out.println("h");

                    if(tripLocationInArray.size() == 1) {
                        Intent intent = new Intent(homePage.this, TripPage.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(tripLocationInArray.size() > 1) {
                        Intent intent = new Intent(homePage.this, MultiTripPage.class);
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
