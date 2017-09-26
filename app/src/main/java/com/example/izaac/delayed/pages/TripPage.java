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
        trip_text = "Trip ID:".concat(tripDetails.getTrip());

        TripHeading = (EditText) findViewById(R.id.trip1_long_name);
        TripHeading.setText("Trip ID:", TextView.BufferType.EDITABLE);
        TripHeading.setEnabled(false);

        Trip_long_name = (EditText) findViewById(R.id.trip1_delay_time);
        //delay = NSDetails.get(0).getDelay();
        Trip_long_name.setText(BaseTripDetails.get(tripNumber).getRoute_long_name(), TextView.BufferType.EDITABLE);
        //Trip_long_name.setEnabled(false);
        //delay = 0;

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
