package com.br.studycalendarview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MinhaAgenda extends AppCompatActivity {

    private ListView listViewEventos;
    private CalendarView calendarView;
    private DialogV4Utils dialog;
    private List<Evento> eventos;
    // essa constante sera usada para pegar a lista de eventos que vem de um filtro
    public static final String BUNDLE_EVENTOS = "BUNDLE_EVENTOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_agenda);

        calendarView = (CalendarView) findViewById(R.id.calendar_event);
        listViewEventos = (ListView) findViewById(R.id.events);

        if(savedInstanceState != null) {
            eventos = savedInstanceState.getParcelableArrayList(BUNDLE_EVENTOS);
        }
        /**
         * O usuario tera uma tela para selecionar o mês/ano para filtrar eventos
         * e o servico web retornara uma lista de eventos (se houver)
         * Dessa tela de filtro, redirecionamos o usuario para essa activity {@link AgendaPolitico}
         * passando via Intent a lista de eventos filtrados por mes/ano
         * */
        else {
            // aqui recuperamos a Intent
            Intent intent = getIntent();
            if(intent != null) {
                // aqui recuperamos o bundle enviado dentro da intent
                Bundle bundle = intent.getExtras();
                if(bundle != null) {
                    Set<String> keys = bundle.keySet();
                    for(String key : keys) {
                        if(key.equals(BUNDLE_EVENTOS)) {
                        /*
                        * Aqui recuperamos a lista de eventos
                        * */
                            eventos = bundle.getParcelableArrayList(BUNDLE_EVENTOS);
                            break;
                        }
                    }
                }
            }
        }

        calendarView = (CalendarView) findViewById(R.id.calendar_event);
        listViewEventos = (ListView) findViewById(R.id.events);


        final DialogV4Utils.ChannelToDialogListener channelToDialogListener = new DialogV4Utils.ChannelToDialogListener() {
            @Override
            public DialogInterface.OnClickListener onDialogNegativeClick(DialogFragment dialog) {
                return new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveClick(DialogFragment dialog) {
                return new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            }

            @Override
            public void notifyClassImplementation(DialogFragment dialog, View view) {

            }
        };

        final List<Evento> mock = new ArrayList();
        AdapterEvento adapterEvent = new AdapterEvento(this, android.R.layout.simple_list_item_1, mock);
        listViewEventos.setAdapter(adapterEvent);
        final Context context = this;
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // mesmo layout
                View layout  = inflater.inflate(R.layout.dialog_detalhe_evento, null);
                if(layout != null) {
                    Evento evento       = mock.get(position);
                    TextView descricao  = (TextView) layout.findViewById(R.id.descricao);
                    descricao.setText(evento.getDescricao());
                    dialog = DialogV4Utils.newInstance(R.layout.dialog_detalhe_evento, "Definir quantidade", channelToDialogListener);
                    // DONE
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putParcelableArrayList(BUNDLE_EVENTOS, (ArrayList<? extends Parcelable>) eventos);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            eventos = savedInstanceState.getParcelableArrayList(BUNDLE_EVENTOS);
        }
    }
}
