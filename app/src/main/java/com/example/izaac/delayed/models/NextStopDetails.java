package com.example.izaac.delayed.models;

/**
 * Created by izaac on 25/09/2017.
 */

public class NextStopDetails {
    private String id;
    private String name;
    private double lat;
    private double lon;
    private double delay;
    private String scheduled_arrival;
    private String eta;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon(Double lon) {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(String scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }



}
