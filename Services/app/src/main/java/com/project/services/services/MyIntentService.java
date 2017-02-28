package com.project.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


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
    }

    private void exec() {
        try {
            Thread.sleep(500);
        } catch (Exception ex) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        counter = 0;
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
