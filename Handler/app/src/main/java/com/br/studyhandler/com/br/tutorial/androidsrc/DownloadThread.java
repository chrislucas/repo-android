package com.br.studyhandler.com.br.tutorial.androidsrc;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import android.os.Handler;

/**
 * Created by r028367 on 15/02/2017.
 */

public class DownloadThread extends Thread {
    private static final String TAG = DownloadThread.class.getSimpleName();
    private Handler handler;
    private int totalQueued;
    private int totalCompleted;

    private DownloadThreadListener listener;

    public DownloadThread(DownloadThreadListener l) {
        this.listener = l;
    }

    @Override
    public void run() {
        try {
            // https://developer.android.com/reference/android/os/Looper.html
            /**
             * Classe usada para rodar uma mensagem vinculada a uma thread
             * Por padraao threads nao tem mensagens vinculadas a elas
             * */
            Looper.prepare();

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("MESSAGE_HANDLER", msg.toString());
                }
            };

            Looper.loop();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public synchronized void  requestStop() {

        /**
         *
         * https://developer.android.com/reference/android/os/Handler.html
         *
         * O metodo run() da interface Runnable
         * ira rodar sobre a Thread que o handler
         * em questao esta vinculado (DOC)
         *
         *
         *
         * */
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
