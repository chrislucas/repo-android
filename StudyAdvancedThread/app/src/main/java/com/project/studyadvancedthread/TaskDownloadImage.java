package com.project.studyadvancedthread;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by r028367 on 24/02/2017.
 */

public class TaskDownloadImage extends AsyncTask<String, Integer, Bitmap> {

    private ProgressBar progressBar;
    private Context context;
    private ImageView imageView;
    private Handler handler;

    public TaskDownloadImage(Context context, ProgressBar progressBar, ImageView imageView) {
        this.context = context;
        this.progressBar = progressBar;
        this.imageView = imageView;
        this.handler = new Handler();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if(params != null && params.length > 0) {
            for(String url : params) {
                try {
                    Thread.sleep(2000);
                    setProgressBarVisibility(View.VISIBLE);
                    DownloadImageTask downloadImageTask = new DownloadImageTask();
                    final Bitmap bitmap = downloadImageTask.downloadBitmap(url);
                    if(bitmap != null) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                        /*
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {}
                        });
                        */
                    }
                    setProgressBarVisibility(View.INVISIBLE);

                } catch (InterruptedException e) {}

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        setProgressBarVisibility(View.INVISIBLE);
        TextView textView = (TextView) ((Activity) context).findViewById(R.id.message);
        textView.setText("Finalizado download");
    }

    private void setProgressBarVisibility(final int visibiliy) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(visibiliy);
            }
        });

    }

    public static class DownloadImageTask {

        DownloadImageTask() {}

        private Bitmap resourceToBitmap(InputStream is) {
            Bitmap bitmap = null;
            if(is != null) {
                bitmap = BitmapFactory.decodeStream(is);
            }
            return bitmap;
        }

        public Bitmap downloadBitmap(String url) {
            Bitmap bitmap = null;
            try {
                InputStream resource = new URL(url).openStream();
                if(resource != null) {
                    bitmap = resourceToBitmap(resource);
                    resource.close();
                }

                else {
                    resource = openStreamHttpsConn(url);
                    bitmap = resourceToBitmap(resource);
                }
            }
            catch (final Exception excp) {
                if(excp != null && excp.getMessage() != null) {
                    Log.e("DOWNLOAD_BITMAP", excp.getMessage());
                }
            }

            finally {
                // return bitmap;
            }
            return bitmap;
        }

        private InputStream openStreamHttpsConn(String urlStr) {
            InputStream is = null;
            try {
                URL url = new URL(urlStr);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                is = conn.getInputStream();
                is.close();
            } catch (final Exception e) {
                Log.e("GET_STREAM_HTTPS", e.getMessage());

            }
            finally {
                // return is;
            }

            return is;
        }
    }

}
