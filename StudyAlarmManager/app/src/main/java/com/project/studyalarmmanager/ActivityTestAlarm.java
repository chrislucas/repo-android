package com.project.studyalarmmanager;

import android.app.AlarmManager;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class ActivityTestAlarm extends AppCompatActivity {

    private Calendar time;

    public static final String TITLE = "TITLE";
    public static final String MESSAGE = "MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_alarm);
        time = Calendar.getInstance();
    }

    public long getTime() {
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, 5);
        return time.getTimeInMillis();
    }

    public void executeAlarmDateDefined(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION);
        intent.putExtra(TITLE, "Executado alarme data definida");
        intent.putExtra(MESSAGE, "Alarme com data definida, mas sem repetição");
        // agendar da hora atual + 5s
        AlarmUtils.schedule(this, intent, getTime());
    }

    public void executeAlarmDateUndefined(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION);
        intent.putExtra(TITLE, "Executando alarme sem data definida");
        intent.putExtra(MESSAGE, "Alarme sem data definida, com repetição");
        // agendar da hora atual + 5, de 30 em 30s
        AlarmUtils.scheduleRepeat(this, intent, getTime(), 15000);

    }

    public void cancelAlarm(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION);
        intent.putExtra(TITLE, "Cancelar alarme");
        intent.putExtra(MESSAGE, "Cancelando o alarme");
        AlarmUtils.cancel(this, intent);
    }

    public void executeAlarmDateDefinedRepeat(View view) {
        Intent intent = new Intent(ListenAlarm.ACTION);
        intent.putExtra(TITLE, "Alarme data definida e repetição");
        intent.putExtra(MESSAGE, "Alarme com repetição e hora definida");
        AlarmUtils alarmUtils = new AlarmUtils(this, intent);
        alarmUtils.reapet(13, 28, 20000);
    }
}
