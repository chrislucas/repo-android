package com.project.studyalarmmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadServiceByAlarmDateUndefined extends Service {

    private List<WorkerThread> threads;

    private static final int MAX_EXEC = 20;

    public class WorkerThread extends Thread {
        private boolean status;
        private int startId, count;

        public WorkerThread(int startId) {
            super("SERVICE" + startId);
            this.startId = startId;
            this.status = true;
            this.count = 0;
        }

        @Override
        public void run() {
            while(status && count < MAX_EXEC) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("EXCEPTION_THREAD", e.getMessage());
                }
                Log.i("THREAD_RUN", String.format("THREAD ID: %d Counter: %d", startId, count));
                count++;
            }
            Log.i("THREAD_STOP_SELF", String.format("THREAD ID: %d", startId));
            stopSelf(startId);
        }
    }

    public MultiThreadServiceByAlarmDateUndefined() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        threads = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        WorkerThread w = new WorkerThread(startId);
        threads.add(w);
        w.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        for(WorkerThread w : threads)
            w.status = false;
        Log.i("ON_DESTROY", "SERVICE_BY_ALARM_UNDEFINED_DATE");
    }
}
