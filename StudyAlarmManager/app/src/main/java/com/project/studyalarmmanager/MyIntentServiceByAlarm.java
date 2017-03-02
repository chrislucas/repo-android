package com.project.studyalarmmanager;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentServiceByAlarm extends IntentService {

    public MyIntentServiceByAlarm() {
        super("MyIntentServiceByAlarm");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {}
        Log.i("MY_INTENT_SERVICE", "ON_HANDLER_INTENT");
    }
}
