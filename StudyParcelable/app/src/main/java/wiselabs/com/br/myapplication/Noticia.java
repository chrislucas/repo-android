package wiselabs.com.br.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.acl.NotOwnerException;

/**
 * Created by C.Lucas on 12/12/2016.
 *
 * Estudo sobre a interface Parcelable. Quais as diferenças entre ela
 * e a interface Serializable
 *
 * Parcelable
 * https://developer.android.com/reference/android/os/Parcelable.html
 *
 * Parcel
 * https://developer.android.com/reference/android/os/Parcel.html
 *
 */
public class Noticia implements Parcelable {

    private int id;
    private String text, reference;

    public static final Parcelable.Creator<Noticia> CREATOR = new Parcelable.Creator<Noticia>() {

        @Override
        public Noticia createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
