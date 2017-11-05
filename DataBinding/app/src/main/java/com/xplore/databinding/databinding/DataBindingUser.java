package com.xplore.databinding.databinding;

/**
 * Created by r028367 on 27/10/2017.
 */

public class DataBindingUser {

    private String firstName, lastName;
    private long age;


    public DataBindingUser(String firstName, String lastName, long age) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.age        = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
