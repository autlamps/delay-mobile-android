package com.example.izaac.delayed.models;

/**
 * Created by izaac on 30/10/2017.
 */

public class StopTime {
    private String stoptime_id;
    private String stop_id;
    private String stop_name;
    private String stop_code;
    private String arrival_time;
    private String departure_time;

    public String getStoptime_id() {
        return stoptime_id;
    }

    public void setStoptime_id(String stoptime_id) {
        this.stoptime_id = stoptime_id;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_code() {
        return stop_code;
    }

    public void setStop_code(String stop_code) {
        this.stop_code = stop_code;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

}
