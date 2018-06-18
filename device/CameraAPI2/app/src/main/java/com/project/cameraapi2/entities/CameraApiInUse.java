package com.project.cameraapi2.entities;

import android.hardware.Camera;

import com.project.cameraapi2.utils.camera.TypeCamera;

public class CameraApiInUse {

    private Camera camera;
    private CallbackCameraApiInUse callbackCameraApiInUse;
    private int type;

    public CameraApiInUse(Camera camera, CallbackCameraApiInUse callbackCameraApiInUse, int type) {
        this(camera, type);
        this.callbackCameraApiInUse = callbackCameraApiInUse;
    }

    public CameraApiInUse(Camera camera) {
        this(camera, TypeCamera.FACING_BACK);
    }

    public CameraApiInUse(Camera camera, @TypeCamera int type) {
        this.camera = camera;
        this.type = type;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int invertCamera() {
        this.type = type == TypeCamera.FACING_BACK
                ? TypeCamera.FACING_FRONT : TypeCamera.FACING_BACK;
        return this.type;
    }

    public CallbackCameraApiInUse getCallbackCameraApiInUse() {
        return callbackCameraApiInUse;
    }
}
