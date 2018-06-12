package com.project.cameraapi2.activities;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.cameraapi2.R;

import java.lang.ref.WeakReference;

import vc.com.icomon.camlibapi.activity.ActivityCamera;
import vc.com.icomon.camlibapi.utils.camera.HelperCameraApi;

public class ActivityCallCamera1 extends AppCompatActivity  {


    private static final int REQUEST_CODE = 0x33;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_camera1);
        imageView = findViewById(R.id.photo);
    }

    public void abrirCamera(View view) {
        Intent intent = new Intent(this, ActivityCamera.class);
        startActivityForResult(intent, ActivityCamera.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ActivityCamera.REQUEST_CODE:
                if (resultCode == ActivityCamera.RESULT_CODE_OK) {
                    Bundle bundle = data.getExtras();
                    assert bundle != null;
                    byte [] dataFromCamera = bundle.getByteArray(ActivityCamera.BUNDLE_BYTE_ARRAY);
                    WeakReference<Bitmap> bitmapWeakReference =  HelperCameraApi.generateBitmap(dataFromCamera);
                    Bitmap bitmap = bitmapWeakReference.get();
                    imageView.setImageBitmap(bitmap);
                }
                break;
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    // acesso a uma foto thumb
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imageBitmap);
                }
                break;
        }
    }

    public void tirarFotoUsandoIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE);
        }
    }
}
