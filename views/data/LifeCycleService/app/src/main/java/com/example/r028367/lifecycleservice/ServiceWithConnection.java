package com.example.r028367.lifecycleservice;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by r028367 on 09/01/2017.
 */

public class ServiceWithConnection extends ServiceWorkerThread implements Count {
    public ServiceWithConnection() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int count() {
        return 0;
    }
}
