package com.example.izaac.delayed.models;

import java.util.ArrayList;
import java.util.List;

import static com.example.izaac.delayed.pages.DelayListActivity.SelectedCalendarDetails;
import static com.example.izaac.delayed.pages.DelayListActivity.TripStopTimeDetails;
import static com.example.izaac.delayed.pages.TripPage.PostResponseSubTMDetails;
import static com.example.izaac.delayed.pages.TripPage.StopInfoDetails;
import static com.example.izaac.delayed.pages.homePage.AllRoutesSelected;
import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

/**
 * Created by izaac on 4/10/2017.
 */

public class DelayListData {

    private static final int[] icons = {android.R.drawable.ic_popup_reminder, android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_delete};


    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();

        int currentIconPos = 0;

        for(int x = 0; x < tripLocationInArray.size(); x++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle(BaseTripDetails.get(tripLocationInArray.get(x)).getRoute_long_name());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getSelectedRouteDetailsData() {
        List<ListItem> data = new ArrayList<>();

        int currentIconPos = 0;

        for(int x = 0; x < SelectedCalendarDetails.size(); x++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle("Start: " + SelectedCalendarDetails.get(x).getStart_time() + " : End: " + SelectedCalendarDetails.get(x).getEnd_time());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getTripIDData() {
        List<ListItem> data = new ArrayList<>();

        int currentIconPos = 0;

        for(int x = 0; x < TripStopTimeDetails.size(); x++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle("Departure: " + TripStopTimeDetails.get(x).getDeparture() + " : Arrival: " + TripStopTimeDetails.get(x).getArrival());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getAllRouteData() {
        List<ListItem> data = new ArrayList<>();

        int currentIconPos = 0;

        for(int x = 0; x < tripLocationInArray.size(); x++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle(AllRoutesSelected.get(tripLocationInArray.get(x)).getShort_name() + " : " + AllRoutesSelected.get(tripLocationInArray.get(x)).getLong_name());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getTotalDelayData() {
        List<ListItem> data = new ArrayList<>();

        int currentIconPos = 0;

        for(int y = 0; y < BaseTripDetails.size(); y++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle(BaseTripDetails.get(y).getRoute_short_name() + " : " + BaseTripDetails.get(y).getRoute_long_name());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getSubscriptionData() {
        List<ListItem> data = new ArrayList<>();

       // String hello = SubscriptionIsTrue.get(0).getId();
        //String two = StopTimeDetails.get(0).getId();

        int currentIconPos = 0;

        for(int y = 0; y < StopInfoDetails.size(); y++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle(StopInfoDetails.get(y).getName());
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getDiffSubscriptionData() {
        List<ListItem> data = new ArrayList<>();

        // String hello = SubscriptionIsTrue.get(0).getId();
        //String two = StopTimeDetails.get(0).getId();

        int currentIconPos = 0;

        for(int y = 0; y < PostResponseSubTMDetails.size(); y++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[currentIconPos]);

            currentIconPos++;

            if (currentIconPos > 2) {
                currentIconPos = 0;
            }

            item.setTitle(PostResponseSubTMDetails.get(y).getStop_name());
            data.add(item);
        }

        return data;
    }
}
