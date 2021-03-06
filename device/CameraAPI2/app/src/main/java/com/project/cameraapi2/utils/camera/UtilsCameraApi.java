package com.project.cameraapi2.utils.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import android.util.Log;

import com.project.cameraapi2.utils.exception.UtilsException;


/**
 * Created by C_Luc on 03/02/2018.
 */

public class UtilsCameraApi {


    public static boolean checkCameraHardwareBack(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static boolean checkCameraHardwareFront(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    }

    public static boolean checkIfExistsCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static Camera getCamera(Context context) {
        try {
            if(checkIfExistsCameraHardware(context)) {
                return Camera.open();
            }
        }
        catch (Exception e) {
            Log.e("EXCP_CAMERA", UtilsException.getMessage(e));
        }
        return null;
    }

    public static Camera getCamera(Context context, @TypeCamera int type) {
        try {
            if(checkIfExistsCameraHardware(context)) {
                int numberOfCameras = Camera.getNumberOfCameras();
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                for (int i = 0; i < numberOfCameras; i++) {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == type) {
                        return Camera.open(i);
                    }
                }
            }
        }
        catch (Exception e) {
            Log.e("EXCP_CAMERA", UtilsException.getMessage(e));
        }
        return null;
    }




}
