package com.br.exploreapisensors.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by C.Lucas on 26/05/2017.
 */

public class AdapterSensor extends ArrayAdapter<Sensor> {
    
    public AdapterSensor(@NonNull Context context, @LayoutRes int resource, @NonNull List<Sensor> objects) {
        super(context, resource, objects);
    }
}
