package com.example.izaac.delayed.models;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class TokenResponse {

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


    public class Result {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("auth_token")
        @Expose
        private String authToken;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
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
}