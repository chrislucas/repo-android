package com.project.cameraapi2.action.camera;

import android.hardware.Camera;
import android.util.Log;


/**
 * Callback quando a imagem é capturada
 * */
public class CameraApiShutterCallback implements Camera.ShutterCallback {
    @Override
    public void onShutter() {
        Log.i("ON_SHUTTER", "ON_SHUTTER");
    }
}
