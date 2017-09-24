package com.example.izaac.delayed.interfaces;

import com.example.izaac.delayed.models.Login;
import com.example.izaac.delayed.models.TokenRequest;
import com.example.izaac.delayed.models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by izaac on 24/09/2017.
 */

public interface DelayApi {

    @POST("LoginPage")
    Call<TokenResponse> login(@Body Login login);


}
