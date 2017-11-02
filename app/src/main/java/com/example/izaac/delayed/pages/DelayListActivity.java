package com.example.izaac.delayed.pages;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.adapter.DelayAdapter;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.AllRoutes;
import com.example.izaac.delayed.models.AllRoutesRespsonse;
import com.example.izaac.delayed.models.DelayListData;
import com.example.izaac.delayed.models.ListItem;
import com.example.izaac.delayed.models.SelectedCalender;
import com.example.izaac.delayed.models.SelectedRoute;
import com.example.izaac.delayed.models.SelectedTrip;
import com.example.izaac.delayed.models.SelectedTripResponse;
import com.example.izaac.delayed.models.TripIDResponse;
import com.example.izaac.delayed.models.TripIdDetails;
import com.example.izaac.delayed.models.TripIdStopInfo;

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
import static com.example.izaac.delayed.pages.homePage.AllRoutesSearch;
import static com.example.izaac.delayed.pages.homePage.AllRoutesSelected;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class DelayListActivity extends AppCompatActivity implements DelayAdapter.ItemClickCallBack{

    private RecyclerView recyclerView;
    private Button searchButton;
    private Button LogoutButton;
    private DelayAdapter delayAdapter;
    private ArrayList listData;
    public static int recyclerViewUserSelection;
    DelayListData delayListData = new DelayListData();
    public static ArrayList<SelectedRoute> SelectedRouteDetails = new ArrayList<SelectedRoute>();
    public static ArrayList<SelectedTrip> SelectedTripDetails = new ArrayList<SelectedTrip>();
    public static ArrayList<SelectedCalender> SelectedCalendarDetails = new ArrayList<SelectedCalender>();
    public static ArrayList<TripIdDetails> TripStopTimeDetails = new ArrayList<TripIdDetails>();
    public static ArrayList<TripIdStopInfo> TripIDStopInfo = new ArrayList<TripIdStopInfo>();
    public static boolean SelectedRouteSearch;
    public static boolean SelectedTripSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_list);

        System.out.println("STOP HERE FOR DEBUG");

        if(AllRoutesSearch == true) {
            listData = (ArrayList) DelayListData.getAllRouteData();
        }
        else if(SelectedRouteSearch == true) {
            listData = (ArrayList) DelayListData.getSelectedRouteDetailsData();
        }
        else if(SelectedTripSearch == true) {
            listData = (ArrayList) DelayListData.getTripIDData();
        }
        else if(DelayTotal == true) {
            listData = (ArrayList) DelayListData.getTotalDelayData();

        }
        else if(DelayTotal == false) {
            listData = (ArrayList) DelayListData.getListData();
        }

        System.out.println("stop");

        searchButton = (Button) findViewById(R.id.searchButton);
        LogoutButton = (Button) findViewById(R.id.LogoutButton);
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        delayAdapter = new DelayAdapter(listData,this);
        recyclerView.setAdapter(delayAdapter);
        delayAdapter.setItemClickCallBack(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelayTotal = false;
                AllRoutesSearch = false;
                SelectedRouteSearch = false;
                SelectedTripSearch = false;
                Intent intent = new Intent(DelayListActivity.this, homePage.class);
                startActivity(intent);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                Intent intent = new Intent(DelayListActivity.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }



    @Override
    public void onItemClick(int p) {

        if(AllRoutesSearch == true)
        {
            ListItem item = (ListItem) listData.get(p);

            recyclerViewUserSelection = p;

            displaySelectedRouteID();
            AllRoutesSearch = false;
            SelectedRouteSearch = true;


        }
        else if(SelectedRouteSearch == true) {
            ListItem item = (ListItem) listData.get(p);

            recyclerViewUserSelection = p;

            displaySelectedTripID();
            SelectedRouteSearch = false;
            SelectedTripSearch = true;

        }
        else if(SelectedTripSearch == true) {
            ListItem item = (ListItem) listData.get(p);

            recyclerViewUserSelection = p;

            Intent intent = new Intent(DelayListActivity.this, SelectedRoutePage.class);
            startActivity(intent);
            SelectedTripSearch = false;
        }
        else {

            ListItem item = (ListItem) listData.get(p);

            recyclerViewUserSelection = p;

            Intent intent = new Intent(DelayListActivity.this, TripPage.class);
            startActivity(intent);
        }

    }

  /*  @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/

    public void displaySelectedTripID() {

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

        //tripLocationInArray;

        Call<TripIDResponse> call = delayApi.tripIdDetails(SelectedTripDetails.get(recyclerViewUserSelection).getId());

        call.enqueue(new Callback<TripIDResponse>() {
            @Override
            public void onResponse(Call<TripIDResponse> call, Response<TripIDResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    // Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                    System.out.println("test");

                    for(int x = 0; x < response.body().getResult().getStopTime().size(); x++) {
                        TripIdDetails tripIdDetails = new TripIdDetails();
                        TripIdStopInfo tripIdStopInfo = new TripIdStopInfo();

                        tripIdDetails.setId(response.body().getResult().getStopTime().get(x).getId());
                        tripIdDetails.setTrip_id(response.body().getResult().getStopTime().get(x).getTripId());
                        tripIdDetails.setStop_sequence(response.body().getResult().getStopTime().get(x).getStopSequence());
                        tripIdDetails.setArrival(response.body().getResult().getStopTime().get(x).getArrival());
                        tripIdDetails.setDeparture(response.body().getResult().getStopTime().get(x).getDeparture());
                        TripStopTimeDetails.add(tripIdDetails);

                        tripIdStopInfo.setId(response.body().getResult().getStopTime().get(x).getStopInfo().getId());
                        tripIdStopInfo.setName(response.body().getResult().getStopTime().get(x).getStopInfo().getName());
                        tripIdStopInfo.setLon(response.body().getResult().getStopTime().get(x).getStopInfo().getLon());
                        tripIdStopInfo.setLat(response.body().getResult().getStopTime().get(x).getStopInfo().getLat());
                        TripIDStopInfo.add(tripIdStopInfo);

                    }

                    System.out.println("S");


                    if(SelectedTripSearch == true) {
                        finish();
                        startActivity(getIntent());
                    }
                }
                else {
                    Toast.makeText(DelayListActivity.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TripIDResponse> call, Throwable t) {
                Toast.makeText(DelayListActivity.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void displaySelectedRouteID() {

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

        //tripLocationInArray;

        Call<SelectedTripResponse> call = delayApi.selectedRoute(AllRoutesSelected.get(tripLocationInArray.get(recyclerViewUserSelection)).getId());

        call.enqueue(new Callback<SelectedTripResponse>() {
            @Override
            public void onResponse(Call<SelectedTripResponse> call, Response<SelectedTripResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    // Toast.makeText(homePage.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                    System.out.println("test");

                    for(int x = 0; x < response.body().getResult().getTrips().size(); x++) {
                        SelectedRoute selectedRoute = new SelectedRoute();
                        SelectedTrip selectedTrip = new SelectedTrip();
                        SelectedCalender selectedCalender = new SelectedCalender();

                        selectedRoute.setId(response.body().getResult().getRoute().getId());
                        selectedRoute.setAgency_id(response.body().getResult().getRoute().getAgencyId());
                        selectedRoute.setGtfsid(response.body().getResult().getRoute().getGtfsId());
                        selectedRoute.setShort_name(response.body().getResult().getRoute().getShortName());
                        selectedRoute.setLong_name(response.body().getResult().getRoute().getLongName());
                        selectedRoute.setRoute_type(response.body().getResult().getRoute().getRouteType());
                        SelectedRouteDetails.add(selectedRoute);

                        selectedTrip.setRoute_id(response.body().getResult().getTrips().get(x).getRouteId());
                        selectedTrip.setId(response.body().getResult().getTrips().get(x).getId());
                        selectedTrip.setHeadsign(response.body().getResult().getTrips().get(x).getHeadsign());
                        SelectedTripDetails.add(selectedTrip);

                        selectedCalender.setStart_time(response.body().getResult().getTrips().get(x).getCalendar().getStartTime());
                        selectedCalender.setEnd_time(response.body().getResult().getTrips().get(x).getCalendar().getEnd());
                        SelectedCalendarDetails.add(selectedCalender);


                    }

                    System.out.println("S");


                    if(SelectedRouteSearch == true) {
                        finish();
                        startActivity(getIntent());
                    }
                }
                else {
                    Toast.makeText(DelayListActivity.this, "Invalid Trip ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SelectedTripResponse> call, Throwable t) {
                Toast.makeText(DelayListActivity.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
