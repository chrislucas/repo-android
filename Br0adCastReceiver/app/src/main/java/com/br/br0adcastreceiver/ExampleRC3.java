package com.br.br0adcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.br.br0adcastreceiver.activities.OpenByReceiver;

/**
 * Created by C.Lucas on 01/01/2017.
 */

public class ExampleRC3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent it = new Intent(context, OpenByReceiver.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }
}
