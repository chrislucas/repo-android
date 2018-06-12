package com.br.br0adcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by C.Lucas on 01/01/2017.
 *
 * Classes do tipo BroadcastReceiver sao responsaveis
 * por receber uma Intent (mensagem) que foi capturada
 * por uma IntentFilter, e realizar algum processo.
 *
 * Atraves do arquivo AndroidManifest com a tag <receiver /> em conjunto
 * com a tag <intent-filter /> ou de forma dinamica
 * atraves do metodo registerReceiver(BroadcastReceiver, IntentFilter)
 * podemos registrar uma classe BroadcastReceiver e vincular a um filtro de intencoes
 * (Intent Filter). Dessa maneira, a aplicacao fica apta a processar uma mensagem
 * que trafega no SO
 *
 */

public class ExampleRC2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
    }


}
