package com.br.br0adcastreceiver;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by C.Lucas on 01/01/2017.
 *
 *
 * Atraves da classe IntentFilter podemos capturar mensagens que trafegam dentro
 * do SO android. Essas mensagens podem ser enviadas atraves de uma Intent e capturadas
 * por um Filtro (IntentFilter)
 *
 * Atraves da classe BroadcastReceiver, podemos criar aplicacoes que reajam a essas mensagens.
 * Ela sempre eh executada em segundo plano, tem o objetivo de receber mensagens (Intent) e
 * realizar alguma tarefa, sem que o usuário perceba. Essa classe nos permite realizar algo
 * bastante interessate, integrar aplicativos
 *
 *
 * Nesse exemplo vamos testar algumas formas de registrar um 'Receiver'
 * tanto usando o arquivo AndroidManifest quanto registrando de forma
 * dinamica atraves do metodo registerReceiver
 *
 *
 */

public class Menu extends ListActivity {

    private static final String[] items = new String[] {
            "Receiver 1",
            "Receiver 2",
            "Receiver 3 - iniciar activity",
            "Erro ANR",
            "Abrir receiver do projeto Br0adcastReceiverStart",
            "Abrir receiver do projeto DownloadImagem"
    };

    private ExampleRC2 receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , items));
        receiver2 = new ExampleRC2();
        this.registerReceiver(receiver2, new IntentFilter("ACTION_RC2"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver2);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        Toast.makeText(this, items[position], Toast.LENGTH_LONG).show();
        switch(position) {
            case 0:
                intent = new Intent("ACTION_RC1");
                intent.putExtra("message", "ACTION_RC1");
                this.sendBroadcast(intent);
                break;
            case 1:
                intent = new Intent("ACTION_RC2");
                intent.putExtra("message", "ACTION_RC2");
                this.sendBroadcast(intent);
                break;
            case 2:
                this.sendBroadcast(new Intent("ACTION_RC3_ABRIR_ACTIVITY"));
                break;
            case 3:
                this.sendBroadcast(new Intent("ACTION_RC_ERROR_ANR"));
                break;
            case 4:
                intent = new Intent("ACTION_RC_ABRIR_APLICATIVO");
                this.sendBroadcast(intent);
                break;
            case 5:
                this.sendBroadcast(new Intent("ACTION_RC_ABRIR_APLICATIVO_DOWNLOAD_IMAGEM"));
                break;
            default:
                finish();
        }
    }
}
