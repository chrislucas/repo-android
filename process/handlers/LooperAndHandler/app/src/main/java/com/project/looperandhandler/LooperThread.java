package com.project.looperandhandler;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 * Created by r028367 on 20/06/2017.
 */

public class LooperThread extends AbstractLooperThread {

    public static final String BUNDLE_STRING = "BUNDLE_STRING";

    private Context context;
    public LooperThread(Context context) {
        this.context = context;
    }

    @Override
    protected void execute(Message message) {
        Bundle bundle = message.getData();
        if(bundle != null) {
            Log.i("MESSAGE", bundle.getString(BUNDLE_STRING));
        }
    }
}
