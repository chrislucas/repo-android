package com.project.cameraapi2.action.camera2;

import android.content.Context;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.WindowManager;


public class OnChangeCameraOrientation extends OrientationEventListener {


    private Context context;
    private Camera camera;

    private void init(Context context, Camera camera) {
        this.context = context;
        this.camera = camera;
    }

    public OnChangeCameraOrientation(@NonNull Context context, @NonNull Camera camera) {
        super(context);
        init(context, camera);
    }

    public OnChangeCameraOrientation(@NonNull Context context, @NonNull Camera camera, int rate) {
        super(context, rate);
        init(context, camera);

    }

    @Override
    public void onOrientationChanged(int orientation) {
        Log.i("ON_ORIENTATION_CHANGE"
                , String.format("Orientation: %d", orientation));
        WindowManager windowManager=  (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //if (windowManager != null) { }

        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(0, info);
        orientation = (orientation + 45) / 90 * 90;
        int rotation = 0;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            rotation = 180; // (info.orientation - orientation + 360) % 360;
        }
        else {  // back-facing camera
            rotation = 90; //(info.orientation + orientation) % 360;
        }
        Log.i("ON_CHANGE_CAMERA_O", String.format("%s.\nDegree %d", info.facing, rotation));
        //Camera.Parameters parameters = camera.getParameters();
        //parameters.setRotation(rotation);
        camera.setDisplayOrientation(rotation);
    }
}
