package com.xplore.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xplore.databinding.databinding.ActivityClockBinding;
import com.xplore.databinding.models.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityClock extends AppCompatActivity {

    private ActivityClockBinding activityClockBinding;
    private String formattedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        activityClockBinding = DataBindingUtil.setContentView(this, R.layout.activity_clock);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateTime();
                        handler.postDelayed(this, 500);
                    }
                }, 500);
            }
        };
        new Handler().post(runnable);
    }

    private void updateTime() {
        final Time time = new Time();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        formattedTime = simpleDateFormat.format( Calendar.getInstance().getTime());
        time.formattedTime.set(formattedTime);
        Log.i("CLOCK", formattedTime);
        activityClockBinding.setFmtTime(time);
    }

}
