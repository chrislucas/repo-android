package com.example.r028367.studynearfieldcommunication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/*
* Estudo sobre troca de dados usando tecnologia NFC (Near Field Comunnication)
*
* Filtros de intencoes quando usamos NFC
*
* Quando filtramos intent NFC uma boa pratica eh ser o mais especifico possivel
* sobre os dados que queremos, assim evitamos janelas pop-ups que perguntem qual a
* funcionalidade que o usuario deseja executar
*
* A comunicacao via NFC exige que dispositivos com tal tecnlogia estejam muito proximos
* para trocar informacao
*
* A tag de disparo do sistema tem 3 acoes para o filtro de intencoes da NFC.
*   ACTION_NDEF_DISCOVERED  -   envia a intencao para quem tem o filtro se a informacao encontrada esta formatada como NDEF
*   ACTION_TECH_DISCOVERED  -
*   ACTION_TAG_DISCOVERED   -   assim que a tag e capturada, uma intent sera iniciada
* */

public class MainActivity extends AppCompatActivity {

    private Button btnAddMessage;
    private TextView textReceived, textSended;
    private EditText inputTextSend;

    private ArrayList<String> messagesToSend, messagesReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            messagesToSend = savedInstanceState.getStringArrayList("messagesToSend");
            messagesReceived = savedInstanceState.getStringArrayList("messagesReceived");
        }

        setContentView(R.layout.activity_main);
        NFCUtils nfc    =  new NFCUtils();
        boolean hasNFC  = nfc.checkNFC(this);

        messagesReceived    = new ArrayList<>();
        messagesToSend      = new ArrayList<>();

        Log.wtf("HAS_NFC", String.valueOf(hasNFC));

        btnAddMessage   = (Button) findViewById(R.id.buttonAddMessage);
        textReceived    = (TextView) findViewById(R.id.textReceived);
        textSended      = (TextView) findViewById(R.id.textSended);
        inputTextSend   = (EditText) findViewById(R.id.inputTextSend);

    }


    public void addMessage(View view) {
        String message = inputTextSend.getText().toString();
        if(message != null)
            messagesToSend.add(message);
        inputTextSend.setText(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putStringArrayList("messagesReceived", messagesReceived);
        outState.putStringArrayList("messagesToSend", messagesToSend);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        messagesToSend = savedInstanceState.getStringArrayList("messagesToSend");
        messagesReceived = savedInstanceState.getStringArrayList("messagesReceived");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("RESUME", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ONRESTART", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ONDESTROY", "onDestroy");
    }

    @Override
    public void finish() {
        super.finish();
        Log.i("FINISH", "finish");
    }
}
