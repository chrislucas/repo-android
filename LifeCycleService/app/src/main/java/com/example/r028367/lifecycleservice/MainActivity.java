package com.example.r028367.lifecycleservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ServiceConnection {


    private Intent intent;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, ServiceWorkerThread.class);
    }

    public void initService(View view) {
        startService(intent);
    }
    /*
    * O metodo stopService vai encerrar o serviço, se
    * tiver N-Threads vinculadas a ele, todas serao encerradas.
    * Quando esse metodo for executado, o metodo onDestroy da instancia
    * da classe Service sera executado
    *
    * */
    public void stopService(View view) {
        stopService(intent);
    }
}
