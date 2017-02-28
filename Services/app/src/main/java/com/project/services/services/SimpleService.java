package com.project.services.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.project.services.MainActivity;
import com.project.services.NotificationUtils;

/**
 * Created by r028367 on 28/02/2017.
 */

public class SimpleService extends Service {

    public static final int MAX_EXEC = 10;
    public static final int NOTIFICATION = 10;
    private int counter;
    private boolean isRunning;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ON_CREATE_SERVICE", "ONCE");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ON_START_COMMAND", String.format("%d", startId));
        counter = 1;
        isRunning = true;
        // multithread
        new WorkerThead().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        Log.i("ON_DESTROY", "FINISH");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }



     public class WorkerThead extends Thread {
        @Override
        public void run() {
            try {
                while(isRunning && counter < MAX_EXEC) {
                    Thread.sleep(500);
                    Log.i("WORKER_THHREAR", String.format("COUNTER %d", counter));
                    counter++;
                }
            } catch (InterruptedException iex) {
                Log.e("InterruptedException", iex.getMessage());
            }
            finally {
                stopSelf();
                Context context = SimpleService.this;
                Intent intent = new Intent(context, MainActivity.class);
                NotificationUtils.createSimpleNotification(context, intent, "Simple Service", "Executando um simples Serviço", NOTIFICATION);
            }
        }
    }

}
