package com.project.cameraapi2.utils;

import android.content.Context;
import android.hardware.Camera;

/**
 * Created by C_Luc on 03/02/2018.
 */

public class CameraUtils {


    public static Camera getCamera(Context context) {
        if(Camera2Utils.checkAnyCameraHardware(context)) {
            return Camera.open();
        }
        return null;
    }

}
