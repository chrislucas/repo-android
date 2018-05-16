package vc.com.icomon.camlibapi.actions.camera.impl;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import vc.com.icomon.camlibapi.actions.camera.CameraControl;
import vc.com.icomon.camlibapi.utils.exception.HelperLogException;
import vc.com.icomon.camlibapi.utils.file.HelperAccessExternalFiles;

public class SaveImage {

    private String appName = "DEFAULT";
    private Context context;
    private CameraControl cameraControl;


    private void saveImage(byte [] data) {
        Log.i("ON_PICTURE_TAKEN", String.format("SIZE_OF %d MB", data.length / (1024 * 1024)));
        Uri uri = HelperAccessExternalFiles.getExternalOutputMediaFileUri(HelperAccessExternalFiles.TypeOutputMediaFile.IMAGE, HelperAccessExternalFiles.TypeOfEnvironmentDir.DIR_PICTURES, "image", appName, context);
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
                Log.e("EXCP_ON_PICTURE_TAKEN", HelperLogException.getMessage(e));
            }
        }
    }
}
