package com.example.izaac.delayed.models;

import java.util.ArrayList;
import java.util.List;

import static com.example.izaac.delayed.pages.homePage.BaseTripDetails;
import static com.example.izaac.delayed.pages.homePage.tripLocationInArray;

/**
 * Created by izaac on 4/10/2017.
 */

public class DelayListData {

    private static final int[] icons = {android.R.drawable.ic_popup_reminder, android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_delete};

    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();


        for(int x = 0; x < tripLocationInArray.size(); x++) {
            ListItem item = new ListItem();

            item.setImageResId(icons[x]);
            item.setTitle(BaseTripDetails.get(tripLocationInArray.get(x)).getRoute_long_name());
            data.add(item);
        }

        return data;
    }
}
