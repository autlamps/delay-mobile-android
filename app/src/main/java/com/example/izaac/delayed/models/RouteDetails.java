package com.example.izaac.delayed.models;

import java.util.ArrayList;

/**
 * Created by izaac on 25/09/2017.
 */

public class RouteDetails {

    private String routeSName;
    private String routeDelayTime;
    private String trip;

    public String getTrip()
    {
        return trip;
    }

    public void setTrip(String trip)
    {
        this.trip = trip;
    }

    public String getRouteSName() {
        return routeSName;
    }

    public void setRouteSName(String routeSName) {
        this.routeSName = routeSName;
    }

    public String getRouteDelayTime() {
        return routeDelayTime;
    }

    public void setRouteDelayTime(String routeDelayTime) {
        this.routeDelayTime = routeDelayTime;
    }



}
