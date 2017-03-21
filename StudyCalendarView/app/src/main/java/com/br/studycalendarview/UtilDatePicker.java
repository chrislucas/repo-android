package com.br.studycalendarview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by r028367 on 17/03/2017.
 */

public class UtilDatePicker {

    private static DatePickerDialog.OnDateSetListener dateSetListener;

    private static void instanceDateSetListener() {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };
    }

    public static void openCalendar(Context context, Calendar calendar) {
        instanceDateSetListener();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener
                ,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)
        );
    }


}
