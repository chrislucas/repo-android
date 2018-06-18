package com.project.studyalarmmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by r028367 on 08/02/2017.
 */

public interface INotification {
    public NotificationCompat.Builder builderNotification(Context context, Intent intent, String title, String message, int id);
}
