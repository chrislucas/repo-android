package com.br.studyhandler.com.br.tutorial.androidsrc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.BufferUnderflowException;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by r028367 on 15/02/2017.
 */

public class DownloadTask implements Runnable {

    private static final String TAG = DownloadTask.class.getSimpleName();
    private static final Random random = new Random();
    public DownloadTask() {}

    @Override
    public void run() {
        HttpURLConnection http = null;
        try {
            Thread.sleep(random.nextInt(15) * 1000);
            http = (HttpsURLConnection) new URL("https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").openConnection();

            int statusCode = http.getResponseCode();
            if(statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("STATUS_CONN", String.valueOf(statusCode));
                return;
            }

            InputStream inputStream = http.getInputStream();

            if(inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String data = null;
                FileOutputStream fos = new FileOutputStream("image");
                while( (data = bufferedReader.readLine()) != null) {
                    Log.i("DATA_DOWNLOAD", data);
                    byte [] buffer = data.getBytes();
                    fos.write(buffer);
                }

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                fos.close();
                inputStream.close();
            }
        }
        catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }

        finally {
            if(http != null) {
                http.disconnect();
            }
        }
    }

    public void readDataFromURL(String urlStr) {
        try {
            URL url =  new URL(urlStr);
            InputStream is = url.openStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            String in;
            while( (in = buffer.readLine()) != null) {

            }
            buffer.close();
            is.close();
        } catch (Exception e) {

        }
    }
}
