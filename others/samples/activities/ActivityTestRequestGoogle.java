package com.example.r028367.appicomon.samples.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.r028367.appicomon.R;
import com.example.r028367.appicomon.samples.httprq.GoogleApiHttpImplSample;

public class ActivityTestRequestGoogle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_request_google);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = "https://www.googleapis.com/books/v1/volumes?q=android";
        GoogleApiHttpImplSample goo = new GoogleApiHttpImplSample(url);
        //goo.setUrl("https://www.googleapis.com/books/v1/volumes?q=java");
        ///httpRq.runOnBackground();
        GoogleApiHttpImplSample.HttpRequestTask httpRq = new GoogleApiHttpImplSample.HttpRequestTask(goo);
        httpRq.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
