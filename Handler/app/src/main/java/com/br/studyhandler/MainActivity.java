package com.br.studyhandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.studyhandler.book.gooand.ExamplesUsingHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ExamplesUsingHandler.runTest1();
        ExamplesUsingHandler.runTest1(this);
    }
}
