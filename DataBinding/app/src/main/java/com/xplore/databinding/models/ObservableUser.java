package com.xplore.databinding.models;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.xplore.databinding.BR;


/**
 * Created by r028367 on 30/10/2017.
 */

public class ObservableUser implements Observable {

    private PropertyChangeRegistry pcr;
    private String firstName, lastName;
    private long age;

    public ObservableUser(String firstName, String lastName, long age) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.age        = age;
        this.pcr        = new PropertyChangeRegistry();
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        pcr.notifyChange(this, BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        pcr.notifyChange(this, BR.lastName);
    }

    @Bindable
    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
        pcr.notifyChange(this, BR.age);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        pcr.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        pcr.remove(callback);
    }
}
