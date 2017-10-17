package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by izaac on 17/10/2017.
 */

public class NotificationResponse {

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
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
