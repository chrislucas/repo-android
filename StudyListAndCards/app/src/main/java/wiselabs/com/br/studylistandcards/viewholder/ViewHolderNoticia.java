package wiselabs.com.br.studylistandcards.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import wiselabs.com.br.studylistandcards.R;

/**
 * Created by C.Lucas on 14/12/2016.
 */
public class ViewHolderNoticia extends RecyclerView.ViewHolder {

    private TextView id, title, text, reference;

    public TextView getId() {
        return id;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getText() {
        return text;
    }

    public TextView getReference() {
        return reference;
    }

    public ViewHolderNoticia(View view) {
        super(view);
        id          = (TextView) view.findViewById(R.id.id_noticia);
        title       = (TextView) view.findViewById(R.id.titulo_noticia);
        text        = (TextView) view.findViewById(R.id.texto_noticia);
        reference   = (TextView) view.findViewById(R.id.referencia_noticia);
    }
}
