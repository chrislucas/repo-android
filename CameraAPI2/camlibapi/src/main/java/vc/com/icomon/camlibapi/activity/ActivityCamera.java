package vc.com.icomon.camlibapi.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vc.com.icomon.camlibapi.R;
import vc.com.icomon.camlibapi.actions.camera.CallbackOnPictureTaken;
import vc.com.icomon.camlibapi.actions.camera.CallbackStartCamera;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionAutoFocusMove;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionCameraApiTakeJPEGPictureCallback;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionCameraApiTakeRawPictureCallback;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionOnZoomChangeListener;
import vc.com.icomon.camlibapi.actions.camera.impl.WrapperOpenCamera;
import vc.com.icomon.camlibapi.view.CameraPreview;

public class ActivityCamera extends AppCompatActivity implements CallbackStartCamera, CallbackOnPictureTaken {

    private WrapperOpenCamera wrapperOpenCamera;
    private CameraPreview cameraPreview;
    public static final String BUNDLE_APP_NAME = "BUNDLE_APP_NAME";
    private String appName = "APP_NAME_DEFAULT";

    @Override
    protected void onStart() {
        super.onStart();
        wrapperOpenCamera = new WrapperOpenCamera(this);
        wrapperOpenCamera.startCameraWithReadAndWritePermission();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null && intent.getExtras() != null) {
                Bundle bundle = intent.getExtras();
                appName = bundle.getString(BUNDLE_APP_NAME);
            }
        }
        else {
            appName = savedInstanceState.getString(BUNDLE_APP_NAME);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            appName = outState.getString(BUNDLE_APP_NAME);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            appName = savedInstanceState.getString(BUNDLE_APP_NAME);
        }
    }

    public void takePicture(View view) {
        if (cameraPreview.getCamera() == null) {
            cameraPreview.tryOpenCamera();
        }
        else {
            ActionCameraApiTakeJPEGPictureCallback takeJPEGPictureCallback =
                    new ActionCameraApiTakeJPEGPictureCallback(this);
            ActionCameraApiTakeRawPictureCallback rawPictureCallaback = new ActionCameraApiTakeRawPictureCallback(this);
            cameraPreview.safeTakePicture(null, rawPictureCallaback, takeJPEGPictureCallback);
        }
    }

    /**
     * {@link CallbackOnPictureTaken}
     * */
    @Override
    public void callback(byte[] dataFromCamera, Camera camera) {
        if (dataFromCamera != null) {

        }
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

    @Override
    public void configureCameraPreviewIfHasPermission() {
        /**
         * Acessar a SurfaceView que mostra a imagem da camera
         * */
        cameraPreview = findViewById(R.id.camera_api_preview_surface_2);
        cameraPreview.tryOpenCamera();
        if (cameraPreview.getCamera() != null) {
            Camera camera = cameraPreview.getCamera();
            camera.setZoomChangeListener(new ActionOnZoomChangeListener());
            camera.setAutoFocusMoveCallback(new ActionAutoFocusMove());
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraPreview != null) {
            cameraPreview.turnOffCamera();
        }
    }
}
