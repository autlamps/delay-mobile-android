package com.example.izaac.delayed.models;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by izaac on 17/10/2017.
 */

public class MyFirebaseInstance extends FirebaseInstanceIdService {

    private static final String TOKEN = "TOKEN";

    public void onTokenRefresh() {

        String recent_token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TOKEN, recent_token);


    }
}
