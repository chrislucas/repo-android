package br.com.bluetooth.xplore.xplorebluetooth;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private void checkPermissions() {
        String [] permissions = {
            Manifest.permission.BLUETOOTH
            ,Manifest.permission.BLUETOOTH_ADMIN
            ,Manifest.permission.ACCESS_COARSE_LOCATION
        };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
