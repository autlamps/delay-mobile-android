package com.example.izaac.delayed.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.RouteDetails;
import com.example.izaac.delayed.models.Trip;
import com.example.izaac.delayed.models.TripDetails;

import static com.example.izaac.delayed.pages.DelayListActivity.recyclerViewUserSelection;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.NSDetails;
import static com.example.izaac.delayed.pages.homePage.selectedTrip;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class TripPage extends AppCompatActivity {

    EditText TripHeading;
    EditText Trip_long_name;
    EditText Trip_delay;
    EditText Trip_next_stop;
    EditText Trip_eta;
    Button BackButton;
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

    NextStopDetails nextStopDetails = new NextStopDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        TripDetails tripDetails = new TripDetails();

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

}
