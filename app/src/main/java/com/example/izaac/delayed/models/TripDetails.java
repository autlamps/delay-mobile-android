package com.example.izaac.delayed.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by izaac on 26/09/2017.
 */

public class TripDetails {

    private String trip;
    private ArrayList<Integer> tripLocationInArray = new ArrayList<Integer>();


    public ArrayList<Integer> getTripLocationInArray() {
        return tripLocationInArray;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTrip() {
        return trip;
    }

}
