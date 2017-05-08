package com.br.studycalendarview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;


public class AgendaPolitico extends AppCompatActivity
         {

    private ListView listViewEventos;
    private CalendarView calendarView;
    private DialogV4Utils dialog;
    private List<Evento> eventos;
    // essa constante sera usada para pegar a lista de eventos que vem de um filtro
    public static final String BUNDLE_EVENTOS = "BUNDLE_EVENTOS";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // TODO APAGAR ESSE LINHA
        final List<Evento> mock = mockEventos();
        final Context context = this;

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
                        Intent intent = new Intent(context, MyService.class);
                        startService(intent);
                        dialog.dismiss();
                    }
                };
            }

            @Override
            public void notifyClassImplementation(DialogFragment dialog, View view) {

            }
        };


        AdapterEvento adapterEvent = new AdapterEvento(this, android.R.layout.simple_list_item_1, mock);

        listViewEventos.setAdapter(adapterEvent);

        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout         = inflater.inflate(R.layout.dialog_detalhe_evento, null);
                if(layout != null) {
                    Evento evento       = mock.get(position);
                    TextView descricao  = (TextView) layout.findViewById(R.id.descricao);
                    descricao.setText(evento.getDescricao());
                    dialog = DialogV4Utils.newInstance(R.layout.dialog_detalhe_evento, "Definir quantidade", channelToDialogListener);
                    // DONE
                    dialog.show(getSupportFragmentManager(), "dialog_insert_item_noserial");
                    // Action: Ao clicar no botao, chamamos um servico web que vincula o evento
                    // a agenda do usuario
                }
            }
        });
    }


    private List<Evento> mockEventos() {
        List<Evento> eventos = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("dd/mm/yyyy HH:mm:ss");

        Evento eventoA = new Evento();
        Calendar calendar = Calendar.getInstance();
        eventoA.setSeguimento("Saúde");
        eventoA.setTitulo("Visita ao Hospital municipal de Belém");
        eventoA.setDescricao("Sed porttitor libero eros, et porta arcu congue id. Mauris ac metus ut dolor sollicitudin molestie id quis sem. Pellentesque tincidunt nulla nec nibh vestibulum, a aliquet arcu finibus");
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        Log.i("DATA", simpleDateFormat.format(calendar.getTime()));
        eventoA.setDataInicio(calendar);
        eventos.add(eventoA);

        Evento eventoB = new Evento();
        calendar = Calendar.getInstance();
        eventoB.setSeguimento("Saúde");
        eventoB.setTitulo("Visita ao Hospital municipal de Tatuapé");
        eventoB.setDescricao("In ornare ac lacus quis congue. Cras vitae orci sodales augue suscipit fermentum sed nec libero. Aliquam ornare libero consectetur urna posuere mollis.");
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        calendar.add(Calendar.MONTH, 3);
        Log.i("DATA", simpleDateFormat.format(calendar.getTime()));
        eventoB.setDataInicio(calendar);
        eventos.add(eventoB);


        Evento eventoC = new Evento();
        calendar = Calendar.getInstance();
        eventoC.setSeguimento("Saúde");
        eventoC.setTitulo("Visita ao Hospital municipal da Vila Guilherme");
        eventoC.setDescricao("Sed feugiat, mauris eget semper volutpat, sem neque laoreet ipsum, rutrum aliquet ligula purus vitae metus. Nunc sodales nunc vitae ipsum suscipit. ");
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        calendar.add(Calendar.MONTH, 2);
        Log.i("DATA", simpleDateFormat.format(calendar.getTime()));
        eventoC.setDataInicio(calendar);
        eventos.add(eventoC);

        return eventos;
    }

    public class Caller implements VincularMinhaAgenda.ChannelWithCaller {
        private Context context;
        public Caller(Context context) {
            this.context = context;
        }

        @Override
        public void execute() {
            // podemos
            Toast.makeText(context, "Evento vinculado", Toast.LENGTH_LONG).show();

        }
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
