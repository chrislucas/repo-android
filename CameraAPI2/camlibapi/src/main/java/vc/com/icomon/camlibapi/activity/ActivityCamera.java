package vc.com.icomon.camlibapi.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vc.com.icomon.camlibapi.R;
import vc.com.icomon.camlibapi.actions.camera.CallbackOnPictureTaken;
import vc.com.icomon.camlibapi.actions.camera.CallbackStartCamera;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionAutoFocusMove;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionCameraApiTakeJPEGPictureCallback;
import vc.com.icomon.camlibapi.actions.camera.impl.ActionOnZoomChangeListener;
import vc.com.icomon.camlibapi.actions.camera.impl.WrapperOpenCamera;

import vc.com.icomon.camlibapi.view.CameraPreview;

public class ActivityCamera extends AppCompatActivity implements CallbackStartCamera, CallbackOnPictureTaken {

    private WrapperOpenCamera wrapperOpenCamera;
    private CameraPreview cameraPreview;


    public static final int REQUEST_CODE = 0xff;
    public static final int RESULT_CODE_OK = Activity.RESULT_OK;
    public static final int RESULT_CODE_PROBLEM = Activity.RESULT_CANCELED;
    public static final String BUNDLE_BYTE_ARRAY = "BUNDLE_BYTE_ARRAY ";

    private byte [] photo;

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
        if (savedInstanceState != null) {
            photo = savedInstanceState.getByteArray(BUNDLE_BYTE_ARRAY);
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
            outState.putByteArray(BUNDLE_BYTE_ARRAY, photo);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            photo = savedInstanceState.getByteArray(BUNDLE_BYTE_ARRAY);
        }
    }

    public void takePicture(View view) {
        if (cameraPreview.getCamera() == null) {
            cameraPreview.tryOpenCamera();
        }
        else {
            ActionCameraApiTakeJPEGPictureCallback takeJPEGPictureCallback =
                    new ActionCameraApiTakeJPEGPictureCallback(this);
            cameraPreview.safeTakePicture(null, null, takeJPEGPictureCallback);
        }
    }

    /**
     * {@link CallbackOnPictureTaken}
     * */
    @Override
    public void callback(@Nullable byte[] dataFromCamera, Camera camera) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putByteArray(BUNDLE_BYTE_ARRAY, dataFromCamera);
        intent.putExtras(bundle);
        setResult(dataFromCamera != null ? RESULT_CODE_OK : RESULT_CODE_PROBLEM, intent);
        finish();
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
