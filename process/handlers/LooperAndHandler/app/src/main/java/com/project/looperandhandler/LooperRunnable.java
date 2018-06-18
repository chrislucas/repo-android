package com.project.looperandhandler;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by r028367 on 20/06/2017.
 */

public class LooperRunnable implements Runnable {

    private static final int INTERNAL_MSG = 0xff;

    protected Handler uiHandler;

    public LooperRunnable(Handler uiHandler) {
        this.uiHandler = uiHandler;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        /**
         * Se o programador chamar o metodo Thread.run, o metodo Looper.prepare
         * eh chamado, se chamar o Thread.start nao. O metodo Looper.prepare
         * so pode ser chamado uma vez, caso contrario uma exceção eh lançada.
         * Para evitar esse problema, basta fazer essa verificacao abaixo.
         * */
        boolean start = false;
        /**
         * Thread nao tem mensagens vinculadas a elas. Usando handler podemos
         * vincular mensagens a Threads e transportar informacoes entre processos.
         * Essa eh uma forma interessante de atualizar componentes de interface grafica
         * */
        if(Looper.myLooper() == null) {
            Looper.prepare();
            start = true;
        }
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                /**
                 * Se eu tentar usar a mesma mensagem
                 * java.lang.IllegalStateException: { when=-2s163ms what=255 target=com.project.looperandhandler.MainActivity$6 } This message is already in use.
                 * */
                if(msg.what == INTERNAL_MSG) {
                    Message msg2    = new Message();
                    Bundle bundle   = new Bundle();
                    bundle.putString(MainActivity.KEY_MESSAGE_HANDLER, "hello world update");
                    msg2.setData(bundle);
                    uiHandler.sendMessage(msg2);
                }
            }
        };
        Message message = new Message();
        message.what = INTERNAL_MSG;
        handler.sendMessage(message);
        /**
         * Se a implementacao da Thread chamar o metodo run(), Looper.run() laca uma excecao
         * Se chamar o metodo start() o metodo Looper.loop precisa ser chamada, para que
         * a instrucao handler.sendMessage(message) acima, seja executada
         * */
        if(start) {
            // fazer com que o Handler comece a processar as nmensagens na fila da thread
            Looper.loop();
        }
    }
}
