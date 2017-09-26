package com.example.izaac.delayed.models;

/**
 * Created by izaac on 25/09/2017.
 */

public class Trip {

    private String trip_id;
    private String route_id;
    private String route_long_name;
    private String route_short_name;
    private String vehicle_id;
    private double latitude;
    private double longitude;

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }





}
