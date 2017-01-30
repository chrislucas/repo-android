package com.example.r028367.servicedownloadimage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ServiceDownload extends Service {

    public static final String CAT = "SERVICE_DOWNLOAD";
    public static final String URL_P = "https://developer.android.com/static/images/android_logo_2x.png";

    public ServiceDownload() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CAT, "ONCREATE_SERVICE");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(CAT, "ONDESTROY_SERVICE");
    }

    private void download() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(URL_P);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestProperty("Request-Method", "GET");
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(false);
                    httpsURLConnection.connect();
                    InputStream in = httpsURLConnection.getInputStream();
                    Log.i(CAT, "LENDO a Image");
                    byte [] byteStreamImg = readBytes(in);
                    if(byteStreamImg != null) {
                        Log.i(CAT, "Imagem lida");
                        createNotification(byteStreamImg);
                    }
                    httpsURLConnection.disconnect();
                    stopSelf();
                } catch (IOException ioex) {
                    Log.e("DOWNLOAD_IMG_IOEX", ioex.getMessage());
                }
            }
        }.start();
    }

    private byte [] readBytes(InputStream in) throws IOException {
        ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
        byte [] bytes = null;
        try {
            byte [] buffer = new byte[1<<9];
            int len;
            while( (len = in.read(buffer)) > 0 ) {
                byteOs.write(len);
                bytes = byteOs.toByteArray();
            }
        } catch (Exception e) {
            Log.e("READ_BYTES_DOWN", e.getMessage());
        }

        finally {
            byteOs.close();
            in.close();
            return bytes;
        }
    }

    protected void createNotification(byte [] buffer) {
        String messageStatus = "Fim do download";
        String title = "Download completo";
        String message = "Visualizar imagem";
        Class<?> activity = ActivityImageViewer.class;

        Intent intent = new Intent(this, activity);
        intent.putExtra("image", buffer);

    }
}
