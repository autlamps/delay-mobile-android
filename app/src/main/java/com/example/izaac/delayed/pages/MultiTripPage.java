package com.example.izaac.delayed.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izaac.delayed.R;

import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class MultiTripPage extends AppCompatActivity {

    EditText Trip1_text;
    EditText Trip2_text;
    EditText Trip3_text;
    EditText Trip4_text;
    String Trip1_Details;
    String Trip2_Details;
    String Trip3_Details;
    String Trip4_Details;
    private int tripSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_trip_page);

        tripSize = tripLocationInArray.size();

        System.out.println("text");

        while(tripSize > 0)
        {
            if(tripSize == 1) {

                Trip1_Details = BaseTripDetails.get(tripLocationInArray.get(0)).getRoute_short_name() + "\n" + BaseTripDetails.get(tripLocationInArray.get(0)).getRoute_long_name();

                Trip1_text = (EditText) findViewById(R.id.trip1_text);
                Trip1_text.setText(Trip1_Details, TextView.BufferType.EDITABLE);
                Trip1_text.setEnabled(false);

                tripSize  = tripSize - 1;
            }
            else if(tripSize == 2) {

                Trip2_Details = BaseTripDetails.get(tripLocationInArray.get(1)).getRoute_short_name() + "\n" + BaseTripDetails.get(tripLocationInArray.get(1)).getRoute_long_name();

                Trip2_text = (EditText) findViewById(R.id.trip2_text);
                Trip2_text.setText(Trip2_Details, TextView.BufferType.EDITABLE);
                Trip2_text.setEnabled(false);

                if(tripSize == 3) {
                    Trip4_text.setVisibility(View.GONE);
                }
                else if(tripSize == 4) {
                    Trip3_text.setVisibility(View.GONE);
                    Trip4_text.setVisibility(View.GONE);
                }


                tripSize  = tripSize - 1;
            }
            else if(tripSize == 3) {

                Trip3_Details =  BaseTripDetails.get(tripLocationInArray.get(2)).getRoute_short_name() + "\n" + BaseTripDetails.get(tripLocationInArray.get(2)).getRoute_long_name();

                Trip3_text = (EditText) findViewById(R.id.trip3_text);
                Trip3_text.setText(Trip3_Details, TextView.BufferType.EDITABLE);
                Trip3_text.setEnabled(false);
                //Trip4_text.setVisibility(View.GONE);

                if(tripSize == 4) {
                    Trip4_text.setVisibility(View.GONE);
                }

                tripSize  = tripSize - 1;

            }
            else if(tripSize == 4) {

                Trip4_Details =  BaseTripDetails.get(tripLocationInArray.get(3)).getRoute_short_name() + "\n" + BaseTripDetails.get(tripLocationInArray.get(3)).getRoute_long_name();

                Trip4_text = (EditText) findViewById(R.id.trip4_text);
                Trip4_text.setText(Trip4_Details, TextView.BufferType.EDITABLE);
                Trip4_text.setEnabled(false);

                tripSize  = tripSize - 1;

            }
        }


    }
}
