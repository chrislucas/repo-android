package com.br.studycalendarview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by C.Lucas on 20/03/2017.
 */

public class AdapterEvento extends ArrayAdapter<Evento> {
    
    private Context context;
    
    public AdapterEvento(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
    }
    
    private class ViewHolder {
        private TextView titulo, dataInicio, dataFim, segmento;
        public ViewHolder() {}
    }
    
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        Evento evento = getItem(position);
        if(evento != null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(convertView == null) {
                convertView = inflater.inflate(R.layout.adapter_list_eventos, null);
                viewHolder = new ViewHolder();
                viewHolder.titulo = (TextView) convertView.findViewById(R.id.titulo);
                viewHolder.dataInicio = (TextView) convertView.findViewById(R.id.data_inicio);
                viewHolder.segmento = (TextView) convertView.findViewById(R.id.segmento);
                convertView.setTag(viewHolder);
            }

            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.titulo.setText(evento.getTitulo());
            viewHolder.segmento.setText(evento.getSeguimento());
            viewHolder.dataInicio.setText(evento.getDataInicialFormatada());

        }
        return  convertView;
    }
}
