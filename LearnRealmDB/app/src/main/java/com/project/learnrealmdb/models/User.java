package com.project.learnrealmdb.models;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by r028367 on 11/04/2017.
 *
 *
 */

// Tutorial
// https://medium.com/android-dev-br/inicia%C3%A7%C3%A3o-ao-realm-b7624e380194
// uma alternativa ao inves de herdar da classe RealmObject eh implementar
    // a interface RealmModel
    // porem a classe deve receber o anotation @RealClass

@RealmClass
public class User /* extends RealmObject */ implements Parcelable, RealmModel {

    @PrimaryKey
    @Index
    private int id;
    @Required
    private String name;
    private RealmList<Contact> contacts;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }

    public User() {
        this.contacts = new RealmList<>();
    }

    public User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        in.readList(this.contacts, Contact.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getName());
        dest.writeList(this.contacts);
    }




    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
