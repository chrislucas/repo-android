package com.project.studyalarmmanager;

import android.app.AlarmManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


/*
*
*  Um alarme pode ser disparado a cada X intervalos de tempo
*  ou  numa determinada hora do dia.
*
*  Ao definir um alarme uma intent e disparada ao S.O
*  qualquer aplicacao pode interceptar essa Intent (atraves de um IntentFilter)
*  podendo iniciar um servico ou um aplicativo
*
*  Podemos interceptar uma intent disparada atraves de um Alert com um
*  BroadcastReceiver e depois decidir o que fazer.
*
*  Exemplo: um alarme dispara uma intent, que eh interceptado por um broadcast
*  receiver que inicia um serviço
*
*  Mesmo se o dispositivo ficar inativo o Alarme sera executado
*
* */

public class MainActivity extends AppCompatActivity {


    public static final int ID_NOTIFICATION = 0xff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void headUpNot(View view) {
        Intent intent = new Intent(this, ActivityTest.class);
        String title = "Mensagem com Delay", message = "Essa notificação foi criada com intuito de ";
        NotificationUtils.delayedNotify(view, 5000, this, intent, title, message, ID_NOTIFICATION);
    }


    public void simpleNotification(View view) {
        Intent intent = new Intent(this, ActivityTest.class);
        String title = "Simple Notificação", message = "Essa notificação foi criada com intuito de ";
        NotificationUtils.create(this, new Intent(this, ActivityTest.class), title, message, ID_NOTIFICATION);
    }

    public void bigNotification(View view) {
        Intent intent = new Intent(this, ActivityTest.class);
        String title = "Notificação Grande", message = "Essa notificação foi criada com intuito de ";
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Mensagem 1");
        messages.add("Mensagem 2");
        messages.add("Mensagem 3");
        NotificationUtils.bigNotification(this, new Intent(this, ActivityTest.class), messages, title, message, ID_NOTIFICATION);
    }
}
