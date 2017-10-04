package com.example.izaac.delayed.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.adapter.DelayAdapter;
import com.example.izaac.delayed.models.DelayListData;

import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

public class DelayListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DelayAdapter delayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_list);

        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        delayAdapter = new DelayAdapter(DelayListData.getListData(),this);
        recyclerView.setAdapter(delayAdapter);
    }
}
