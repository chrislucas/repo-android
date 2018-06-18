package com.project.services.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by r028367 on 28/02/2017.
 */

public class SMSIntercepterReceiver extends BroadcastReceiver {
    public SMSIntercepterReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Olha mensagem", Toast.LENGTH_LONG).show();
    }
}
