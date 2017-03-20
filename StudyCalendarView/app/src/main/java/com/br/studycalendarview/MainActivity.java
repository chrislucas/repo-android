package com.br.studycalendarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    
    
    ListView events;
    CalendarView calendarView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
