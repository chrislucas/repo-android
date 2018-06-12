package com.project.cameraapi2.action.camera;

import android.hardware.Camera;
import android.util.Log;

public class ActionOnZoomChangeListener implements Camera.OnZoomChangeListener {

    @Override
    public void onZoomChange(int zoomValue, boolean stopped, Camera camera) {
        Log.i("ZOOM_CHANGE", String.valueOf(zoomValue));
    }
}
