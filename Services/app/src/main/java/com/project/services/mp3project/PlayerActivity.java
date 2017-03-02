package com.project.services.mp3project;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.services.R;
import com.project.services.mp3project.Services.MultimidiaService;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    public static final int REQUEST_LIST_PERMISSION = 0x0f;

    private MultimidiaInterface multimidiaInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MultimidiaService.Mp3ServiceBinder c = (MultimidiaService.Mp3ServiceBinder) service;
            multimidiaInterface = c.getInterfaceMp3();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            multimidiaInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        requestPermissions();
        Intent intent = new Intent(this, MultimidiaService.class);
        startService(intent);

        boolean isServiceBinded = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void requestPermissions() {
        List<String> permissionsDenied = new ArrayList<>();
        String permissions [] = {
             Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for (String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                permissionsDenied.add(permission);
            }
        }

        String arrayPermissions [] = new String[permissionsDenied.size()];
        permissionsDenied.toArray(arrayPermissions);
        if(arrayPermissions.length > 0)
            ActivityCompat.requestPermissions(this, arrayPermissions, REQUEST_LIST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LIST_PERMISSION:
                break;
        }
        if(permissions != null && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            for (String permission : permissions) {
                Log.i("PERMISSION_ACTIVITY_2", permission);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        stopService(new Intent(this, MultimidiaService.class));
    }
}
