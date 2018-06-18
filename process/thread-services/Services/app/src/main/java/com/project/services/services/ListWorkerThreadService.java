package com.project.services.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListWorkerThreadService extends Service {

    public static final int MAX_EXEC = 10;

    public ListWorkerThreadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


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

    List<WorkerThread> threads;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("WORKER_THERAD", "CREATE");
        threads = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("WORKER_THERAD", "ON_START");
        ListWorkerThreadService.WorkerThread workerThread = new ListWorkerThreadService().new WorkerThread(startId);
        workerThread.start();
        threads.add(workerThread);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(WorkerThread workerThread : threads) {
            workerThread.status = false;
        }
        threads.clear();
        Log.i("WORKER_THERAD", "FINISH");
    }
}
