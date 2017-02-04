package com.example.r028367.serviceandbinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity /* implements ServiceConnection */ {

    private static final String BIND_SERVICE = "BIND_SERVICE";
    private static final String UNBIND_SERVICE = "UNBIND_SERVICE";
    private static final String INSTANCE_BINDER = "INSTANCE_BINDER";

    private ObserverService observerService;
    //private ServiceConnection serviceConnection;
    private boolean binded;
    private MService.LocalBinder binder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //serviceConnection = this;
        if(savedInstanceState != null) {
            binder = (MService.LocalBinder) BundleCompat.getBinder(savedInstanceState, INSTANCE_BINDER);
            // binder = (MService.LocalBinder) savedInstanceState.getBinder(INSTANCE_BINDER);
        }
        Log.i("TESTE_INSTANCE", String.valueOf(this == MainActivity.this));
    }
/*
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MService.LocalBinder) service;
        observerService = binder.get();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i("ON_SERVICE_DISCONNECTED", name.toString());
        observerService = null;
    }
*/
    public void startBindService(View view) {
        Log.i(BIND_SERVICE, "INICIAR SERVIÇO BIND");
        Class<?> clazz = MService.class;
        Intent intent = new Intent(MainActivity.this, clazz);
        binded = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void stopBindService(View view) {
        Log.i(UNBIND_SERVICE, "PARAR SERVIÇO BIND");
        if(binded) {
            unbindService(serviceConnection);
            binded = false;
        }
    }

    public void getValueObserver(View view) {
        if(observerService != null) {
            Integer value = (Integer) observerService.observer();
            if(value != null) {
                Log.i("GET_VALUE", value.toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(binded) {
            unbindService(serviceConnection);
            binded = false;
        }
        super.onDestroy();
    }

    /*
    * putBinder min API 18
    * */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        BundleCompat.putBinder(outState, INSTANCE_BINDER, binder);
        //outState.putBinder(INSTANCE_BINDER, binder);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            binder = (MService.LocalBinder) BundleCompat.getBinder(savedInstanceState, INSTANCE_BINDER);
            //binder = (MService.LocalBinder) savedInstanceState.getBinder(INSTANCE_BINDER);
        }
    }


    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MService.LocalBinder) service;
            observerService = binder.get();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("OTHER_DISCONNECTED", name.toString());
            observerService = null;
        }
    };
}
