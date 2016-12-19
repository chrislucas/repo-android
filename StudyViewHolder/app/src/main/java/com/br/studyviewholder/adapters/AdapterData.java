package com.br.studyviewholder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.studyviewholder.Data;
import com.br.studyviewholder.R;
import com.br.studyviewholder.ViewHolderData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by C.Lucas on 18/12/2016.
 */
public class AdapterData extends BaseAdapter {

    private Context context;
    private List<Data> list;


    public AdapterData(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Data getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Context getContext() {
        return context;
    }

    public List<Data> getList() {
        return list;
    }


    /*
    *
    * Fato interessante sobre o list view: segundo site alura
    * quando criamos um listview, a classe verifica atraves da classe
    * adapter quantos elementos existem na lista, atraves do metodo getCount()
    *
    * Sabendo quantos itens serao, a classe se prepara para exibir quantos
    * forem possiveis na tela. Assim, o SO ira inflar N itens atraves do metodo
    * getView(), mais 2 itens serao inflados, para que possam ser reaproveitados.
    *
    * Esses itens sao passados como parametro como 'View convertView'
    *
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolderData viewHolderData = null;

        /*
        * Vamos passar a utilizar o parametro convertView,
        * para aumentar a performance da ListView
        * */
        // verficar se temos uma view pronta
        if( convertView == null ) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_item_data, parent, false);
            // Uma vez que sabemos quais views compoem o item
            // podemos fazer a busca com findViewById uma vez
            // quando criamos o item, quando o reaproveitarmos
            // reaproveitasmos a busca

            viewHolderData = new ViewHolderData(view);
            view.setTag(viewHolderData);
        } else {
            view = convertView;
            // para evitarmos o famoso NullPOinter Exception na nossa ViewHolder
            viewHolderData = (ViewHolderData) view.getTag();
        }


        Data data = (Data) getItem(position);
        viewHolderData.getId().setText(String.valueOf(data.getId()));
        viewHolderData.getTextData().setText(data.getData());
        viewHolderData.getImage().setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}
