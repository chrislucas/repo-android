package wiselabs.com.br.studylistandcards.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by C.Lucas on 14/12/2016.
 */
public class ViewHolderNoticia extends RecyclerView.ViewHolder {

    final TextView id, text, reference;

    public ViewHolderNoticia(View itemView) {
        super(itemView);
        id = null;
        text = null;
        reference = null;
    }
}
