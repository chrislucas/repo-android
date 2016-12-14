package wiselabs.com.br.studylistandcards.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wiselabs.com.br.studylistandcards.R;
import wiselabs.com.br.studylistandcards.entity.Noticia;
import wiselabs.com.br.studylistandcards.viewholder.ViewHolderNoticia;

/**
 * Created by C.Lucas on 14/12/2016.
 */
public class AdapterNoticia extends RecyclerView.Adapter {

    private List<Noticia> noticias;
    private Context ctx;

    public AdapterNoticia() {
        this.noticias = new ArrayList<>();
    }

    public AdapterNoticia(List<Noticia> noticias, Context context) {
        this.noticias = noticias;
        this.ctx = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolderNoticia vHolder = (ViewHolderNoticia) holder;
        Noticia  noticia = noticias.get(position);
        // preencher os TextViews
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.ctx).inflate(R.id.layout_vh_noticia
                ,parent, false);
        ViewHolderNoticia vHolder = new ViewHolderNoticia(view);
        return vHolder;
    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }
}
