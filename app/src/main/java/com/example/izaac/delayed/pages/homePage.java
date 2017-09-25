package com.example.izaac.delayed.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.AuthTokens;
import com.example.izaac.delayed.models.DelayResponse;
import com.example.izaac.delayed.models.NextStopDetails;
import com.example.izaac.delayed.models.RouteDetails;
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

public class homePage extends AppCompatActivity {

    private EditText Trip1;
    private EditText Trip2;
    private Button NextButton;
    private String SelectedTrip1;
    private String SelectedTrip2;
    RouteDetails routeDetails = new RouteDetails();
    NextStopDetails nextStopDetails = new NextStopDetails();
    public static ArrayList<NextStopDetails> NSDetails = new ArrayList<NextStopDetails>();
    AuthTokens authTokens = new AuthTokens();


   /// @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        System.out.println("jello");

        Trip1 = (EditText) findViewById(R.id.trip1text);
        Trip2 = (EditText) findViewById(R.id.trip2text);

        NextButton = (Button) findViewById(R.id.nextButton);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTrip();
            }
        });




    }

    //RouteDetails routeDetails = new RouteDetails();

    public void selectTrip() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Delay-Android-0.1").addHeader("X-DELAY-AUTH", authTokens.getToken()).build();
                return chain.proceed(newRequest);
            }
        };

// Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        SelectedTrip1 = Trip1.getText().toString().trim();
        SelectedTrip2 = Trip2.getText().toString().trim();

        Trip trip = new Trip("X-DELAY-AUTH", authTokens.getToken());
        Call<DelayResponse> call = delayApi.trip(trip);

        call.enqueue(new Callback<DelayResponse>() {
            @Override
            public void onResponse(Call<DelayResponse> call, Response<DelayResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();

                    //routeDetails.setRouteSName(response.body().getResult().getTrips()[]);
                    //routeDetails.setNSDetails(response.body().getResult().getTrips());
                    //RouteDetails routeDetails = new RouteDetails(SelectedTrip1, SelectedTrip2);
                    for (int i =0; i < response.body().getResult().getTrips().size(); i++) {
                        nextStopDetails.setName(response.body().getResult().getTrips().get(i).getNextStop().getName());
                        nextStopDetails.setDelay(response.body().getResult().getTrips().get(i).getNextStop().getDelay());
                        nextStopDetails.setEta(response.body().getResult().getTrips().get(i).getNextStop().getEta());
                        nextStopDetails.setId(response.body().getResult().getTrips().get(i).getNextStop().getId());
                        nextStopDetails.setLat(response.body().getResult().getTrips().get(i).getNextStop().getLat());
                        nextStopDetails.getLon(response.body().getResult().getTrips().get(i).getNextStop().getLon());
                        NSDetails.add(nextStopDetails);

                    }

                    Intent intent = new Intent(homePage.this, TripPage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(homePage.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DelayResponse> call, Throwable t) {
                Toast.makeText(homePage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
