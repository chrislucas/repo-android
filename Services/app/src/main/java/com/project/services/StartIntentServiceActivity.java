package com.project.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.services.services.MyIntentService;
import com.project.services.services.SimpleService;

public class StartIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_intent_service);
    }

    public void startIntentService(View view) {
        startService(new Intent(this, MyIntentService.class));
    }


    public void stopIntentService(View view) {
        stopService(new Intent(this, MyIntentService.class));
    }
}
