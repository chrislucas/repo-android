package com.br.studyhandler.book.gooand;

import java.util.TreeMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.br.studyhandler.R;

/**
 * Created by C.Lucas on 17/02/2017.
 */

public class ExamplesUsingHandler {

    ExamplesUsingHandler() {}

    /**
     * Observacao interessante sobre Threads no Android.
     * se for necessario atualizar a UI usando Thread
     * somente a UIThread o a 'thread principal' pode
     * faze-la.
     *
     * A classe Handler foi criada para resolver esse problema.
     * Ela eh capaz de enviar uma mensagem a UIThread
     *
     * */
    public static void runTest1() {
        final Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("RUN_TEST_HANDLER", "Teste maroto");
                    }
                });
            }
        }.start();
    }


    public static void runTest1(final Activity activity) {
        /**
         * Pesquisar sobre
         * Can't create handler inside thread that has not called Looper.prepare(
         * */
        new Thread() {
            public void run() {
                activity.runOnUiThread/*new Handler(Looper.getMainLooper()).post*/(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) activity.findViewById(R.id.hello_world);
                        text.setText("Atualizado pela RunOnUIThread");
                        Log.i("RUN_TEST_HANDLER", "Teste maroto RunOnUIThread");
                    }
                });
            }
        }.start();
    }

}
