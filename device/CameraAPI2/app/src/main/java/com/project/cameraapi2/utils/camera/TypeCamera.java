package com.project.cameraapi2.utils.camera;

import android.hardware.Camera;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.project.cameraapi2.utils.camera.TypeCamera.FACING_BACK;
import static com.project.cameraapi2.utils.camera.TypeCamera.FACING_FRONT;


@Retention(RetentionPolicy.SOURCE)
@IntDef({FACING_BACK, FACING_FRONT})
public @interface TypeCamera {
    int FACING_BACK = Camera.CameraInfo.CAMERA_FACING_BACK;
    int FACING_FRONT = Camera.CameraInfo.CAMERA_FACING_FRONT;
}
