package com.project.cameraapi2.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.cameraapi2.R;
import com.project.cameraapi2.customviews.camera.CameraApiPreview;

import vc.com.icomon.camlibapi.actions.camera.CallbackStartCamera;
import vc.com.icomon.camlibapi.actions.camera.impl.WrapperOpenCamera;

public class ActivityCameraApiTestLib extends AppCompatActivity implements CallbackStartCamera {

    private WrapperOpenCamera wrapperOpenCamera;
    private CameraApiPreview cameraApiPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_api_test_lib);
        wrapperOpenCamera = new WrapperOpenCamera(this);
        wrapperOpenCamera.startCameraWithReadAndWritePermission();
    }


    @Override
    public void configureCameraPreviewIfHasPermission() {
        /**
         * Acessar a SurfaceView que mostra a imagem da camera
         * */
        cameraApiPreview = findViewById(R.id.camera_api_preview_surface_test);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WrapperOpenCamera.REQUEST_ALL:
            case WrapperOpenCamera.REQUEST_CAMERA_PERMISSIONS:
                configureCameraPreviewIfHasPermission();
                break;
        }
    }

    public void takePicture(View view) {
        cameraApiPreview.takeAPicture();
    }
}
