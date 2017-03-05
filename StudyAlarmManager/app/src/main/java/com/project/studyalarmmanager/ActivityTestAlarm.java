package com.project.studyalarmmanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityTestAlarm extends AppCompatActivity {

    private Calendar time;

    public static final String TITLE    = "TITLE";
    public static final String MESSAGE  = "MESSAGE";

    private TextView hour;
    private boolean shutdown;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_alarm);
        time = Calendar.getInstance();
        hour = (TextView) findViewById(R.id.text_hour);
        handler  = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                dateFormat.applyPattern("kk:mm:ss");
                //Log.i("HOUR", dateFormat.format(date));
                hour.setText(dateFormat.format(date));
                handler.postDelayed(this, 1000);
            }
        };
/*
        final Runnable r = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat();
                        dateFormat.applyPattern("kk:mm:ss");
                        Log.i("HOUR", dateFormat.format(date));
                        hour.setText(dateFormat.format(date));
                    } catch (InterruptedException e) {}
                }
            }
        };
*/
        initializerAlarm();

    }


    public void initializerAlarm() {
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        long shiftTime = calendar.getTimeInMillis();

        Intent intent = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intent.putExtra(TITLE, "Executando alarme sem data definida");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("kk:mm:ss");
        String message = String.format("Alarme sem data definida %s", simpleDateFormat.format(new Date(time)));
        intent.putExtra(MESSAGE, message);
        Intent serviceByAlarm = new Intent(this, ServiceByAlarmDateUndefined.class);
        Bundle bundleService = new Bundle();
        bundleService.putBoolean(ServiceByAlarmDateUndefined.BUNDLE_BOOL, false);
        serviceByAlarm.putExtras(bundleService);

        Bundle bundleBroadcast = new Bundle();
        bundleBroadcast.putParcelable(ListenAlarm.BUNDLE_INTENT_SERVICE, serviceByAlarm);
        intent.putExtras(bundleBroadcast);

        // agendar da hora atual + 5, de 15 em 15
        AlarmUtils.scheduleRepeat(this, intent, shiftTime, 15000);
    }

    /**
     * Vamos nos aproveitar do ciclo de vida da Activity
     *
     * */

    @Override
    protected void onStart() {
        super.onStart();
    }

    public long getTime() {
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, 5);
        return time.getTimeInMillis();
    }

    public void executeAlarmDateDefined(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intent.putExtra(TITLE, "Executado alarme data definida");
        intent.putExtra(MESSAGE, "Alarme com data definida, mas sem repetição");
        // agendar da hora atual + 5s
        AlarmUtils.schedule(this, intent, getTime());
    }

    public void executeAlarmDateUndefined(View view) {
        Intent intentCallBroadcast = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intentCallBroadcast.putExtra(TITLE, "Executando alarme sem data definida");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("kk:mm:ss");
        String message = String.format("Alarme sem data definida %s", simpleDateFormat.format(new Date(getTime())));
        intentCallBroadcast.putExtra(MESSAGE, message);
        Intent serviceByAlarm = new Intent(this, MultiThreadServiceByAlarmDateUndefined.class);
        Bundle bundleService = new Bundle();
        bundleService.putString(MultiThreadServiceByAlarmDateUndefined.BUNDLE, "Segura essa mensagem, MULTITHREAD");
        serviceByAlarm.putExtras(bundleService);
        Bundle bundleBroadcast = new Bundle();
        bundleBroadcast.putParcelable(ListenAlarm.BUNDLE_INTENT_SERVICE, serviceByAlarm);
        intentCallBroadcast.putExtras(bundleBroadcast);
        // agendar da hora atual + 5, de 30 em 30s
        AlarmUtils.scheduleRepeat(this, intentCallBroadcast, getTime(), 15000);
    }

    public void cancelAlarm(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intent.putExtra(TITLE, "Cancelar alarme");
        intent.putExtra(MESSAGE, "Cancelando o alarme");
        AlarmUtils.cancel(this, intent);
    }

    public void executeAlarmDateDefinedRepeat(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intent.putExtra(TITLE, "Alarme data definida e repetição");
        intent.putExtra(MESSAGE, "Alarme com repetição e hora definida");
        AlarmUtils alarmUtils = new AlarmUtils(this, intent);
        alarmUtils.reapet(13, 28, 20000);
    }


    public void start(View view) {
        handler.post(runnable);
    }

    public void stop(View view) {
        handler.removeCallbacks(runnable);
    }

    public void callIntentServiceByAlarm(View view) {
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        long shiftTime = calendar.getTimeInMillis();

        Intent intent = new Intent(ListenAlarm.ACTION_BROADCAST_RECEIVERF_LISTEN_ALARM);
        intent.putExtra(TITLE, "Executando alarme sem data definida");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("kk:mm:ss");
        String message = String.format("Alarme sem data definida %s", simpleDateFormat.format(new Date(time)));
        intent.putExtra(MESSAGE, message);
        Intent serviceByAlarm = new Intent(this, MyIntentServiceByAlarm.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ListenAlarm.BUNDLE_INTENT_SERVICE, serviceByAlarm);
        intent.putExtras(bundle);
        // agendar da hora atual + 5, de 15 em 15
        AlarmUtils.scheduleRepeat(this, intent, shiftTime, 15000);
    }
}
