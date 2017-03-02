package com.project.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    private boolean running;
    private static final int MAX_EXEC = 10;
    private int counter;

    public class WorkerThread extends Thread {
        private boolean status;
        private int startId, count;

        public WorkerThread(int startId) {
            super("SERVICE" + startId);
            this.startId = startId;
            this.status = true;
            this.count = 0;
        }

        @Override
        public void run() {
            while(this.status && this.count < MAX_EXEC) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("EXCEPTION_THREAD", e.getMessage());
                }
                Log.i("THREAD_INTENT_SERV_RUN", String.format("THREAD ID: %d Counter: %d", startId, count));
                count++;
            }
            Log.i("THREAD_STOP_SELF", String.format("THREAD ID: %d", startId));
            stopSelf(startId);
        }
    }

    List<WorkerThread> threads = new ArrayList<>();;
    private int startId = 1;
    MyIntentService.WorkerThread workerThread;
    /**
     *
     * IntentService executa o metodo onHadleIntent
     * */
    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            running = true;
            counter = 0;
            while(running && counter < MAX_EXEC) {
                exec();
                Log.i("INTENT_SERVICE", String.format("COUNTER %d", counter));
                counter++;
            }
        }


        MyIntentService.WorkerThread workerThread = new MyIntentService().new WorkerThread(startId++);
        threads.add(workerThread);
        Log.i("onHandleIntent", String.format("WORKER %d", workerThread.startId));
        workerThread.start();

    }

/*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ON_START_INTENT_SERVICE", String.format("ON_START_COMMAND %d", startId));
        this.startId = startId;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("ON_START_INTENT_SERV", String.format("ON_START %d", startId));
        this.startId = startId;
    }
*/
    private void exec() {
        try {
            Thread.sleep(500);
        } catch (Exception ex) {}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*
        running = false;
        counter = 0;
        */
        for(WorkerThread workerThread : threads) {
            workerThread.status = false;
        }
        threads.clear();
        Log.i("ON_DESTROY_INTENT_SERV", "FINISH");
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {

    }
}
