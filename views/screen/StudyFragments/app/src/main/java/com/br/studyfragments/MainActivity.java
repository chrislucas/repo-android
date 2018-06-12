package com.br.studyfragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.br.studyfragments.tutorial.vogella.FragmentDetail;
import com.br.studyfragments.tutorial.vogella.FragmentList;
import com.br.studyfragments.tutorial.vogella.OnFragmentInteractionListener;
import com.br.studyfragments.tutorial.vogella.OnItemSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
         FragmentDetail.OnFragmentInteractionListener
        ,FragmentList.OnItemSelectedListener {
    FragmentList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        FragmentDetail detail = FragmentDetail.newInstance("Argumento qualquer para FragmentDetail");

        list = FragmentList.newInstance();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container_subs_frag_detail, detail);
        ft.commit();

        ft = fm.beginTransaction();
        ft.add(R.id.container_subs_frag_list, list);
        ft.commit();
        // remover
    }

    public void removeFragment(View view) {
        FragmentManager fm = getFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.container_subs_frag_detail);
        boolean isInLayout = frag.isInLayout();

        Log.i("ISINLAYOUT", String.valueOf( ((FragmentDetail) frag).isInLayout() ) );

        if(frag != null /* && isInLayout */) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(frag);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onItemSelected(String date) {
        Log.i("ON_SELECTED_ITEM", date);
        list.addDate(date);
    }

    /*
    * Esse metodo eh chamado pelo botao no fragment FragmentList.
    * Alguns desenvolvedores indicam que para botoes em fragments
    * que sao adicionados a activities, o melhor eh deixar os metodos
    * na activity que carrega o fragment
    * */
    public void updateDetail(View view) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        String dt = dateFormat.format(date);
        this.onItemSelected(dt);
    }
}
