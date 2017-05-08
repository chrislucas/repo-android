package com.project.learnrealmdb.models;

import io.realm.annotations.Ignore;

/**
 * Created by r028367 on 11/04/2017.
 */

public enum Category {
    A(1), B(2), C(3);
    private int type;
    Category(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
}
