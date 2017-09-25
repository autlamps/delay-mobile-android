package com.example.izaac.delayed.pages;

/**
 * Created by izaac on 25/09/2017.
 */

import android.support.annotation.FloatRange;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.izaac.delayed.R;

public class Tab1Trips extends Fragment {

    private EditText Trip1_long_name;
    private EditText Trip1_delay_time;
    private EditText Trip2_long_name;
    private EditText Trip2_delay_time;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1trips, container, false);

        //Trip1_long_name = (EditText) findViewByID(R.id.trip1_long_name);

        return rootView;
    }


}