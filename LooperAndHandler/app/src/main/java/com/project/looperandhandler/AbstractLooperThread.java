package com.project.looperandhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;

/**
 * Created by r028367 on 19/06/2017.
 *
 * Ainda pensaodo como da para brincar com essa classe
 */

public abstract class AbstractLooperThread extends Thread {
    protected Handler handler;
    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler() {
            /**
             * Subclasses must implement this to receive messages.
             *
             * @param msg
             * Agora essa implementacao de Thread pode receber uma mensagem
             */
            @Override
            public void handleMessage(Message msg) {
                /**
                 * A especializacao dessa classe deve implementar o metodo execute(Messsage msg)
                 * */
                execute(msg);
            }
        };
        /**
         * Executar {@link android.os.MessageQueue} nessa Thread
         *
         * */
        handler.sendMessage(buildFakeMessage());
        MessageQueue messageQueue   = Looper.myQueue();
        String stringMsgQueue       = messageQueue.toString();
        Log.i("MESSAGE_QUEUE", stringMsgQueue);
        Looper.loop();
    }

    private final Message buildFakeMessage() {
        Message message = new Message();
        Bundle bundle   = new Bundle();
        bundle.putString(LooperThread.BUNDLE_STRING, "Hello World");
        message.setData(bundle);
        return message;
    }

    protected abstract void execute(Message message);

}
