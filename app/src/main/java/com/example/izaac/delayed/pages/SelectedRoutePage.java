package com.example.izaac.delayed.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.CreateSubscription;
import com.example.izaac.delayed.models.SubscriptionData;
import com.example.izaac.delayed.models.SubscriptionStopTimeDetails;
import com.example.izaac.delayed.models.SubscriptionsResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.izaac.delayed.pages.DelayListActivity.recyclerViewUserSelection;
import static com.example.izaac.delayed.pages.TripPage.PostResponseSubDetails;
import static com.example.izaac.delayed.pages.TripPage.PostResponseSubTMDetails;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.NSDetails;
import static com.example.izaac.delayed.pages.homePage.NotificationID;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class SelectedRoutePage extends AppCompatActivity {


    public static boolean SubscriptionIsTrue;
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
    private String[] notifiication_ids;
    private Button HomeButton;
    private Button SubscribeButton;
    private int tripNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_route_page);

        SharedPreferences sharedPreferences = getSharedPreferences("Notification Tokens", Context.MODE_PRIVATE);
        notifiication_ids = new String[]{NotificationID};

        HomeButton = (Button) findViewById(R.id.HomeButton);
        SubscribeButton = (Button) findViewById(R.id.SubscribeButton);


        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedRoutePage.this, homePage.class);
                startActivity(intent);
                finish();
            }
        });






    }

    public int checkSelectedTrip() {

        tripNumber = tripLocationInArray.get(recyclerViewUserSelection);

        return tripNumber;

    }

    public int checkSelectedTripFromTotalTrips() {

        tripNumber = recyclerViewUserSelection;

        return tripNumber;
    }

    public void PostToApi() {

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

        System.out.println("stop here");

        CreateSubscription createSubscription = new CreateSubscription(BaseTripDetails.get(recyclerViewUserSelection).getTrip_id(),
                NSDetails.get(tripNumber).getStoptime_id(),days, notifiication_ids);

        Call<SubscriptionsResponse> call = delayApi.createSubscription(createSubscription);

        call.enqueue(new Callback<SubscriptionsResponse>() {
            @Override
            public void onResponse(Call<SubscriptionsResponse> call, Response<SubscriptionsResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(SelectedRoutePage.this, "Trip Subscribed", Toast.LENGTH_SHORT).show();

                    SubscriptionData subscriptionData = new SubscriptionData();
                    SubscriptionStopTimeDetails subscriptionStopTimeDetails = new SubscriptionStopTimeDetails();

                    subscriptionData.setId(response.body().getResult().getId());
                    subscriptionData.setUser_id(response.body().getResult().getUserId());
                    subscriptionData.setTrip_id(response.body().getResult().getTripId());
                    PostResponseSubDetails.add(subscriptionData);

                    subscriptionStopTimeDetails.setStop_name(response.body().getResult().getStopTime().getStopInfo().getName());
                    PostResponseSubTMDetails.add(subscriptionStopTimeDetails);

                    SubscriptionIsTrue = false;
                    Intent intent = new Intent(SelectedRoutePage.this, SubscriptionListActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SelectedRoutePage.this, "Subscription Incorrect", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SubscriptionsResponse> call, Throwable t) {
                Toast.makeText(SelectedRoutePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
