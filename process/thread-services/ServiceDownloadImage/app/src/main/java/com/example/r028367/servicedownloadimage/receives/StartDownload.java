package com.example.r028367.servicedownloadimage.receives;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class StartDownload extends BroadcastReceiver {

    public static final String CAT = "START_DOWNLOAD";
    public static final String DOWNLOAD_INTENT = "DOWNLOAD_INTENT";
    private static final int S = 15;

    public StartDownload() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(CAT, "OS iniciando");
        manager(context);
    }

    private void manager(Context context) {
        Intent intent = new Intent(DOWNLOAD_INTENT);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, S);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long time = calendar.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
}
