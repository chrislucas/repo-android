package vc.com.icomon.camlibapi.utils.camera;

import android.hardware.Camera;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@IntDef({TypeCamera.FACING_BACK, TypeCamera.FACING_FRONT})
public @interface TypeCamera {
    int FACING_BACK = Camera.CameraInfo.CAMERA_FACING_BACK;
    int FACING_FRONT = Camera.CameraInfo.CAMERA_FACING_FRONT;
}
