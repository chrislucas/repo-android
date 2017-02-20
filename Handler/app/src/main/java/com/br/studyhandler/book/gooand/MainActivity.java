package com.br.studyhandler.book.gooand;


import android.os.Message;
import android.os.Handler;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.br.studyhandler.R;


import java.util.Calendar;


/**
 * Explicação sucinta sobre Processos no Android
 *
 * Cada aplicação roda num processo separado no Android. Cada
 * processo possui uma unica Thread responsavel por atualizar
 * as views da aplicação (UIThread) e somente essa thread pode
 * atualizar uma view
 *
 * Podemos utilizar uma instancia da classe Handler para enviar
 * uma mensagem para UIThread para que essa por sua vez, possa
 * atualizar os componentes UI
 *
 * */

public class MainActivity extends AppCompatActivity {

    private Handler myHandler;
    public static final int TAG_MESSAGE_1 = 1;
    public static final int TAG_MESSAGE_2 = 2;
    public static final int TAG_EMPTY_MESSAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ExamplesUsingHandler.runTest1();
        ExamplesUsingHandler.runTest1(this);
        myHandler = new MyHandler();
    }


    public void sendDelayedMessage(View view) {
        Message message = new Message();
        message.what = TAG_MESSAGE_1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        // Testar como funciona a passagem de dados usando a classe
        // Message
        Bundle bundle   = new Bundle();
        long delay1     = calendar.getTimeInMillis();
        String data1    = calendar.getTime().toString();
        bundle.putString("DATE", data1);
        message.setData(bundle);
        myHandler.sendMessageDelayed(message, 3000);

        // segunda mensagem
        message = new Message();
        message.what    = TAG_MESSAGE_2;

        /**
         * SystemClock:
         *
         * */

        long delay2     = SystemClock.uptimeMillis() + 3100;
        Calendar calendar2 =  Calendar.getInstance();
        calendar2.setTimeInMillis(delay2);
        String data2 = calendar2.getTime().toString();
        Log.i("OUTRA_MENSAGEM_1", String.format("%s\n%s", data1, data2));
        Log.i("OUTRA_MENSAGEM_2", String.format("%d\n%d", delay1, delay2));
        myHandler.sendMessageAtTime(message, delay2);
    }


    public static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Bundle bundle;
            switch (msg.what) {
                case MainActivity.TAG_MESSAGE_1:
                    Log.i("MESSAGE", "TAG_MESSAGE_1");
                    bundle = msg.getData();
                    if(bundle != null) {
                        String date = bundle.getString("DATE");
                        Log.i("TAG_MESSAGE_1", date);
                    }
                    break;
                case MainActivity.TAG_MESSAGE_2:
                    Log.i("MESSAGE", "TAG_MESSAGE_2");
                    break;
                case MainActivity.TAG_EMPTY_MESSAGE:
                    Log.i("MESSAGE", "TAG_EMPTY_MESSAGE");
                    // acredito que quando usamos o metodo sendEmptyMessage
                    // a variavel msg nao carrega esse bundle consigo
                    if(msg != null ) {
                        bundle = msg.getData();
                        if( bundle != null) {
                            // a varoavel date recebera null;
                            String date = bundle.getString("DATE");
                            Log.i("TAG_EMPTY_MESSAGE", date != null ? date : "null " + msg.what);
                        }
                    }
                    break;
            }
        }
    }



    public void actionPostMessage(View view) {

    }

    public void actionSendEmptyMessage(View view) {
        Message message = new Message();
        message.what = TAG_EMPTY_MESSAGE;
        Bundle bundle = new Bundle();
        bundle.putString("DATE", Calendar.getInstance().getTime().toString());
        message.setData(bundle);
        // provavelmente nao adianta criar esse bundle
        // o metodo sendEmptyMessage funciona como se so tivesse
        // definido o valor da variavel message.what
        myHandler.sendEmptyMessage(message.what);
    }

}
