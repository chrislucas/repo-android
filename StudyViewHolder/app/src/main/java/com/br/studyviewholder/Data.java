package com.br.studyviewholder;

import android.widget.ImageView;

/**
 * Created by C.Lucas on 18/12/2016.
 */
public class Data {

    private Integer id;
    private String data;


    public Data(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
