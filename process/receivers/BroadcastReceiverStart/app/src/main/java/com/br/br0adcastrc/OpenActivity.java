package com.br.br0adcastrc;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by C.Lucas on 01/01/2017.
 */

public class OpenActivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Outra aplicacao", Toast.LENGTH_LONG).show();
        Intent it = new Intent(context, MainActivity.class);
        it.setComponent(new ComponentName("com.br.br0adcastrc", "com.br.br0adcastrc.MainActivity"));
        it.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //it.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(it);
    }
}
