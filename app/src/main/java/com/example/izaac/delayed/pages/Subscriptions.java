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
    public static ArrayList<Subscription> SubscriptionDetails;
    public static ArrayList<StopTime> StopTimeDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);

        SearchButton = (Button) findViewById(R.id.SearchTripsButton);
        DelaysButton = (Button) findViewById(R.id.DelayMenuButton);
        Trip1Text = (EditText) findViewById(R.id.Trip1Text);
        Trip2Text = (EditText) findViewById(R.id.Trip2Text);
        Trip3Text  =(EditText) findViewById(R.id.Trip3Text);

        setSubscriptions();

        Trip1 = StopTimeDetails.get(0).getStop_code() + ": " + StopTimeDetails.get(0).getArrival_time();
        Trip1Text.setText(Trip1, TextView.BufferType.EDITABLE);
        Trip1Text.setEnabled(false);

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

    public void setSubscriptions() {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                Request.Builder newRequest = request.newBuilder().addHeader("X-DELAY-AUTH", sharedPreferences.getString("AUTH_TOKEN", "N/A"));

                return chain.proceed(newRequest.build());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

       // selectedTrip = Trip.getText().toString().trim();

        Call<TotalSubscriptionsResponse> call = delayApi.subscription();

        call.enqueue(new Callback<TotalSubscriptionsResponse>() {
            @Override
            public void onResponse(Call<TotalSubscriptionsResponse> call, Response<TotalSubscriptionsResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {

                    System.out.println("h");

//                    if(BaseTripDetails.size() == 0) {
//                        Toast.makeText(Subscriptionse.this, "No Trips Delayed At This Time", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if(DelaysActive == true) {
//                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
//                        startActivity(intent);
//                        //finish();
//                        DelayTotal = true;
//
//                    }
//                    else if(tripLocationInArray.size() == 1) {
//                        Intent intent = new Intent(homePage.this, TripPage.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else if(tripLocationInArray.size() > 1) {
//                        Intent intent = new Intent(homePage.this, DelayListActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }

//                    Trip_eta = (EditText) findViewById(R.id.trip_eta);
//                    trip_eta_text = "Eta: " + NSDetails.get(tripNumber).getEta();
//                    Trip_eta.setText(trip_eta_text, TextView.BufferType.EDITABLE);
//                    Trip_eta.setEnabled(false);


                    for(int x = 0; x < response.body().getResult().getSubscriptions().size(); x++) {

                        Subscription subscription = new Subscription();
                        StopTime stopTime = new StopTime();

                        subscription.setId(response.body().getResult().getSubscriptions().get(x).getId());
                        subscription.setRoute_id(response.body().getResult().getSubscriptions().get(x).getRouteId());
                        subscription.setTrip_id(response.body().getResult().getSubscriptions().get(x).getTripId());

                        for(int i = 0; i < response.body().getResult().getSubscriptions().get(x).getDays(x).size(); i++) {
                            subscription.setDays(response.body().getResult().getSubscriptions().get(x).getDays(i));
                        }

                        subscription.setDatecreated(response.body().getResult().getSubscriptions().get(x).getDateCreated());
                        subscription.setArchived(response.body().getResult().getSubscriptions().get(x).getArchived());
                        SubscriptionDetails.add(subscription);

                        stopTime.setStop_id(response.body().getResult().getSubscriptions().get(x).getStopTime().getStopId());
                        stopTime.setStoptime_id(response.body().getResult().getSubscriptions().get(x).getStopTime().getStopTimeId());
                        stopTime.setStop_name(response.body().getResult().getSubscriptions().get(x).getStopTime().getStopName());
                        stopTime.setStop_code(response.body().getResult().getSubscriptions().get(x).getStopTime().getStopCode());
                        stopTime.setArrival_time(response.body().getResult().getSubscriptions().get(x).getStopTime().getArrivalTime());
                        stopTime.setDeparture_time(response.body().getResult().getSubscriptions().get(x).getStopTime().getDepartureTime());
                        StopTimeDetails.add(stopTime);


                    }


                }
                else {
                    Toast.makeText(Subscriptions.this, "Invalid Subscription", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TotalSubscriptionsResponse> call, Throwable t) {
                Toast.makeText(Subscriptions.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
