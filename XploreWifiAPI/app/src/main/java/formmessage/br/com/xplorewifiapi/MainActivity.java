package formmessage.br.com.xplorewifiapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final int REQUEST_PERMISSION_CODE = 0x32;

    private void requestPermissions() {
        String [] permissions = {
             Manifest.permission.INTERNET
            ,Manifest.permission.ACCESS_WIFI_STATE
            ,Manifest.permission.CHANGE_WIFI_STATE
            ,Manifest.permission.ACCESS_NETWORK_STATE
            ,Manifest.permission.READ_PHONE_STATE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ArrayList<String> permissionShouldAsk = new ArrayList<>();
        for(String permission : permissions) {
            if( ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED )
                permissionShouldAsk.add(permission);
        }
        if(permissionShouldAsk.size() > 0) {
            String permissionDenied [] = new String[permissionShouldAsk.size()];
            ActivityCompat.requestPermissions(this, permissionShouldAsk.toArray(permissionDenied), REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults != null && grantResults.length > 0) {
            if(permissions != null && permissions.length > 0) {
                for(int idx=0; idx<grantResults.length; idx++) {}
            }
        }
    }
}
