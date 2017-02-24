package com.br.studyhandler.book.gooand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.br.studyhandler.R;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ExampleDownloadImage extends AppCompatActivity {

    private Handler handler;
    private static final String HANDLER_INSTANCE = "HANDLER_INSTANCE";
    private DownloadTask downloadTask;
    private ProgressBar progressBar;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_download_image);

        if(savedInstanceState != null) {}

        handler = new Handler();
        downloadTask = new DownloadTask(handler);
        final String urls [] = {
                 "https://www.facebook.com/rsrc.php/v3/yD/r/2wuGfVYB2an.png"
                ,"https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
                ,"http://st.codeforces.com/s/18867/images/codeforces-logo-with-telegram.png"
        };

        Button btnExecPostDelayed = (Button) findViewById(R.id.postDelayed);
        btnExecPostDelayed.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG_POST_DELAYED", "POST_DELAYED");
                    }
                }, 1000);
            }
        });

        Button btnExecDownload = (Button) findViewById(R.id.doDownload);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_download_img);
        progressBar.setVisibility(View.VISIBLE);
        btnExecDownload.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   executeDownloading(urls, 1);
               }
           }
        );
        warning = (TextView) findViewById(R.id.warning);
        downloadImage(urls[1]);
    }

    private void downloadImage(final String url) {
        final Context context = this;
        new Thread() {
            @Override
            public void run() {
                try {
                    final Bitmap bitmap = downloadTask.downloadBitmap(url);
                    if(bitmap != null) {
                        updateImageView(bitmap);
                    }
                    else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                //Toast.makeText(context, "Não foi realizado o Download da imagem", Toast.LENGTH_LONG).show();
                                warning.setText(String.format("Não foi realizado o Download da imagem\n%s", url));
                            }
                        });
                    }
                }
                catch (Exception e) {
                    Log.e("EXCEPTION_DOWN_IMG", e.getMessage(), e);
                }
                finally {

                }
            }
        }.start();
    }

    private void executeDownloading(final String urls [], int idx) {
        final Context context = this;
        final int newIdx = ++idx;
        final int id = newIdx % urls.length ;
        if(newIdx > 10) {
            Toast.makeText(this, "FIM da operacao", Toast.LENGTH_LONG).show();
            return;
        }
        String url = urls[id];
        downloadImage(url);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                executeDownloading(urls, newIdx);
            }
        }, 2000);

        /*
        new Thread() {
            @Override
            public synchronized void start() {
                try {
                    final String url = urls[id];
                    final Bitmap bitmap = downloadTask.downloadBitmap(url);
                    if(bitmap != null) {
                        updateImageView(bitmap);
                    }
                    else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                //Toast.makeText(context, String.format("Não foi realizado o Download da imagem\n%s", url), Toast.LENGTH_LONG).show();
                                warning.setText(String.format("Não foi realizado o Download da imagem\n%s", url));
                            }
                        });
                    }
                }
                catch (Exception e) {
                    Log.e("EXCEPTION_DOWN_IMG", e.getMessage(), e);
                }
                finally {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            executeDownloading(urls, newIdx);
                        }
                    }, 2000);
                }
            }
        }.start();
        */
    }

    private void updateImageView(final Bitmap bitmap) {
        // vou tentar atualizar a ProgressBar via thread
        // mas vai dar erro pois somente a UIThread pode faze-lo
        // progressBar.setVisibility(View.INVISIBLE);
        // ImageView imageView = (ImageView) findViewById(R.id.img_downloaded);
        // imageView.setImageBitmap(bitmap);
        /**
         * Os componentes da View gora criados pela UI Thread,
         * Iniciamos uma nova Thread para realizar o download, e o codigo acima
         * esta tentando atualizar a View ProgressBar a partir dessa nova thread
         * o que é proibido pela arquitetura do android
         **/
        // runOnUiThread() encapsula a chamada a handler.post(Runnable r)

        Runnable r = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                ImageView imageView = (ImageView) findViewById(R.id.img_downloaded);
                imageView.setImageBitmap(bitmap);
            }
        };
        runOnUiThread(r);
        //handler.post(r);
    }



    public static class DownloadTask {

        private Handler handlerDownloadTask;

        DownloadTask() {}

        DownloadTask(Handler handler) {
            handlerDownloadTask = handler;
        }

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
                handlerDownloadTask.post(new Runnable() {
                    @Override
                    public void run() {
                        if(excp != null && excp.getMessage() != null) {
                            Log.e("DOWNLOAD_BITMAP", excp.getMessage());
                        }
                    }
                });
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
                handlerDownloadTask.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("GET_STREAM_HTTPS", e.getMessage());
                    }
                });

            }

            finally {
                // return is;
            }

            return is;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // PersistableBundle
        //outState.putSerializable("T", (Serializable) handler);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            //handler = (Handler) savedInstanceState.getSerializable(HANDLER_INSTANCE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
