package com.example.izaac.delayed.interfaces;

import com.example.izaac.delayed.models.CreateUser;
import com.example.izaac.delayed.models.DelayResponse;
import com.example.izaac.delayed.models.Login;
import com.example.izaac.delayed.models.NotificationResponse;
import com.example.izaac.delayed.models.NotificationToken;
import com.example.izaac.delayed.models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by izaac on 24/09/2017.
 */

public interface DelayApi {

    @POST("tokens")
    Call<TokenResponse> login(@Body Login login);

    @GET("delays")
    Call<DelayResponse> trip();

    @POST("users")
    Call<TokenResponse> createUser(@Body CreateUser createUser);

    @POST("notifications")
    Call<NotificationResponse> notificationToken(@Body NotificationToken notificationToken);

}
