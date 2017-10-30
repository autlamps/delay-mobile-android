package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by izaac on 30/10/2017.
 */

public class TotalSubscriptionsResponse {

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("result")
        @Expose
        private Result result;
        @SerializedName("errors")
        @Expose
        private Object errors;
        @SerializedName("meta")
        @Expose
        private Meta meta;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Object getErrors() {
            return errors;
        }

        public void setErrors(Object errors) {
            this.errors = errors;
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

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("subscriptions")
        @Expose
        private List<Subscription> subscriptions;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Subscription> getSubscriptions() {
            return subscriptions;
        }

        public void setSubscriptions(List<Subscription> subscriptions) {
            this.subscriptions = subscriptions;
        }

    }

    public class StopTime {

        @SerializedName("stop_time_id")
        @Expose
        private String stopTimeId;
        @SerializedName("stop_id")
        @Expose
        private String stopId;
        @SerializedName("stop_name")
        @Expose
        private String stopName;
        @SerializedName("stop_code")
        @Expose
        private String stopCode;
        @SerializedName("arrival_time")
        @Expose
        private String arrivalTime;
        @SerializedName("departure_time")
        @Expose
        private String departureTime;

        public String getStopTimeId() {
            return stopTimeId;
        }

        public void setStopTimeId(String stopTimeId) {
            this.stopTimeId = stopTimeId;
        }

        public String getStopId() {
            return stopId;
        }

        public void setStopId(String stopId) {
            this.stopId = stopId;
        }

        public String getStopName() {
            return stopName;
        }

        public void setStopName(String stopName) {
            this.stopName = stopName;
        }

        public String getStopCode() {
            return stopCode;
        }

        public void setStopCode(String stopCode) {
            this.stopCode = stopCode;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

    }

    public class Subscription {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("route_id")
        @Expose
        private String routeId;
        @SerializedName("trip_id")
        @Expose
        private String tripId;
        @SerializedName("stop_time")
        @Expose
        private StopTime stopTime;
        @SerializedName("days")
        @Expose
        private List<String> days = null;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("archived")
        @Expose
        private Boolean archived;

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

        public String getTripId() {
            return tripId;
        }

        public void setTripId(String tripId) {
            this.tripId = tripId;
        }

        public StopTime getStopTime() {
            return stopTime;
        }

        public void setStopTime(StopTime stopTime) {
            this.stopTime = stopTime;
        }

        public List<String> getDays(int i) {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public Boolean getArchived() {
            return archived;
        }

        public void setArchived(Boolean archived) {
            this.archived = archived;
        }
    }
}


