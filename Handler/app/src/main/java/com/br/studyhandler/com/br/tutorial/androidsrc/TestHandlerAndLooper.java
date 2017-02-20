package com.br.studyhandler.com.br.tutorial.androidsrc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.studyhandler.R;
import com.br.studyhandler.com.br.tutorial.androidsrc.DownloadThreadListener;

public class TestHandlerAndLooper extends AppCompatActivity
        implements DownloadThreadListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler_and_looper);
    }

    @Override
    public void handledownloadThreadUpdate() {

    }
}
