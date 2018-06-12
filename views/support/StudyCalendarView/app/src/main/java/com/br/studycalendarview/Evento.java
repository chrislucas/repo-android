package com.br.studycalendarview;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by C.Lucas on 20/03/2017.
 */

public class Evento implements Parcelable {

    // talvez seja interessante ter um ENUM
    public enum SEGUIMENTO {

    }
    
    private Calendar dataInicio, dataFim;
    private String titulo;
    private String seguimento;
    private Location geoLocalizacao;
    private String localizacao;
    private String descricao;
    
    public Evento() {}
    
    public Evento(Parcel in) {
        this.dataInicio = (Calendar) in.readValue(Calendar.class.getClassLoader());
        this.dataFim    = (Calendar) in.readValue(Calendar.class.getClassLoader());
        this.titulo     = in.readString();
        this.seguimento = in.readString();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dataInicio);
        dest.writeValue(dataFim);
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

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicialFormatada() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("dd/mm/yyyy HH:mm:ss");
        Date date = this.getDataInicio().getTime();
        return simpleDateFormat.format(date);
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSeguimento() {
        return seguimento;
    }

    public void setSeguimento(String seguimento) {
        this.seguimento = seguimento;
    }

    public Location getGeoLocalizacao() {
        return geoLocalizacao;
    }

    public void setGeoLocalizacao(Location geoLocalizacao) {
        this.geoLocalizacao = geoLocalizacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
