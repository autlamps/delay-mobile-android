package com.example.izaac.delayed.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.adapter.DelayAdapter;
import com.example.izaac.delayed.models.DelayListData;
import com.example.izaac.delayed.models.ListItem;

import java.util.ArrayList;

import static com.example.izaac.delayed.pages.LoginPage.DelayTotal;
import static com.example.izaac.delayed.pages.TripPage.SubscriptionData;

public class SubscriptionListActivity extends AppCompatActivity implements DelayAdapter.ItemClickCallBack{


    private RecyclerView recyclerView;
    private Button searchButton;
    private Button delayButton;
    private DelayAdapter delayAdapter;
    private ArrayList listData;
    public static int recyclerViewUserSelection;
    DelayListData delayListData = new DelayListData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_list2);

        System.out.println("STOP HERE FOR DEBUG");

        if(SubscriptionData == true) {
            listData = (ArrayList) DelayListData.getSubscriptionData();
        }

        System.out.println("stop");

        searchButton = (Button) findViewById(R.id.searchButton);
        delayButton = (Button) findViewById(R.id.Delays);
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        delayAdapter = new DelayAdapter(listData,this);
        recyclerView.setAdapter(delayAdapter);
        delayAdapter.setItemClickCallBack(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelayTotal = false;
                Intent intent = new Intent(SubscriptionListActivity.this, homePage.class);
                startActivity(intent);
            }
        });

        delayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelayTotal = false;
                Intent intent = new Intent(SubscriptionListActivity.this, DelayListActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onItemClick(int p) {

        ListItem item = (ListItem) listData.get(p);

        recyclerViewUserSelection = p;

        Intent intent = new Intent(SubscriptionListActivity.this, TripPage.class);
        startActivity(intent);

    }

  /*  @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/
}
