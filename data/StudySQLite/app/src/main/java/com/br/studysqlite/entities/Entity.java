package com.br.studysqlite.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * Created by r028367 on 09/01/2017.
 */

public abstract class Entity {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Entity() {
    }
}
