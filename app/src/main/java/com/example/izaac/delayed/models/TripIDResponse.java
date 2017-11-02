package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by izaac on 3/11/2017.
 */

public class TripIDResponse {

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

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("stop_time")
        @Expose
        private List<StopTime> stopTime;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<StopTime> getStopTime() {
            return stopTime;
        }

        public void setStopTime(List<StopTime> stopTime) {
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
