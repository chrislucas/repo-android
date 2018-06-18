package com.project.importdb;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import java.util.List;

/**
 * Created by r028367 on 01/02/2017.
 */

public class NotificationUtils {

    public static NotificationCompat.Builder notificationActionless(Context context, int drawable, String title, String message, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setAutoCancel(true);
        builder.setSmallIcon(drawable);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, builder.build());
        return builder;
    }


    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        /*
        * Colocando a activity que criou essa intent na pilha de activities
        * Dessa forma, se o App for fechado, ao disparar a intent  atraves
        * da notification, conseguimos voltar para activity que originou a
        * intent de notificacao
        * https://developer.android.com/reference/android/support/v4/app/TaskStackBuilder.html
        * */
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        // Cria uma intent e atualiza uma possivel intent com o mesmo id
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    private static PendingIntent getSimplePendingIntent(Context context, Intent intent, int request) {
        PendingIntent p = PendingIntent.getActivity(context, request, intent, PendingIntent.FLAG_ONE_SHOT);
        return p;
    }

    private static void create(Context context, Intent intent, String title, String message, int id, int type) {
        PendingIntent pendingIntent = null;
        switch (type) {
            case 0:
                pendingIntent = getSimplePendingIntent(context, intent, id);
                break;
            case 1:
                pendingIntent = getPendingIntent(context, intent, id);
                break;
        }

        NotificationCompat.Builder builder = getComumNotificationBuilder(context, pendingIntent, title, message);
        builder.setVibrate(new long[] {1000, 1000, 1000, 1000} );
        builder.setAutoCancel(true);        // cancela a notificacao ao clicar sobre ela
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, builder.build());
    }

    public static void createSimpleNotification(Context context, Intent intent, String title, String message, int id) {
        create(context, intent, title, message, id, 0);
    }

    public static void createWithTask(Context context, Intent intent, String title, String message, int id) {
        create(context, intent, title, message, id, 1);
    }


    public static void create(Context context, Intent intent, String title, String message, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);
        NotificationCompat.Builder builder = getComumNotificationBuilder(context, p, title, message);
        builder.setAutoCancel(true);        // cancela a notificacao ao clicar sobre ela
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, builder.build());
    }

    private static NotificationCompat.Builder getComumNotificationBuilder(Context context, PendingIntent pendingIntent, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentIntent(pendingIntent);        // intent que sera chamada ao clicar na notificacao
        return builder;
    }

    public static void createHeapUpNotification(Context context, Intent intent, String title, String message, int id) {
        PendingIntent pendingIntent = getPendingIntent(context, intent, id);
        NotificationCompat.Builder builder = getComumNotificationBuilder(context, pendingIntent, title, message);
        builder.setAutoCancel(true);                    // cancela a notificacao ao clicar sobre ela
        /*
        * Se usarmos o metodo setColor, o icone da notificacao deve ser png com fundo transaparente
        * pois esse metodo ira pintar o fundo da imagem com a cor que passamos como argumento desse metodo
        * */
        builder.setColor(Color.BLACK);
        builder.setFullScreenIntent(pendingIntent, false);

        /*
        *
        * NotificationCompat.VISIBILITY_PUBLIC
        * NotificationCompat.VISIBILITY_PRIVATE
        * NotificationCompat.VISIBILITY_SECRET
        *
        * */
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, builder.build());
    }

    public static boolean delayedNotify(View view, int interval, final Context context
            , final Intent intent, final String title, final String message, final int id) {
        boolean exec = false;
        if(view != null) {
            /*
            * Handler postDelayed: https://developer.android.com/reference/android/os/Handler.html
            * vai criar uma notificacao com atraso de X segundos
            * */
            exec = view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    createHeapUpNotification(context, intent, title, message, id);
                }
            }, interval);
        }
        return exec;
    }

    public static boolean delayedNotify(final INotification iNot, View view, int interval, final Context context
            , final Intent intent, final String title, final String message, final int id) {
        boolean exec = false;
        if(view != null) {
            /*
            * Handler postDelayed: https://developer.android.com/reference/android/os/Handler.html
            * vai criar uma notificacao com atraso de X segundos
            * */
            exec = view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder builder = iNot.builderNotification(context, intent, title, message, id);
                    NotificationManagerCompat nm = NotificationManagerCompat.from(context);
                    nm.notify(id, builder.build());
                }
            }, interval);
        }
        return exec;
    }

    public static void cancellAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }

    public static void bigNotification(Context context, Intent intent, List<String> messages, String title, String message, int id) {
        PendingIntent pendingIntent = getPendingIntent(context, intent, id);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(title);
        inboxStyle.setSummaryText(message);
        int lines = 0;
        if(messages != null && messages.size() > 0) {
            lines = messages.size();
            for (String msg : messages)
                inboxStyle.addLine(msg);
        }

        NotificationCompat.Builder builder = getComumNotificationBuilder(context, pendingIntent, title, message);
        builder.setAutoCancel(true);
        builder.setNumber(lines);
        builder.setStyle(inboxStyle);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, builder.build());
    }

    public interface INotification {
        public NotificationCompat.Builder builderNotification(Context context, Intent intent, String title, String message, int id);
    }

}
