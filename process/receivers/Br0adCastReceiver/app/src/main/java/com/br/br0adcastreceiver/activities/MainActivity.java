package com.br.br0adcastreceiver.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.br0adcastreceiver.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Intent intent = new Intent("ACTION_RC1");
        intent.putExtra("message", "MainActivity");
        sendBroadcast(intent);
        */
    }
}
