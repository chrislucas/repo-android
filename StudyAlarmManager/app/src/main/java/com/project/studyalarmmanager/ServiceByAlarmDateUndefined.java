package com.project.studyalarmmanager;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class ServiceByAlarmDateUndefined extends Service {
    public ServiceByAlarmDateUndefined() {}

    private boolean isRunning;
    private int counter;
    private static final int MAX = 20;

    public static final String BUNDLE_BOOL = "BUNDLE_BOOL";

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        isRunning   = true;
        counter     = 0;

        if(intent != null) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                boolean value = bundle.getBoolean(BUNDLE_BOOL);
                Log.d("BUNDLE_BOOLEAN", String.valueOf(value));
            }
        }

        Log.i("ON_START_COMMAND", String.valueOf(startId));
        new Thread() {
            @Override
            public void run() {
                while(isRunning && counter < MAX) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        Log.e("_ERROR", e.getMessage());
                    }
                    Log.i("COUNTER", String.valueOf(counter));
                    counter++;
                }
                stopSelf(startId);
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.i("ON_CREATE", "SERVICE_BY_ALARM_UNDEFINED_DATE");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //isRunning = false;
        Log.i("ON_DESTROY", "SERVICE_BY_ALARM_UNDEFINED_DATE");
    }
}
