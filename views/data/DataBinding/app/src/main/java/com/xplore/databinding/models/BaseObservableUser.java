package com.xplore.databinding.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by r028367 on 27/10/2017.
 */

public class BaseObservableUser extends BaseObservable {

    private String firstName, lastName;
    private long age;

    public BaseObservableUser(String firstName, String lastName, long age) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.age        = age;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);

    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
