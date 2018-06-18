package wiselabs.com.br.studylistandcards.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by C.Lucas on 18/12/2016.
 */
public class ViewHolderProjeto extends RecyclerView.ViewHolder {
    private TextView numero, autor, assunto, anotacao;

    public ViewHolderProjeto(View view) {
        super(view);
        this.numero = null;
        this.autor = null;
        this.assunto = null;
        this.anotacao = null;
    }
}
