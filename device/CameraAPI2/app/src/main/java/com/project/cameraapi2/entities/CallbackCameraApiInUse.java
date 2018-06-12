package com.project.cameraapi2.entities;

import android.hardware.Camera;

public class CallbackCameraApiInUse {
    private Camera.PictureCallback pictureCallback;
    private Camera.ShutterCallback shutterCallback;

    public CallbackCameraApiInUse(Camera.PictureCallback pictureCallback, Camera.ShutterCallback shutterCallback) {
        this.pictureCallback = pictureCallback;
        this.shutterCallback = shutterCallback;
    }

    public Camera.PictureCallback getPictureCallback() {
        return pictureCallback;
    }

    public Camera.ShutterCallback getShutterCallback() {
        return shutterCallback;
    }
}
