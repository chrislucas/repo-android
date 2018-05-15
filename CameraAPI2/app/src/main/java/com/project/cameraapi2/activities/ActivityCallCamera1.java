package com.project.cameraapi2.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.cameraapi2.R;

import vc.com.icomon.camlibapi.activity.ActivityCamera;

public class ActivityCallCamera1 extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_camera1);
    }

    public void abrirCamera(View view) {
        Intent intent = new Intent(this, ActivityCamera.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivityCamera.BUNDLE_APP_NAME, getString(R.string.app_name));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
