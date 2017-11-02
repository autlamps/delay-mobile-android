package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by izaac on 3/11/2017.
 */

public class SelectedTripResponse {

    public class Calendar {

        @SerializedName("monday")
        @Expose
        private Boolean monday;
        @SerializedName("tuesday")
        @Expose
        private Boolean tuesday;
        @SerializedName("wednesday")
        @Expose
        private Boolean wednesday;
        @SerializedName("thursday")
        @Expose
        private Boolean thursday;
        @SerializedName("friday")
        @Expose
        private Boolean friday;
        @SerializedName("saturday")
        @Expose
        private Boolean saturday;
        @SerializedName("sunday")
        @Expose
        private Boolean sunday;
        @SerializedName("start_time")
        @Expose
        private String startTime;
        @SerializedName("end")
        @Expose
        private String end;

        public Boolean getMonday() {
            return monday;
        }

        public void setMonday(Boolean monday) {
            this.monday = monday;
        }

        public Boolean getTuesday() {
            return tuesday;
        }

        public void setTuesday(Boolean tuesday) {
            this.tuesday = tuesday;
        }

        public Boolean getWednesday() {
            return wednesday;
        }

        public void setWednesday(Boolean wednesday) {
            this.wednesday = wednesday;
        }

        public Boolean getThursday() {
            return thursday;
        }

        public void setThursday(Boolean thursday) {
            this.thursday = thursday;
        }

        public Boolean getFriday() {
            return friday;
        }

        public void setFriday(Boolean friday) {
            this.friday = friday;
        }

        public Boolean getSaturday() {
            return saturday;
        }

        public void setSaturday(Boolean saturday) {
            this.saturday = saturday;
        }

        public Boolean getSunday() {
            return sunday;
        }

        public void setSunday(Boolean sunday) {
            this.sunday = sunday;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

    }

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("errors")
        @Expose
        private Object errors;
        @SerializedName("result")
        @Expose
        private Result result;
        @SerializedName("meta")
        @Expose
        private Meta meta;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Object getErrors() {
            return errors;
        }

        public void setErrors(Object errors) {
            this.errors = errors;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

    public class Meta {

        @SerializedName("version")
        @Expose
        private String version;
        @SerializedName("by")
        @Expose
        private String by;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }

    }

    public class Result {

        @SerializedName("route")
        @Expose
        private Route route;
        @SerializedName("trips")
        @Expose
        private List<Trip> trips;

        public Route getRoute() {
            return route;
        }

        public void setRoute(Route route) {
            this.route = route;
        }

        public List<Trip> getTrips() {
            return trips;
        }

        public void setTrips(List<Trip> trips) {
            this.trips = trips;
        }

    }

    public class Route {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("gtfs_id")
        @Expose
        private String gtfsId;
        @SerializedName("agency_id")
        @Expose
        private String agencyId;
        @SerializedName("short_name")
        @Expose
        private String shortName;
        @SerializedName("long_name")
        @Expose
        private String longName;
        @SerializedName("route_type")
        @Expose
        private String routeType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGtfsId() {
            return gtfsId;
        }

        public void setGtfsId(String gtfsId) {
            this.gtfsId = gtfsId;
        }

        public String getAgencyId() {
            return agencyId;
        }

        public void setAgencyId(String agencyId) {
            this.agencyId = agencyId;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public String getRouteType() {
            return routeType;
        }

        public void setRouteType(String routeType) {
            this.routeType = routeType;
        }

    }

    public class Trip {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("route_id")
        @Expose
        private String routeId;
        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("gtfsid")
        @Expose
        private String gtfsid;
        @SerializedName("headsign")
        @Expose
        private String headsign;
        @SerializedName("calendar")
        @Expose
        private Calendar calendar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRouteId() {
            return routeId;
        }

        public void setRouteId(String routeId) {
            this.routeId = routeId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
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

        public Calendar getCalendar() {
            return calendar;
        }

        public void setCalendar(Calendar calendar) {
            this.calendar = calendar;
        }

    }
}
