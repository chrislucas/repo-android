package com.br.br0adcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by C.Lucas on 01/01/2017.
 */

public class ErrorANR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Toast.makeText(context, "Testando ERRO ANR, Android Not Respond", Toast.LENGTH_LONG).show();
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("ANR_ERROR", e.getMessage());
        }
    }
}
