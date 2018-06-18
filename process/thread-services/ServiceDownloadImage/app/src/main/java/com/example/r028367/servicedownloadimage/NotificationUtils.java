package com.example.r028367.servicedownloadimage;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;

/**
 * Created by r028367 on 01/02/2017.
 */

public class NotificationUtils {


    private Context context;
    private CharSequence tickerText, title;

    public NotificationUtils(Context context, CharSequence tickerText, CharSequence title) {

    }

    public void create(CharSequence message, int icon, int id, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = null;
        int apiLevel = Build.VERSION.SDK_INT;
        if(apiLevel >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.build();
            }
            else {
                notification = builder.getNotification();
            }
        }
        else {
            notification = new Notification(icon, tickerText, System.currentTimeMillis());
        }

        NotificationManager nManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        nManager.notify(id, notification);
    }


    public void createBigNotification(CharSequence message, int icon, int id, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = null;
        int apiLevel = Build.VERSION.SDK_INT;
        if(apiLevel >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentText(message)
                    .setContentTitle(title)
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Notification.InboxStyle style = new Notification.InboxStyle();
                style.setBigContentTitle(title);
                builder.setStyle(style);
                notification = builder.build();
            }
            else {
                notification = builder.getNotification();
            }
        }
        else {
            notification = new Notification(icon, tickerText, System.currentTimeMillis());
        }
    }

    public void notiticationProgressBar(CharSequence message, int icon, int id, Intent intent, int maxProgress, int progress) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = null;
        int apiLevel = Build.VERSION.SDK_INT;
        if(apiLevel >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent)
                    .setProgress(maxProgress, progress, false);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.build();
            }
            else {
                notification = builder.getNotification();
            }
        }
        else {
            notification = new Notification(icon, tickerText, System.currentTimeMillis());
        }

        NotificationManager nManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        nManager.notify(id, notification);
    }

    public void notiticationInfiniteProgressBar(CharSequence message, int icon, int id, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = null;
        int apiLevel = Build.VERSION.SDK_INT;
        if(apiLevel >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent)
                    .setProgress(0, 0, true);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.build();
            }
            else {
                notification = builder.getNotification();
            }
        }
        else {
            notification = new Notification(icon, tickerText, System.currentTimeMillis());
        }

        NotificationManager nManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        nManager.notify(id, notification);
    }


    public static void cancell(Context context, int id) {
        NotificationManager nManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        nManager.cancel(id);
    }
}
