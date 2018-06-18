package com.project.services.mp3project;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.BundleCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.project.services.MainActivity;
import com.project.services.NotificationUtils;
import com.project.services.R;
import com.project.services.mp3project.Services.MultimidiaService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    public static final int REQUEST_LIST_PERMISSION = 0x0f;
    public static final int REQUEST_PERMISSION_WRITE_EXT = 0xff;
    public static final String BIND_PLAYER = "BIND_PLAYER";
    private static final String PATH_MP3_FILE = "/storage/emulated/0/Music/i_wanna_be.mp3";

    private MultimidiaInterface multimidiaInterface;
    private PlayerMultimidiaInterface player;
    private Context context = this;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        /**
         * 3 - Se a chamada ao metodo bindService(Intent service, ServiceConnection conn, int flags)
         * foi bem sucessidada o metodo onServiceConnected sera chamado
         * */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(name != null) {
                Log.i("ON_SERVICE_CONNECTED", name.toString());
            }

            if(service != null) {
                try {
                    String desc = service.getInterfaceDescriptor();
                    String str = service.toString();
                    if(str != null) {
                        Log.i("ON_SERVICE_CONNECTED", str);
                    }
                } catch (RemoteException e) {
                    Log.e("REMOTE_EX_SERV_CONN", e.getMessage());
                }
            }
            // captura uma instancia do tipo IBinder, que esta conectado com servico
            MultimidiaService.Mp3ServiceBinder connectionServiceBinder = (MultimidiaService.Mp3ServiceBinder) service;
            // recuperar a interface que da acesso ao servico
            multimidiaInterface = connectionServiceBinder.getInterfaceMp3();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            /**
             * 9 liberando recursos
             * */
            Log.i("ON_SERV_DISCONNECTED", "Liberando Recursos");
            NotificationUtils.createSimpleNotification(context, new Intent(), "DISCONNECTED", "Disconectando Player, Liberando Recursos", 1);
            multimidiaInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        requestPermissions();
        String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if(ContextCompat.checkSelfPermission(this
                ,WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            /**
             * Devo mostrar uma explicacao
             * */
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE) ) {
                // abrir uma modal
            }

            //else {
                ActivityCompat.requestPermissions(this, new String[] {WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_EXT);
            //}
        }

        else {
            initService();
        }
        // paths();
    }

    private void initService() {
        // player = new PlayerMultimidiaInterface();
        Intent intent = new Intent(this, MultimidiaService.class);
        /*
        Bundle bundle = new Bundle();
        BundleCompat.putBinder(bundle, BIND_PLAYER, player);
        intent.putExtras(bundle);
        */
        // 1 - inicia o servico
        startService(intent);
        // 2 - cria uma conexao com o Servico MultimidiaService
        boolean isServiceBinded = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        Log.i("SERVICE_BIND", String.valueOf(isServiceBinded));
    }


    private void paths() {
        /**
         *
         * {@link ContextCompat}
         * {@link android.os.Environment#DIRECTORY_MUSIC},
         * {@link android.os.Environment#DIRECTORY_PODCASTS},
         * {@link android.os.Environment#DIRECTORY_RINGTONES},
         * {@link android.os.Environment#DIRECTORY_ALARMS},
         * {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
         * {@link android.os.Environment#DIRECTORY_PICTURES}, or
         * {@link android.os.Environment#DIRECTORY_MOVIES}.
         *
         * http://stackoverflow.com/questions/5694933/find-an-external-sd-card-location
         * http://stackoverflow.com/questions/5858107/how-to-get-file-path-from-sd-card-in-android
         * */
        File[] paths = ContextCompat.getExternalFilesDirs(this, null);
        if(paths != null && paths.length > 0)
            for (File path : paths)
                Log.i("PATHS", path.toString());
        File ext = Environment.getExternalStorageDirectory();
        Log.i("EXT", ext.toString());
        final String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");
    }


    private void requestPermissions() {
        List<String> permissionsDenied = new ArrayList<>();
        String permissions [] = {
             Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for (String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                permissionsDenied.add(permission);
            }
        }

        String arrayPermissions [] = new String[permissionsDenied.size()];
        permissionsDenied.toArray(arrayPermissions);
        if(arrayPermissions.length > 0)
            ActivityCompat.requestPermissions(this, arrayPermissions, REQUEST_LIST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LIST_PERMISSION:
                break;
            case REQUEST_PERMISSION_WRITE_EXT:
                if(grantResults != null && grantResults.length > 0 ) {
                    if( grantResults[0] == PackageManager.PERMISSION_GRANTED  ){
                        initService();
                    }
                }
                break;
        }

        if(permissions != null && permissions.length > 0) {
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                for (String permission : permissions) {
                    Log.i("PERMISSION_ACTIVITY_2", permission);
                    if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        initService();
                    }
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        // a musica fica tocando mesmo se essa activity for fechada
        if(multimidiaInterface != null && multimidiaInterface.isPlaying()) {
            NotificationUtils.createSimpleNotification(this, new Intent(this, MainActivity.class), "UNBINDSERVICE", "Desconectando o serviço de música", 1);
        }

        else {
            /**
             * 7encerrando serviço caso nao estaja tocando nenhuma musica
             * */
            stopService(new Intent(this, MultimidiaService.class));
        }
    }

    public void play(View view) {
        /*
        if(player != null)
            player.play(PATH_MP3_FILE);
            */
        if(multimidiaInterface != null) {
            multimidiaInterface.play(PATH_MP3_FILE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void pause(View view) {
        /*
        if(player != null)
            player.pause();
            */
        if(multimidiaInterface != null) {
            multimidiaInterface.pause();
        }
    }

    public void stop(View view) {
        /*
        if(player != null)
            player.stop();
        */
        if(multimidiaInterface != null) {
            multimidiaInterface.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
        if(player != null)
            player.close();
        */
        if(multimidiaInterface != null) {
            multimidiaInterface.close();
        }
    }
}
