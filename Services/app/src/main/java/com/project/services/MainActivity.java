package com.project.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.project.services.services.SimpleService;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
    }

    public void startService(View view) {
        startService(new Intent(this, SimpleService.class));
    }


    public void stopService(View view) {
        stopService(new Intent(this, SimpleService.class));
    }
}
