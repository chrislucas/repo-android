package com.example.r028367.lifecycleservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
// import android.support.annotation.WorkerThread;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 09/01/2017.
 *
 * Estudo sobre o ciclo de vida de uma Service
 *
 *
 */

public class ServiceWorkerThread extends Service /* implements Runnable */ {

    public class WorkerThread /* extends Thread */ implements Runnable  {

        private int startId;
        private boolean status;
        private int count;

        public WorkerThread(int startId) {
            this.startId    = startId;
            this.status     = true;
            this.count      = 0;
        }

        @Override
        public void run() {
            while(status && this.count <= MAX_EXEC) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e("ERROR_WORKEDTHREAD", e.getMessage());
                }
                Log.i(CATEGORY, String.format("run() ID Thread %d, Count: %d", this.startId, this.count++));
            }
            Log.i(CATEGORY, String.format("Encerrar Thread %d", this.startId));
            stopSelf(startId);
        }
    }

    public static final String CATEGORY = "SERVICE_CREATE_EXAMPLE";
    public static final int MAX_EXEC = 30;
    private List<WorkerThread> workerThreads = new ArrayList<>();


    public ServiceWorkerThread() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(CATEGORY, "ServiceWorkerThread.onBind()");
        return null;
    }

    /*
    * Metodo executado quando o servico eh criado e somente quando criado.
    * Se o mesmo servico for iniciado o metodo que sera executado eh o onStartCommand
    *
    * */
    @Override
    public void onCreate() {
        //super.onCreate();
        Log.i(CATEGORY, "ServiceWorkerThread.onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(CATEGORY, "ServiceWorkerThread.onStartCommand()");

        // Se WorkThread implementar Runnable
        // Eu devo criar uma instancia da classe Thread

        //ServiceWorkerThread.WorkerThread workerThread = new ServiceWorkerThread().new WorkerThread(startId);
        WorkerThread workerThread = new WorkerThread(startId);
        Thread thread = new Thread(workerThread);
        workerThreads.add(workerThread);
        // se a classe extender Thread, eu posso chamar o metodo start() direto
        // e nao preciso qriar uma instancia de thread
        //workerThread.start();
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }
/*
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
*/

    @Override
    public void onDestroy() {
        //super.onDestroy();
        /*
        * Esse metodo eh chamado uma unica vez, nao importa quantas Threads foram executadas
        *
        * */
        for(WorkerThread wt : workerThreads) {
            wt.status = false;
        }
        workerThreads.clear();
        Log.i(CATEGORY, "ServiceWorkerThread.onDestroy()");
    }

    /*
    * bindService(Intent service, ServiceConnection conn, int flags) permite
    * iniciar um servico se o mesmo ainda nao esta sendo executado
    *
    * Esse metodo nao chama a ckasse onStartCommand()
    *
    * O metodo startService() tem o objeto de iniciar um servico completamente
    * disvinculado com quem o chamaou, ja o bindService() tem o objetivo de vincular
    * uma classe (que implemente a interface IBinder) a um servico ja em execucao, para
    * poder chamar metodos dessa classe que foi vinculada
    *
    * Exemplo do livro:
    *
    * Se nos iniciamos um serviço a partir de uma activity, para ele rodar em segundo plano,
    * como acessamos a referencia desse servico a partir da activity ?
    *
    * Por exemplo, um player de mp3, cujo servico da musica eh executado em 2 plano mas
    * o controle esta na activity que iniciou a musica?
    *
    *
    * */

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
/*
    @Override
    public void run() {

    }
*/


}
