package com.project.looperandhandler;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * https://stackoverflow.com/questions/7597742/what-is-the-purpose-of-looper-and-how-to-use-it
 * */

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_title);
        testLooperRunnable();
    }

    /*
    * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
    *
    * Se colocar o Looper.prepare(); antes da chamada do metodo errorToastInsideThread
    * Caused by: java.lang.RuntimeException: Only one Looper may be created per thread
    * */
    private void errorToastInsideThread() {
        //Looper.prepare();
        final Context context = this;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Toast Inside Thread", Toast.LENGTH_LONG).show();
            }
        };
        new Thread(runnable, "Toast").start();
    }


    private void toastInseideThread() {
        final Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Toast Inside Thread", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void toastInseideThread2() {
        final Context context = this;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Toast Inside Thread", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void toastInseideThread3() {
        final Context context = this;
        final Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.arg1 == 1) {
                    Toast.makeText(context, "Toast Inside Thread", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                //java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
                Looper.prepare();
                Message message = new Message();
                message.arg1 = 1;
                callback.handleMessage(message);
                Looper.loop();
            }
        }, "Toast").start();
    }


    private void updateTextViewUsingHandlerThread() {
        //HandlerThread handlerThread = new HandlerThread("HandlerThread");
    }

    private void testLooperThread() {
        AbstractLooperThread looperThread = new LooperThread(this);
        looperThread.start();
    }

    public static final String KEY_MESSAGE_HANDLER = "KEY_MESSAGE_HANDLER";

    private Handler uiHandler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if(bundle != null) {
                final String message = bundle.getString(KEY_MESSAGE_HANDLER);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(message);
                            }
                        }, 2000);
                    }
                });
/*
                java.lang.IllegalStateException: Main thread not allowed to quit.
                Looper myLooper = Looper.getMainLooper();
                if(myLooper != null) {
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        myLooper.quitSafely();
                    }
                }
*/
            }
        }
    };

    private void testLooperRunnable() {
        /**
         * Quando uso A classe Runnable ocorre o seguinte erro
         * Only one Looper may be created per thread
         * */
        LooperRunnable looperRunnable = new LooperRunnable(uiHandler);
        //looperRunnable.run();
        //new Thread(looperRunnable).start();
        new Thread(looperRunnable).run();
    }

}
