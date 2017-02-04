package com.example.r028367.serviceandbinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;

public class MService extends ExampleService implements ObserverService<Integer> , Runnable {

    public static final String CATEGORY = "SERVICE_CREATE_EXAMPLE";
    private final IBinder iBinder = new LocalBinder();
    //private Integer count;
    private static final int MAX_EXEC = 30;

    public class LocalBinder extends Binder {
        public ObserverService<Integer> get() {
            return MService.this;
        }
    }

    public MService() {}

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public Integer observer() {
        return count;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CATEGORY, "onCreate() Inicio Exemplo Service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ON_DESTROY", "Service onDestroy");
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count = 0;
        String text = String.format("onStartCommand() Thread ID: %d", startId);
        (new Thread(this, text)).start();
        Log.i("ON_START_COMMAND", text);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void run() {
        while(count <= MAX_EXEC) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Log.e("RUN_SLEEP_EXCEPTION", e.getMessage());
            }
            Log.i("RUN", String.format("%d",count));
            count++;
        }
        Log.i("AFTER_RUN", "Fim Exemplo Service");
        stopSelf();
    }
}
