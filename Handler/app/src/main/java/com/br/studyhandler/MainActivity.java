package com.br.studyhandler;


import android.os.Message;
import android.os.Handler;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.br.studyhandler.book.gooand.ExamplesUsingHandler;


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

    private MyHandler myHandler;
    public static final int TAG_MESSAGE_1 = 1;
    public static final int TAG_MESSAGE_2 = 2;

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
        myHandler.sendMessageDelayed(message, 3000);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        message = new Message();
        message.what = TAG_MESSAGE_2;
        String data1 = calendar.getTime().toString();
        long delay = SystemClock.uptimeMillis() + 4;
        Calendar aCalendar =  Calendar.getInstance();
        aCalendar.setTimeInMillis(delay);
        String data2 = aCalendar.getTime().toString() ;
        Log.i("OUTRA_MENSAGEM", String.format("%s\n%s", data1, data2));
        myHandler.sendMessageAtTime(message, delay);
    }


    public static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what) {
                case MainActivity.TAG_MESSAGE_1:
                    Log.i("MESSAGE", "TAG_MESSAGE_1");
                    break;
                case MainActivity.TAG_MESSAGE_2:
                    Log.i("MESSAGE", "TAG_MESSAGE_2");
                    break;
            }
        }
    }

    public void postMsg(View view) {

    }

}
