package com.project.cameraapi2.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.project.cameraapi2.R;
import com.project.cameraapi2.customviews.camera.CameraApiPreview;
import com.project.cameraapi2.utils.camera.UtilsCameraApi;

import java.util.ArrayList;


public class ActivityCameraApi extends AppCompatActivity {

    private CameraApiPreview cameraApiPreview;

    private static final int REQUEST_PERMISSIONS = 0xff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_api);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    private void checkPermissions() {
        String [] permissions = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                list.add(permission);
        }
        if (list.size() > 0) {
            permissions = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                permissions[i] = list.get(i);
            }
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        }
        else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                break;
        }
    }

    private void openCamera() {
        if (UtilsCameraApi.checkIfExistsCameraHardware(this)) {
            cameraApiPreview = findViewById(R.id.camera_api_preview_surface);
        }
        //if (cameraApiPreview != null) { cameraApiPreview.turnOnCamera(); }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //newConfig.getLayoutDirection();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //cameraApiPreview.turnOffCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void takePicture(View view) {
        cameraApiPreview.takeAPicture();
    }

    public void reverseCamera(View view) {
        cameraApiPreview.reverseCamera();
    }
}
