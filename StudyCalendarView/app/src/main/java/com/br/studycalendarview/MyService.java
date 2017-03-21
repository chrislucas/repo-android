package com.br.studycalendarview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyService extends Service {

    public MyService() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            /*
            AgendaPolitico.Caller caller = new AgendaPolitico().new Caller(getApplicationContext());
            final VincularMinhaAgenda vincularMinhaAgenda = new VincularMinhaAgenda(getApplicationContext(), caller);
            vincularMinhaAgenda.execute();
            */
            try {
                URL url = new URL("http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily");
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                //httpConn.setRequestMethod("POST");
                int responseCode = httpConn.getResponseCode();
                InputStream is = httpConn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String in;
                while( ( in = bufferedReader.readLine()) != null) {
                    Log.i("BUFFER", in);
                }
            } catch (Exception e) {
               Log.e("URL_EXCEPTION", e.getMessage());
            }
        }
        return startId;
    }
}
