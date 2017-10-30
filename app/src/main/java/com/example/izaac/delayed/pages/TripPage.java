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
import com.example.izaac.delayed.models.SubscriptionsResponse;
import com.example.izaac.delayed.models.TokenResponse;
import com.example.izaac.delayed.models.Trip;
import com.example.izaac.delayed.models.TripDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.izaac.delayed.pages.DelayListActivity.recyclerViewUserSelection;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.NSDetails;
import static com.example.izaac.delayed.pages.homePage.selectedTrip;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class TripPage extends AppCompatActivity {

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
    String[] notifiication_ids;


    NextStopDetails nextStopDetails = new NextStopDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        System.out.println("Hello");

        TripDetails tripDetails = new TripDetails();

        SharedPreferences sharedPreferences = getSharedPreferences("Notification Tokens", Context.MODE_PRIVATE);
        notifiication_ids = new String[]{sharedPreferences.getString("NOTIFICATION_TOKEN", "N/A")};

        checkSelectedTrip();
        tripDetails.getTripLocationInArray();
        convertDelayToMinutes();

        System.out.println("stop");
        trip_text = "Trip ID: " + selectedTrip;

        /*Heading Text*/

        TripHeading = (EditText) findViewById(R.id.trip_short_name);
        TripHeading.setText(trip_text, TextView.BufferType.EDITABLE);
        TripHeading.setEnabled(false);

        /*Setting the trips long name*/

        Trip_long_name = (EditText) findViewById(R.id.trip_long_name);
        Trip_long_name.setText(BaseTripDetails.get(tripLocationInArray.get(recyclerViewUserSelection)).getRoute_long_name(), TextView.BufferType.EDITABLE);

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
                Intent intent = new Intent(TripPage.this, Subscriptions.class);
                startActivity(intent);

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

    /*Method Posts Selected Trip To API Service*/

    public void PostToApi() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        CreateSubscription createSubscription = new CreateSubscription(BaseTripDetails.get(tripLocationInArray.get(recyclerViewUserSelection)).getTrip_id(),
                NSDetails.get(tripNumber).getStoptime_id(),days, notifiication_ids);

        Call<SubscriptionsResponse> call = delayApi.createSubscription(createSubscription);

        call.enqueue(new Callback<SubscriptionsResponse>() {
            @Override
            public void onResponse(Call<SubscriptionsResponse> call, Response<SubscriptionsResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(TripPage.this, "Trip Subscribed", Toast.LENGTH_SHORT).show();

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

}
