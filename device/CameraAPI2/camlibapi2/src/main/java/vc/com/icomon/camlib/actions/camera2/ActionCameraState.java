package vc.com.icomon.camlib.actions.camera2;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActionCameraState extends CameraDevice.StateCallback {

    @Override
    public void onOpened(@NonNull CameraDevice camera) {

    }

    @Override
    public void onDisconnected(@NonNull CameraDevice camera) {

    }

    @Override
    public void onError(@NonNull CameraDevice camera, int error) {

    }
}
