package com.project.studyalarmmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceByAlarm extends Service {
    public ServiceByAlarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
}
