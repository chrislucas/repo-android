package com.br.studysqlite.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by r028367 on 09/01/2017.
 */

public class User extends Entity implements Parcelable {

    private String name, pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /*
    * Esse construtor e util quando em algum ponto do codigo é necessario
    * montar um Array de Parcelable. Isso ocorre por exemplo quando queremos
    * passar um Array de Objetos de uma activity para outra atraves do metodo
    * putParcelableArrayListExtra(String, ArrayList<? extends Parcelable>)
    *
    * */

    public User(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
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
