package com.br.br0adcastreceiver.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by C.Lucas on 01/01/2017.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Finalizacao de Boot do SO", Toast.LENGTH_LONG).show();
    }
}
