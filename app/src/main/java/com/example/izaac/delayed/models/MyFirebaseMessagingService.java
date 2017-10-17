package com.example.izaac.delayed.models;

//import com.google.firebase.messaging.MyFirebaseMessagingService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.izaac.delayed.pages.LoginPage;
import com.example.izaac.delayed.pages.homePage;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by izaac on 17/10/2017.
 */

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this, homePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("Delayed App Service Notification");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        //notificationBuilder.setSmallIcon(R.)
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }

}
