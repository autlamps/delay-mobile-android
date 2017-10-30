package com.example.izaac.delayed.models;

/**
 * Created by izaac on 30/10/2017.
 */

public class CreateSubscription {
    private String trip_id;
    private String stoptime_id;
    private String[] days;
    private String[] notification_ids;

    public CreateSubscription(String trip_id, String stoptime_id, String[] days, String[] notification_ids) {
        this.trip_id = trip_id;
        this.stoptime_id = stoptime_id;
        this.days = days;
        this.notification_ids = notification_ids;

    }

}
