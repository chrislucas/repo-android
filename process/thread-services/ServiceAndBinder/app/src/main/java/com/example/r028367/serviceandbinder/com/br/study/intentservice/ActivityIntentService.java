package com.example.r028367.serviceandbinder.com.br.study.intentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.r028367.serviceandbinder.R;

public class ActivityIntentService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        startService(new Intent(this, MyIntentService.class));
    }
}
