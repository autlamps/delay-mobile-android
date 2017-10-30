package com.example.izaac.delayed.models;

/**
 * Created by izaac on 30/10/2017.
 */

public class CreateSubscription {
    private String trip_id;
    private String stop_time_id;
    private String[] days;
    private String[] notification_ids;

    public CreateSubscription(String trip_id, String stop_time_id, String[] days, String[] notification_ids) {
        this.trip_id = trip_id;
        this.stop_time_id = stop_time_id;
        this.days = days;
        this.notification_ids = notification_ids;

    }

}
