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
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class DelayListActivity extends AppCompatActivity implements DelayAdapter.ItemClickCallBack{

    private RecyclerView recyclerView;
    private Button searchButton;
    private DelayAdapter delayAdapter;
    private ArrayList listData;
    public static int recyclerViewUserSelection;
    DelayListData delayListData = new DelayListData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_list);

        if(DelayTotal == true) {
            listData = (ArrayList) DelayListData.getTotalDelayData();
        }
        else if(DelayTotal == false) {
            listData = (ArrayList) DelayListData.getListData();
        }

        System.out.println("stop");

        searchButton = (Button) findViewById(R.id.searchButton);
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        delayAdapter = new DelayAdapter(listData,this);
        recyclerView.setAdapter(delayAdapter);
        delayAdapter.setItemClickCallBack(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelayTotal = false;
                Intent intent = new Intent(DelayListActivity.this, homePage.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onItemClick(int p) {

        ListItem item = (ListItem) listData.get(p);

        recyclerViewUserSelection = p;

        Intent intent = new Intent(DelayListActivity.this, TripPage.class);
        startActivity(intent);

    }

  /*  @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/
}
