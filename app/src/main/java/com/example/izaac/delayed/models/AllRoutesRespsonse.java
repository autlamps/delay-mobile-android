package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by izaac on 30/10/2017.
 */

public class AllRoutesRespsonse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("errors")
    @Expose
    private Errors errors;
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

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
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

    public class Errors {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("msg")
        @Expose
        private String msg;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

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
        @SerializedName("routes")
        @Expose
        private List<Route> routes;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Route> getRoutes() {
            return routes;
        }

        public void setRoutes(List<Route> routes) {
            this.routes = routes;
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

}
