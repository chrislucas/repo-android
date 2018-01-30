package com.project.cameraapi2.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.project.cameraapi2.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo com OpenCV
 *
 * */
public class XploreCameraOpenCVActivity extends AppCompatActivity implements CvCameraViewListener2 {


    private CameraBridgeViewBase openCvCameraView;
    private Mat cannyEdges;

    private static final int REQUEST_PERMISSION_CAMERA = 0xf3;

    private BaseLoaderCallback callback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    openCvCameraView.enableView();
                    cannyEdges = new Mat();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }

        @Override
        public void onPackageInstall(int operation, InstallCallbackInterface callback) {
            super.onPackageInstall(operation, callback);
        }
    };

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if(!OpenCVLoader.initDebug()){
            Log.d("TAG", "OpenCV not loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, callback);
        }
        else {
            Log.d("TAG", "OpenCV loaded");
            callback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        requestPermissions();
        openCvCameraView = (CameraBridgeViewBase) findViewById(R.id.camera_view_opencv);
        String cameraPermission = Manifest.permission.CAMERA;
        if(ActivityCompat.checkSelfPermission(this, cameraPermission)
                !=PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, cameraPermission)) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                final CvCameraViewListener2 cvCameraViewListener2 = this;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.setTitle("Permissão de câmera.")
                                .setMessage("Permitir câmera.")
                                .setCancelable(false)
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        openCvCameraView.setCvCameraViewListener(cvCameraViewListener2);
                                    }
                                })
                                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).create().show();
                    }
                });
            }
            else {
                ActivityCompat.requestPermissions(this, new String[] {cameraPermission}, REQUEST_PERMISSION_CAMERA);
            }
        }

        else {
            openCvCameraView.setVisibility(SurfaceView.VISIBLE);
            openCvCameraView.setCvCameraViewListener(this);
        }
    }

    /**
     * This method is invoked when camera preview has started. After this method is invoked
     * the frames will start to be delivered to client via the onCameraFrame() callback.
     *
     * @param width  -  the width of the frames that will be delivered
     * @param height - the height of the frames that will be delivered
     */
    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    /**
     * This method is invoked when camera preview has been stopped for some reason.
     * No frames will be delivered via onCameraFrame() callback after this method is called.
     */
    @Override
    public void onCameraViewStopped() {

    }

    /**
     * This method is invoked when delivery of the frame needs to be done.
     * The returned values - is a modified frame which needs to be displayed on the screen.
     * TODO: pass the parameters specifying the format of the frame (BPP, YUV or RGB and etc)
     *
     * @param inputFrame
     */
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat src = inputFrame.gray();
        Mat edge = new Mat();
        Imgproc.Canny(src, cannyEdges, edge, 10 , 15);
        Log.i("MAT_SRC", src.toString());
        Log.i("MAT_EDGE", edge.toString());
        Log.i("MAT_CANNY", cannyEdges.toString());
        return cannyEdges;
    }

    /**
     * Carregando OpenCV no metodo onResume
     * */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(openCvCameraView != null) {
            openCvCameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(openCvCameraView != null) {
            openCvCameraView.disableView();
        }
    }


    private static final int REQUEST_PERM = 0xff;

    private void requestPermissions() {
        List<String> permissionsDenied = new ArrayList<>();
        String permissions [] = {
            Manifest.permission.CAMERA
        };

        for (String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                permissionsDenied.add(permission);
            }
            boolean shouldRequest = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
            Log.i("SHOULD_REQUEST", String.format("%s %s", permission, String.valueOf(shouldRequest)));
        }

        if(permissionsDenied.size() > 0) {
            String arrayPermissions [] = new String[permissionsDenied.size()];
            permissionsDenied.toArray(arrayPermissions);
            ActivityCompat.requestPermissions(this, arrayPermissions, REQUEST_PERM);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( (permissions != null && permissions.length > 0)  && (grantResults != null && grantResults.length > 0)) {
            /*
            for(String permission : permissions)
                Log.d("PERMISSIONS_REQUEST", permission);
            for(int rs : grantResults)
                Log.d("PERMISSIONS_REQUEST", String.valueOf(rs));
            */
            if(requestCode == REQUEST_PERM && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCvCameraView.setCvCameraViewListener(this);
            }

            else if(requestCode == REQUEST_PERMISSION_CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCvCameraView.setCvCameraViewListener(this);
            }
        }
    }
}
