package com.br.sharepreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    
    private CheckBox keepMeLogged;
    private static final String KEY_KEEP_ME_LOGGED = "KEY_KEEP_ME_LOGGED ";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        keepMeLogged = (CheckBox) findViewById(R.id.keep_me_logged);
        boolean checked = HelperSharePreference.getBool(this, KEY_KEEP_ME_LOGGED);
        keepMeLogged.setChecked(checked);
    }
    
    public void saveValueKeepMeLogged(View view) {
        boolean checked = keepMeLogged.isChecked();
        HelperSharePreference.setBool(this, KEY_KEEP_ME_LOGGED, checked);
        Log.i("KEEP_ME_LOGGED", String.valueOf(checked));
    }
}
