package com.example.izaac.delayed.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izaac.delayed.R;

public class TripPage extends AppCompatActivity {

    EditText Trip1_long_name;
    EditText Trip1_delay_time;
    EditText Trip2_long_name;
    EditText Trip2_delay_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        Trip1_long_name = (EditText) findViewById(R.id.trip1_long_name);
        Trip1_long_name.setText("Hello", TextView.BufferType.EDITABLE);
    }
}
