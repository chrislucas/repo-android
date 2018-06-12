package com.project.cameraapi2.action.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.project.cameraapi2.R;
import com.project.cameraapi2.utils.exception.UtilsException;
import com.project.cameraapi2.utils.file.UtilsFile;


import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * Callback com um array de bytes com a imagem em formato JPEG
 * */

public class ActionCameraApiTakeJPEGPictureCallback implements Camera.PictureCallback {

    private Camera camera;
    private CameraControl cameraControl;
    private Context context;

    public ActionCameraApiTakeJPEGPictureCallback(@NonNull Camera camera, @NonNull CameraControl cameraControl, Context context) {
        this.camera = camera;
        this.cameraControl = cameraControl;
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.i("ON_PICTURE_TAKEN", String.format("SIZE_OF %d MB", data.length / (1024 * 1024)));
        Uri uri = UtilsFile.getExternalOutputMediaFileUri(UtilsFile.IMAGE, UtilsFile.DIR_PICTURES, "image", context);
        if (uri != null) {
            try {
                String path = uri.getPath();
                Log.i("ON_PICTURE_TAKEN", String.format("Caminho: %s", path));
                File file = new File(path);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data);
                fileOutputStream.close();
                cameraControl.restartCamera();
            }
            catch (Exception e) {
                Log.e("EXCP_ON_PICTURE_TAKEN", UtilsException.getMessage(e));
            }
        }
    }
}
