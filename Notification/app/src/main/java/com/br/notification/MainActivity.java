package com.br.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void createNotification(Context context, CharSequence messageBar
            , CharSequence title, CharSequence message, Class<?> Activity) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // fotma obsoleta
        //Notification notification = new Notification(android.R.drawable.stat_notify_chat, messageBar, System.currentTimeMillis());
        // https://developer.android.com/reference/android/app/Notification.Builder.html
        // a forma compativel
        Notification notification = new NotificationCompat.Builder(context).build();
    }
}
