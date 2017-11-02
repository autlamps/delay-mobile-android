package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by izaac on 30/10/2017.
 */

public class SubscriptionsResponse {

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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("trip_id")
        @Expose
        private String tripId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("archived")
        @Expose
        private Boolean archived;
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
        @SerializedName("notification_ids")
        @Expose
        private List<String> notificationIds = null;
        @SerializedName("stop_time")
        @Expose
        private StopTime stopTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTripId() {
            return tripId;
        }

        public void setTripId(String tripId) {
            this.tripId = tripId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Boolean getArchived() {
            return archived;
        }

        public void setArchived(Boolean archived) {
            this.archived = archived;
        }

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

        public List<String> getNotificationIds() {
            return notificationIds;
        }

        public void setNotificationIds(List<String> notificationIds) {
            this.notificationIds = notificationIds;
        }

        public StopTime getStopTime() {
            return stopTime;
        }

        public void setStopTime(StopTime stopTime) {
            this.stopTime = stopTime;
        }

    }

    public class StopInfo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

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

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }

    public class StopTime {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("trip_id")
        @Expose
        private String tripId;
        @SerializedName("stop_sequence")
        @Expose
        private Integer stopSequence;
        @SerializedName("stop_info")
        @Expose
        private StopInfo stopInfo;
        @SerializedName("departure")
        @Expose
        private String departure;
        @SerializedName("arrival")
        @Expose
        private String arrival;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTripId() {
            return tripId;
        }

        public void setTripId(String tripId) {
            this.tripId = tripId;
        }

        public Integer getStopSequence() {
            return stopSequence;
        }

        public void setStopSequence(Integer stopSequence) {
            this.stopSequence = stopSequence;
        }

        public StopInfo getStopInfo() {
            return stopInfo;
        }

        public void setStopInfo(StopInfo stopInfo) {
            this.stopInfo = stopInfo;
        }

        public String getDeparture() {
            return departure;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
        }

        public String getArrival() {
            return arrival;
        }

        public void setArrival(String arrival) {
            this.arrival = arrival;
        }

    }
}
