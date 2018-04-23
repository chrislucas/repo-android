package com.project.cameraapi2.customviews.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

import com.project.cameraapi2.action.camera.CameraApiShutterCallback;
import com.project.cameraapi2.action.camera.CameraApiTakeJPEGPictureCallback;
import com.project.cameraapi2.action.camera2.OnChangeCameraOrientation;
import com.project.cameraapi2.utils.camera.UtilsCameraApi;
import com.project.cameraapi2.utils.exception.UtilsException;

import org.opencv.android.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CameraApiPreview extends SurfaceView implements SurfaceHolder.Callback2 {

    private Camera camera;
    private SurfaceHolder surfaceHolder;

    private boolean isCameraOpen = false;
    private Context context;

    private CameraApiTakeJPEGPictureCallback cameraApiTakeJPEGPictureCallback;
    private CameraApiShutterCallback cameraApiShutterCallback;

    private OnChangeCameraOrientation onChangeCameraOrientation;


    private void init(Context context) {
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        this.context = context;
        this.cameraApiShutterCallback = new CameraApiShutterCallback();
        this.camera = UtilsCameraApi.getCamera(this.context);
        this.cameraApiTakeJPEGPictureCallback = new CameraApiTakeJPEGPictureCallback(camera);
        this.onChangeCameraOrientation = new OnChangeCameraOrientation(context, camera);
    }

    public CameraApiPreview(Context context) {
        super(context);
        init(context);
    }

    public CameraApiPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CameraApiPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraApiPreview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


    }

    private void test(SurfaceHolder holder) {
        SurfaceHolder surfaceHolder = getHolder();
        Canvas canvas = surfaceHolder.lockCanvas();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        turnOnCamera();
    }

    public void startCamera() throws IOException {
        /**
         *
         * */
        camera.lock();
        camera.setPreviewDisplay(surfaceHolder);
        camera.startPreview();
    }


    public void turnOnCamera() {
        if (camera != null && ! isCameraOpen) {
            try {
                startCamera();
                isCameraOpen = true;
                Camera.Parameters parameters = camera.getParameters();
                parameters.setJpegQuality(100);
                WindowManager windowManager=  (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                if (windowManager != null) {
                    Display display = windowManager.getDefaultDisplay();
                    Point dimension = new Point();
                    display.getSize(dimension);;
                    int orientation = display.getRotation();
                    Log.i("ROTATION_ORIENTATION", String.format(Locale.getDefault()
                            ,"Orientation %d.\n D(%d, %d)", orientation, dimension.x, dimension.y));
                    switch (orientation) {
                        case Surface.ROTATION_0:
                            /**
                             * modificar a orientacao do objeto Parameters afeta a imagem a orientacao
                             * da foto tirada pelo usuario que pode ser capturada no callback na implementacao
                             * da class {@link Camera.PictureCallback#onPictureTaken(byte[], Camera)}
                             * */
                            //camera.setDisplayOrientation(0);
                            // modificar a orientacao da camera afeta a View que eh motrada para o usuario
                            //parameters.setRotation(0);
                            onChangeCameraOrientation.onOrientationChanged(0);
                            break;
                        case Surface.ROTATION_90:
                            /*
                            camera.setDisplayOrientation(90);
                            parameters.setRotation(90);
                            */
                            onChangeCameraOrientation.onOrientationChanged(90);
                            break;
                        case Surface.ROTATION_180:
                            /*
                            camera.setDisplayOrientation(180);
                            parameters.setRotation(180);
                            */
                            onChangeCameraOrientation.onOrientationChanged(180);
                            break;
                        case Surface.ROTATION_270:
                            /*
                            camera.setDisplayOrientation(270);
                            parameters.setRotation(270);
                            */
                            onChangeCameraOrientation.onOrientationChanged(270);
                            break;
                    }

                    parameters.setPictureSize(dimension.x, dimension.y);
                    List<String> focusMode = parameters.getSupportedFocusModes();
                    if (focusMode != null && focusMode.size() > 0) {
                        for (String mode : focusMode) {
                            Log.i("SUPPORTED_FOCUS_MODE", mode);
                        }
                        if (focusMode.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        }
                        if(focusMode.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                        }
                    }
                    parameters.setPreviewFrameRate(20);
                }
                //camera.setParameters(parameters);
                Log.i("CAMERA_API", "Camera Ligada");
            }
            catch (IOException e) {
                Log.e("EXCP_SURFACE_CREATED", UtilsException.getMessage(e));
            }
        }
    }

    public void turnOffCamera() {
        if (camera != null && isCameraOpen) {
            camera.unlock();
            camera.stopPreview();
            isCameraOpen = false;
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        turnOffCamera();
    }

    @Override
    public void surfaceRedrawNeededAsync(SurfaceHolder holder, Runnable drawingFinished) {

    }

    public void takeAPicture() {
        if (camera != null) {
            camera.takePicture(cameraApiShutterCallback, null, cameraApiTakeJPEGPictureCallback);
        }
    }


}
