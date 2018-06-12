package com.project.studycontentprovider.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 17/02/2017.
 */

public class PermissionsUtils {

    public static final Integer REQUEST_CODE = 0xff0033;

    public static boolean validatePermission(Map<Integer, String> solicitations, Activity context) {
        List<String> permissions = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : solicitations.entrySet()) {
            String solicitation = entry.getValue();
            boolean ok = ContextCompat.checkSelfPermission(context, solicitation) == PackageManager.PERMISSION_GRANTED;
            if(!ok) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(context, solicitation)) {
                    // mostrar uma modal explicando
                }
                permissions.add(solicitation);
            }
        }
        if(permissions.size() == 0)
            return true;
        String [] newPermissions = new String[permissions.size()];
        permissions.toArray(newPermissions);
        ActivityCompat.requestPermissions(context, newPermissions, REQUEST_CODE);
        return false;
    }


    public static boolean validatePermission(List<String>solicitations, Activity context, int requestCode) {
        List<String> permissions = new ArrayList<>();
        for(String solicitation : solicitations) {
            boolean ok = ContextCompat.checkSelfPermission(context, solicitation) == PackageManager.PERMISSION_GRANTED;
            if(!ok) {
                if( ActivityCompat.shouldShowRequestPermissionRationale(context, solicitation) ) {}
                permissions.add(solicitation);
            }
        }
        if(permissions.size() == 0)
            return true;
        String [] newPermissions = new String[permissions.size()];
        permissions.toArray(newPermissions);
        ActivityCompat.requestPermissions(context, newPermissions, requestCode);
        return false;
    }
}
