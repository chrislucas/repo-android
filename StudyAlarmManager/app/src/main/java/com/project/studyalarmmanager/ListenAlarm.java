package com.project.studyalarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.Calendar;

public class ListenAlarm extends BroadcastReceiver {

    public static final String TAG      = "ALARM";
    public static final String ACTION   = "REMEMBER";
    public static final int ID_NOTIFICATION_RC = 0x44ff99;

    public ListenAlarm() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, String.format("%s", Calendar.getInstance().getTime().toString()));

        if(intent != null) {
            String message  = intent.getStringExtra("MESSAGE");
            String title    = intent.getStringExtra("TITLE");
            if(message == null && title == null) {
                title   = "Chave e valor indefinidos";
                message = "Definir chave e valor da intent";
            }
            Intent notify = new Intent(context, ActivityTest.class);
            NotificationUtils.create(context, notify, title, message, ID_NOTIFICATION_RC);
        }
    }
}
