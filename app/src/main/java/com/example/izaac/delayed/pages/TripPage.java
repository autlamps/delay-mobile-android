package com.example.izaac.delayed.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.Trip;
import com.example.izaac.delayed.models.TripDetails;

import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.NSDetails;

public class TripPage extends AppCompatActivity {

    EditText TripHeading;
    EditText Trip_long_name;
    EditText Trip_delay;
    EditText Trip_next_stop;
    EditText Trip_eta;
    TripDetails tripDetails = new TripDetails();
    Trip trip = new Trip();
    private String trip_text;
    private int tripNumber;
    int delay;

    NextStopDetails nextStopDetails = new NextStopDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        check();

        System.out.println("stop");
        trip_text = "Trip ID:".concat(BaseTripDetails.get(tripNumber).getRoute_short_name());

        TripHeading = (EditText) findViewById(R.id.trip_short_name);
        TripHeading.setText(trip_text, TextView.BufferType.EDITABLE);
        TripHeading.setEnabled(false);

        Trip_long_name = (EditText) findViewById(R.id.trip_long_name);
        //delay = NSDetails.get(0).getDelay();
        Trip_long_name.setText(BaseTripDetails.get(tripNumber).getRoute_long_name(), TextView.BufferType.EDITABLE);
        //Trip_long_name.setEnabled(false);
        //delay = 0;

        Trip_delay = (EditText) findViewById(R.id.trip_delay);
        delay = NSDetails.get(tripNumber).getDelay();
        Trip_delay.setText(Integer.toString(delay), TextView.BufferType.EDITABLE);
        Trip_delay.setEnabled(false);
        delay = 0;

        Trip_next_stop = (EditText) findViewById(R.id.trip_next_stop);
        Trip_next_stop.setText(NSDetails.get(tripNumber).getName());
        Trip_next_stop.setEnabled(false);

        Trip_eta = (EditText) findViewById(R.id.trip_eta);
        Trip_eta.setText(NSDetails.get(tripNumber).getEta());
        Trip_eta.setEnabled(false);

    }


    public int check() {
        for(int x = 0; x < BaseTripDetails.size(); x++) {
            if(BaseTripDetails.get(x).getRoute_short_name().equalsIgnoreCase(tripDetails.getTrip()))
            {
                tripNumber = x;
                break;
            }

        }

        return tripNumber;

    }


}
