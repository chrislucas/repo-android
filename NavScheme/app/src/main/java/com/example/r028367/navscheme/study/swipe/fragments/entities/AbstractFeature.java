package com.example.r028367.navscheme.study.swipe.fragments.entities;

/**
 * Created by r028367 on 16/01/2017.
 */

public abstract class AbstractFeature {

    private String description;
    private String code;

    public AbstractFeature(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
