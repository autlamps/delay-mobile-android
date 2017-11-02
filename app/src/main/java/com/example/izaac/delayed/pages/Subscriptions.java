package com.example.izaac.delayed.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.DelayResponse;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.StopTime;
import com.example.izaac.delayed.models.Subscription;
import com.example.izaac.delayed.models.TotalSubscriptionsResponse;
import com.example.izaac.delayed.models.Trip;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.izaac.delayed.pages.LoginPage.DelayTotal;
import static com.example.izaac.delayed.pages.TripPage.StopInfoDetails;
import static com.example.izaac.delayed.pages.TripPage.StopTimeDetails;
import static com.example.izaac.delayed.pages.TripPage.SubscriptionDetails;
import static com.example.izaac.delayed.pages.homePage.NSDetails;

public class Subscriptions extends AppCompatActivity {

    private Button SearchButton;
    private Button DelaysButton;
    private EditText Trip1Text;
    private EditText Trip2Text;
    private EditText Trip3Text;
    private String Trip1;
    private String Trip2;
    private String Trip3;
    //public static ArrayList<Subscription> SubscriptionIsTrue = new ArrayList<Subscription>();
  //  public static ArrayList<StopTime> StopTimeDetails = new ArrayList<StopTime>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);

        System.out.println("hello");


        SearchButton = (Button) findViewById(R.id.SearchTripsButton);
        DelaysButton = (Button) findViewById(R.id.DelayMenuButton);
        Trip1Text = (EditText) findViewById(R.id.Trip1Text);
        Trip2Text = (EditText) findViewById(R.id.Trip2Text);
        Trip3Text  =(EditText) findViewById(R.id.Trip3Text);

       /// setSubscriptions();

        Trip1 = StopInfoDetails.get(0).getName() + " : " + StopTimeDetails.get(0).getArrival();
        Trip1Text.setText(Trip1, TextView.BufferType.EDITABLE);
        Trip1Text.setEnabled(false);

        Trip2 = StopInfoDetails.get(1).getName() + " : " + StopTimeDetails.get(1).getArrival();
        Trip2Text.setText(Trip2, TextView.BufferType.EDITABLE);
        Trip2Text.setEnabled(false);

        Trip3 = StopInfoDetails.get(2).getName() + " : " + StopTimeDetails.get(2).getArrival();
        Trip3Text.setText(Trip3, TextView.BufferType.EDITABLE);
        Trip3Text.setEnabled(false);
       // Trip2Text.setVisibility(View.INVISIBLE);
        //Trip3Text.setVisibility(View.INVISIBLE);

         SearchButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Subscriptions.this, homePage.class);
                 startActivity(intent);
             }
         });
        DelaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Subscriptions.this, homePage.class);
                startActivity(intent);
            }
        });

    }


}
