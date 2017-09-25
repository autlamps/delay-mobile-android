package com.example.izaac.delayed.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DelayResponse {

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
        @SerializedName("trips")
        @Expose
        private List<Trip> trips = null;
        @SerializedName("exec_name")
        @Expose
        private String execName;
        @SerializedName("created")
        @Expose
        private Integer created;
        @SerializedName("valid_until")
        @Expose
        private Integer validUntil;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Trip> getTrips() {
            return trips;
        }

        public void setTrips(List<Trip> trips) {
            this.trips = trips;
        }

        public String getExecName() {
            return execName;
        }

        public void setExecName(String execName) {
            this.execName = execName;
        }

        public Integer getCreated() {
            return created;
        }

        public void setCreated(Integer created) {
            this.created = created;
        }

        public Integer getValidUntil() {
            return validUntil;
        }

        public void setValidUntil(Integer validUntil) {
            this.validUntil = validUntil;
        }

        public class NextStop {

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
            @SerializedName("delay")
            @Expose
            private Integer delay;
            @SerializedName("scheduled_arrival")
            @Expose
            private String scheduledArrival;
            @SerializedName("eta")
            @Expose
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

            public Integer getDelay() {
                return delay;
            }

            public void setDelay(Integer delay) {
                this.delay = delay;
            }

            public String getScheduledArrival() {
                return scheduledArrival;
            }

            public void setScheduledArrival(String scheduledArrival) {
                this.scheduledArrival = scheduledArrival;
            }

            public String getEta() {
                return eta;
            }

            public void setEta(String eta) {
                this.eta = eta;
            }

        }

        public class Trip {

            @SerializedName("trip_id")
            @Expose
            private String tripId;
            @SerializedName("route_id")
            @Expose
            private String routeId;
            @SerializedName("route_long_name")
            @Expose
            private String routeLongName;
            @SerializedName("route_short_name")
            @Expose
            private String routeShortName;
            @SerializedName("next_stop")
            @Expose
            private NextStop nextStop;
            @SerializedName("vehicle_id")
            @Expose
            private String vehicleId;
            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("lon")
            @Expose
            private Double lon;

            public String getTripId() {
                return tripId;
            }

            public void setTripId(String tripId) {
                this.tripId = tripId;
            }

            public String getRouteId() {
                return routeId;
            }

            public void setRouteId(String routeId) {
                this.routeId = routeId;
            }

            public String getRouteLongName() {
                return routeLongName;
            }

            public void setRouteLongName(String routeLongName) {
                this.routeLongName = routeLongName;
            }

            public String getRouteShortName() {
                return routeShortName;
            }

            public void setRouteShortName(String routeShortName) {
                this.routeShortName = routeShortName;
            }

            public NextStop getNextStop() {
                return nextStop;
            }

            public void setNextStop(NextStop nextStop) {
                this.nextStop = nextStop;
            }

            public String getVehicleId() {
                return vehicleId;
            }

            public void setVehicleId(String vehicleId) {
                this.vehicleId = vehicleId;
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
    }
}



