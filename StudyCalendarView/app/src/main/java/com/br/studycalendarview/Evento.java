package com.br.studycalendarview;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by C.Lucas on 20/03/2017.
 */

public class Evento implements Parcelable {
    
    private Calendar calendar;
    private String titulo;
    private String seguimento;
    
    public Evento() {}
    
    public Evento(Parcel in) {
        in.readValue(Calendar.class.getClassLoader());
        this.titulo = in.readString();
        this.seguimento = in.readString();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(calendar);
        dest.writeString(this.titulo);
        dest.writeString(this.seguimento);
    }
    
    public static final Creator<Evento> CREATOR = new Creator<Evento> () {
        @Override
        public Evento createFromParcel(Parcel source) {
            return new Evento(source);
        }
        
        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
        
    };
}
