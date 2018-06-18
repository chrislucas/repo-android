package wiselabs.com.br.studylistandcards.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.acl.NotOwnerException;
import java.util.Random;

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

    private Integer id, idClass;
    private String title, text, reference;

    public Integer getId() {
        return id;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getReference() {
        return reference;
    }

    public Noticia(Integer id, String title, String text, String reference) {
        this.id = id;
        this.text = text;
        this.reference = reference;
        this.title = title;
    }

    protected Noticia(Parcel in) {
        this.id = in.readByte() == 0x00 ? null : in.readInt();
        this.text = in.readString();
        this.reference = in.readString();
        this.title = in.readString();
        Random rdm = new Random();
        this.idClass = rdm.nextInt(Integer.MAX_VALUE - (1 << 15)) + (1 << 15);
    }

    /*
    *   Definicao da classe Parcel:
    *   Container para uma referencia de um objeto que pode ser enviado atraves da Interface
    *   IBinder (https://developer.android.com/reference/android/os/IBinder.html)
    *   Parcel pode conter dados "comprimidos/achatados", tal processo pode ser feito para
    *   o envio e desfeito quando a classe que deve receber os dados, recebe-los. A interface
    *   Parcelable pode ser usada para fazer o trabalho de compressão (serializacao) para o envio.
    *   Parcel tambem pode manter uma referencia de um objeto do tipo IBinder.
    *
    *   Segundo a documentacao, a classe Parcel não foi construida como um mecanismo de Serializacao.
    *   Ela junto a interface Parcelable foram desenvolvidas para implementar alta performace em
    *   "IPC transport"
    *
    *
    *   Um ponto interessante é que, a classe Parcel não foi projetada para ser armazenada em banco
    *   de dados. Seu proposito é realizar a leitura e a escrita de quaisquer tipos de dados
    *
    * */
    public static final Creator<Noticia> CREATOR = new Creator<Noticia>() {

        @Override
        public Noticia createFromParcel(Parcel source) {
            return new Noticia(source);
        }

        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };

    /*
    * Devolver um Inteiro como identificador da classe
    * */
    @Override
    public int describeContents() {
        return this.idClass;
    }
    /*
    *
    * Metodo responsavel por serializar a classe
    * */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.reference);
        dest.writeString(this.text);
        dest.writeString(this.title);
    }
}

/*
* Explicacao interessate sobre Parcelable
* http://pt.stackoverflow.com/questions/38492/quando-e-como-implementar-o-parcelable-vs-serializable
*
* pesquisar sobre IPC transport
* http://pt.slideshare.net/jserv/android-ipc-mechanism
* http://stackoverflow.com/questions/11994001/is-parcelable-worth-to-implement-to-pass-data-between-activities
* */