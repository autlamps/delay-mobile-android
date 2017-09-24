package com.example.izaac.delayed.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by izaac on 25/09/2017.
 */

public class TokenResponse {

   /* @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("auth_token")
    @Expose
    private String auth_token;

    public String getUser_id() {
        return user_id;
    }

    public String getAuth_token() {
        return auth_token;
    }*/

    private String user_id;
    private String auth_token;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }





}
