package wiselabs.com.br.studylistandcards.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C.Lucas on 18/12/2016.
 */
public class Projeto implements Parcelable {

    private String assunto;
    private List<Politico> autores = new ArrayList<>();
    private String numero;
    private int ano;
    private String lei;
    private StatusProjeto status;
    private String linkCamara;
    private TipoProjeto tipoProjeto;

    public static final Creator<Projeto> CREATOR = new Creator<Projeto>() {
        @Override
        public Projeto createFromParcel(Parcel source) {
            return new Projeto(source);
        }

        @Override
        public Projeto[] newArray(int size) {
            return new Projeto[size];
        }
    };

    public Projeto(Parcel source) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
