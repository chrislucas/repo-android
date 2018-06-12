package com.project.cameraapi2.action.handler;

import android.os.Handler;
import android.os.Message;

public class HandlerCamera extends Handler {

    public HandlerCamera() {
        super();
    }

    public HandlerCamera(Callback callback) {
        super(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }
}
