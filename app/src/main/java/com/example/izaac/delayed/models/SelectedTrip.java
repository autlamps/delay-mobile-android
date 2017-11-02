package com.example.izaac.delayed.models;

/**
 * Created by izaac on 3/11/2017.
 */

public class SelectedTrip {

    private String id;
    private String route_id;
    private String service_id;
    private String gtfsid;
    private String headsign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getGtfsid() {
        return gtfsid;
    }

    public void setGtfsid(String gtfsid) {
        this.gtfsid = gtfsid;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }
}
