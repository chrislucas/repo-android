package com.project.cameraapi2.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;


/**
 * Created by r028367 on 31/01/2018.
 */

public class Camera2Utils {


    public static final int REQUEST_CAMERA_PERMISSION = 0xf3;


    public static boolean checkCameraHardwareBack(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static boolean checkCameraHardwareFront(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    }

    public static boolean checkAnyCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Nullable
    private static CameraManager getCameraManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        }
        return null;
    }

    public static String[] getCameraIdList(Context context) {
        String ids[] = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CameraManager cameraManager = getCameraManager(context);
            try {
                if (cameraManager != null) {
                    ids = cameraManager.getCameraIdList();
                }
            } catch (CameraAccessException e) {
                Log.e("GET_ID_LIST_CAMERA"
                        , String.format("Reason: %s.\n Message: %s"
                                , e.getReason(), e.getMessage()));
            }
        }
        return ids;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void openCamera(Activity activity, @NonNull String id
            , @NonNull CameraDevice.StateCallback stateCallback, @NonNull Handler handler) {
        CameraManager cameraManager = getCameraManager(activity.getApplicationContext());
        if (cameraManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
                else {
                    try {
                        cameraManager.openCamera(id, stateCallback, handler);
                    }
                    catch (CameraAccessException e) {
                       Log.e("OPEN_CAMERA_EXCP", e.getMessage());
                    }
                }
            }
        }
    }

    public static void logCameraCharacteristics(Activity activity) {
        CameraManager cameraManager = getCameraManager(activity.getApplicationContext());
        if(cameraManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                try {
                    String ids [] = cameraManager.getCameraIdList();
                    for(String id : ids) {
                        CameraCharacteristics c =  cameraManager.getCameraCharacteristics(id);
                        List<CameraCharacteristics.Key<?>> list = c.getKeys();
                        for(CameraCharacteristics.Key k : list) {
                            Log.i("C_CHAR_KEY", k.getName());
                        }
                    }
                }
                catch (CameraAccessException e) {
                    Log.e("CAMERA_ACCESS_EXCP", e.getLocalizedMessage());
                }
            }
        }
    }
}
