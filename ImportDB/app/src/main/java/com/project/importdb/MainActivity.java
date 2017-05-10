package com.project.importdb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.nio.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
    }

    private void requestPermissions() {
        List<String> listPermissionsDenied = new ArrayList<>();
        String permissions [] = {
             Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for(String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                listPermissionsDenied.add(permission);
            }
            boolean shouldIhaveRequestPermission = ActivityCompat
                    .shouldShowRequestPermissionRationale(this, permission);

        }

        if(listPermissionsDenied.size() == 0) {
            readAllFiles();
        }
        else {
            String buffer [] = new String[listPermissionsDenied.size()];
            listPermissionsDenied.toArray(buffer);
            ActivityCompat.requestPermissions(this, buffer, 0x03);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0x03) {
            if(permissions != null && grantResults != null) {
                if( grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
                    readAllFiles();
                }
                for(int i=0; i<grantResults.length; i++) {}
            }
        }
    }

    private void readAllFiles() {
        File sd     = Environment.getExternalStorageDirectory();
        File data   = Environment.getDataDirectory();
        Log.i("ENVIROMENT", String.format("SD %s\n DATA %s\n", sd.getPath(), data.getPath()));
        Log.i("ENVIROMENT", String.format("SD %s\n DATA %s\n", sd.getAbsoluteFile(), data.getAbsoluteFile()));

        navigation(data);
    }

    private void navigation(File node) {
        if(node != null && node.isDirectory()) {
            String leafs [] = node.list();
            if(leafs != null) {
                for(String leaf : leafs) {
                    Log.i("FILE", leaf);
                    navigation(new File(node, leaf));
                }
            }
        }
    }

    /**
     * https://docs.oracle.com/javase/tutorial/essential/io/walk.html
     * */
}
