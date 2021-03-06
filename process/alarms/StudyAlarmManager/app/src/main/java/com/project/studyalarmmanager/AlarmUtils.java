package com.project.studyalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by r028367 on 08/02/2017.
 */

public class AlarmUtils {

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private Context context;
    public static int MINIMAL_TIMER_ALARM = 1200000; // 20 * 60 * 1000
    public static final String TAG = "ALARMUTIL";


    public AlarmUtils(Context context, Intent intent, int codeRequest) {
        this.context    = context;
        alarmManager    = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmIntent     = PendingIntent.getBroadcast(context, codeRequest, intent, 0);
    }


    public AlarmUtils(Context context, Intent intent, int codeRequest, int flag1, int flag2) {
        this.context    = context;
        alarmManager    = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmIntent     = PendingIntent.getBroadcast(context, codeRequest, intent, flag1 | flag2);
    }


    public PendingIntent pIntentService(Context context, Intent intent, int codeRequest) {
        PendingIntent pendingIntent = PendingIntent.getService(context, codeRequest, intent, 0);
        return  pendingIntent;
    }


    public PendingIntent pBroadcast(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return  pendingIntent;
    }

    // AlarmManager.RTC
    // Executa o alarme numa hora especificada

    // AlarmManager.RTC_WAKEUO
    // IDEM ao de cima, porem ira acordar a CPU se necessario

    // AlarmManager.ELAPSED_REALTIME
    // Geralmente usado quando o Alarme nao deve se preocupar com a hora
    // especifica que deve ser iniciado, e sim que ao passar o intervalo
    // de tempo pre estabelecido, ele sera executado

    // AlarmManager.ELAPSED_REALTIME_WAKEUP
    // IDEM ao de cima, porem a CPU sera acordada se necessario para executar o alarme
    public void reapet(int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        defineAlartRepeat(calendar, interval, AlarmManager.ELAPSED_REALTIME_WAKEUP);
    }

    public void reapet(int hour, int minute, long intervalMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, (hour % 24) == 0 ? 1 : (hour % 24) );
        calendar.set(Calendar.MINUTE, (minute % 60) == 0 ? 1 : (minute % 60));
        //Log.i(TAG, String.format("Repeat com hora definida %s", calendar.getTime().toString()));
        defineAlartRepeat(calendar, intervalMillis, AlarmManager.RTC_WAKEUP);
    }


    private void defineAlartRepeat(Calendar calendar, long interval, int typeAlarm) {
        /**
         * AlarmManager.RTC_WAKEUP
         * */
        alarmManager.setRepeating(typeAlarm, calendar.getTimeInMillis(), interval, alarmIntent);
    }

    /*
    * Definir um alarme na data/hora informado
    * */
    public static void schedule(Context context, Intent intent, long triggerAtMillis, int codeRequest) {
        /**
         * PendingIntent.getBroadcast
         *
         * PendingIntent.FLAG_UPDATE_CURRENT
         *
         * */
        PendingIntent p = PendingIntent.getBroadcast(context, codeRequest, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, p);
        //Log.i(TAG, "ALARME com data/hora definido");
    }

    public static void scheduleRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis, int codeRequest) {
        PendingIntent p = PendingIntent.getBroadcast(context, codeRequest, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
    }


    public static void cancel(Context context, Intent intent, int codeRequest) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, codeRequest, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(p);
    }
}
