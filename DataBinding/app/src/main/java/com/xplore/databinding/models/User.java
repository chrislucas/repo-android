package com.xplore.databinding.models;

import android.databinding.ObservableField;

/**
 * Created by r028367 on 30/10/2017.
 */

public class User {

    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableField<Long> age = new ObservableField<>();

}
