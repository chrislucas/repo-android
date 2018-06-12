package com.project.cameraapi2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class CameraSurfaceview extends SurfaceView implements SurfaceHolder {

    private SurfaceHolder.Callback2 callback;
    private Context context;


    public CameraSurfaceview(Context context, Callback2 callback) {
        super(context);
        this.callback = callback;
        this.context = context;
    }

    @Override
    public void addCallback(Callback callback) {

    }

    @Override
    public void removeCallback(Callback callback) {

    }

    @Override
    public boolean isCreating() {
        return false;
    }

    @Override
    public void setType(int type) {

    }

    @Override
    public void setFixedSize(int width, int height) {

    }

    @Override
    public void setSizeFromLayout() {

    }

    @Override
    public void setFormat(int format) {

    }

    @Override
    public Canvas lockCanvas() {
        return null;
    }

    @Override
    public Canvas lockCanvas(Rect dirty) {
        return null;
    }

    @Override
    public void unlockCanvasAndPost(Canvas canvas) {

    }

    @Override
    public Rect getSurfaceFrame() {
        return null;
    }

    @Override
    public Surface getSurface() {
        return null;
    }

    @Override
    public Canvas lockHardwareCanvas() {
        return null;
    }


}
