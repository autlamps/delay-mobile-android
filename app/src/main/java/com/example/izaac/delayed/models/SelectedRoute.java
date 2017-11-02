package com.example.izaac.delayed.models;

/**
 * Created by izaac on 3/11/2017.
 */

public class SelectedRoute {

    private String id;
    private String gtfsid;
    private String agency_id;
    private String short_name;
    private String long_name;
    private String route_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGtfsid() {
        return gtfsid;
    }

    public void setGtfsid(String gtfsid) {
        this.gtfsid = gtfsid;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }
}
