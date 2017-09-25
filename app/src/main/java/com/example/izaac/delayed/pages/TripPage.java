package com.example.izaac.delayed.pages;

import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.models.NextStopDetails;

import static com.example.izaac.delayed.pages.homePage.NSDetails;

public class TripPage extends AppCompatActivity {

    EditText Trip1_long_name;
    EditText Trip1_delay_time;
    EditText Trip2_long_name;
    EditText Trip2_delay_time;
    NextStopDetails nextStopDetails = new NextStopDetails();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        Trip1_long_name = (EditText) findViewById(R.id.trip1_long_name);
        Trip1_long_name.setText(NSDetails.get(0).getName(), TextView.BufferType.EDITABLE);

        Trip1_delay_time = (EditText) findViewById(R.id.trip1_delay_time);
        Trip1_delay_time.setText(NSDetails.get(0).getDelay(), TextView.BufferType.EDITABLE);

        Trip2_long_name = (EditText) findViewById(R.id.trip2_long_name);
        Trip2_long_name.setText(NSDetails.get(1).getName(), TextView.BufferType.EDITABLE);

        Trip2_delay_time = (EditText) findViewById(R.id.trip2_delay_time);
        Trip2_delay_time.setText(NSDetails.get(1).getDelay(),TextView.BufferType.EDITABLE);

    }
}
