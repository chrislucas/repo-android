package com.project.cameraapi2.activities;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.cameraapi2.R;

import com.project.cameraapi2.action.camera2.ActionCameraState;
import com.project.cameraapi2.action.handler.HandlerCamera;
import com.project.cameraapi2.utils.camera.UtilsCameraAPI2;


public class ActivityCameraApi2 extends AppCompatActivity {

    private CameraDevice.StateCallback stateCallback = new ActionCameraState();
    private Handler handlerCamera = new HandlerCamera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_camera2);
        UtilsCameraAPI2.logCameraCharacteristics(this);
    }

    private void tryOpenCamera() {
        String [] ids = UtilsCameraAPI2.getCameraIdList(this);
        UtilsCameraAPI2.openCamera(this, ids[0], stateCallback, handlerCamera);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case UtilsCameraAPI2.REQUEST_CAMERA_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tryOpenCamera();
                }
                break;
        }

    }
}
