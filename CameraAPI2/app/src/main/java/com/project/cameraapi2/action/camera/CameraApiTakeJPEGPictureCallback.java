package com.project.cameraapi2.action.camera;

import android.hardware.Camera;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.project.cameraapi2.utils.exception.UtilsException;
import com.project.cameraapi2.utils.file.UtilsFile;


import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * Callback com um array de bytes com a imagem em formato JPEG
 * */

public class CameraApiTakeJPEGPictureCallback implements Camera.PictureCallback {

    private Camera camera;

    public CameraApiTakeJPEGPictureCallback(@NonNull Camera camera) {
        this.camera = camera;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        camera.getParameters();
        Log.i("ON_PICTURE_TAKEN", String.format("SIZE_OF %d MB", data.length / (1024 * 1024)));
        Uri uri = UtilsFile.getExternalOutputMediaFileUri(UtilsFile.IMAGE, UtilsFile.DIR_PICTURES, "image");
        if (uri != null) {
            try {
                String path = uri.getPath();
                Log.i("ON_PICTURE_TAKEN", String.format("Caminho: %s", path));
                File file = new File(path);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data);
                fileOutputStream.close();
                camera.startPreview();
            }
            catch (Exception e) {
                Log.e("EXCP_ON_PICTURE_TAKEN", UtilsException.getMessage(e));
            }
        }
    }
}
