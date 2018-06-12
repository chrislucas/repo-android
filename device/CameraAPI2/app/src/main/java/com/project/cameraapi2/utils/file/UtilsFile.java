package com.project.cameraapi2.utils.file;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.util.Log;

import com.project.cameraapi2.R;

import java.io.File;
import java.lang.annotation.Retention;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.annotation.RetentionPolicy.*;


public class UtilsFile {

    public static final int VIDEO = 1;
    public static final int IMAGE = 2;
    @IntDef({VIDEO , IMAGE})
    @Retention(SOURCE)
    private @interface TypeOutputMediaFile { }


    @Retention(SOURCE)
    @StringDef({DIR_PICTURES, DIR_MOVIES})
    private @interface TypeOfEnvironmentDir { }
    public static final String DIR_PICTURES = "Pictures";
    public static final String DIR_MOVIES = "Movies";

    public static Uri getExternalOutputMediaFileUri(@TypeOutputMediaFile int typeOutput, @TypeOfEnvironmentDir String typeOfEnv, String folder, Context context) {
        return Uri.fromFile(getExternalOutputMediaFile(typeOutput, typeOfEnv, context, folder));
    }

    private static File getExternalOutputMediaFile(@TypeOutputMediaFile int type, @TypeOfEnvironmentDir String typeOfEnv, Context context, String folder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE/YYYY", Locale.getDefault());
        long time = Calendar.getInstance().getTimeInMillis();
        Date date = new Date(time);
        folder = String.format("%s/%s/%s", folder
                , simpleDateFormat.format(date)
                , context.getString(R.string.app_name));

        File externalMediaFileDir = new File(Environment.getExternalStoragePublicDirectory(typeOfEnv), folder);
        if (!externalMediaFileDir.exists()) {
            if (!externalMediaFileDir.mkdirs()) {
                Log.e("EXTERNAL_OUTPUT", "Erro ao criar o diretório");
                return null;
            }
        }
        File f = null;
        simpleDateFormat.applyPattern("ddEEEYYYYHHmmss");
        String filename =  simpleDateFormat.format(date);

        String path = externalMediaFileDir.getPath().concat(File.separator);
        switch (typeOfEnv) {
            case DIR_PICTURES:
                path = path.concat("IMG_".concat(filename).concat(".jpg"));
                break;
            case DIR_MOVIES:
                path = path.concat("VIDEO_".concat(filename).concat(".mp4"));
                break;
            default:
                return null;
        }
        f = new File(path);
        return f;
    }


}
