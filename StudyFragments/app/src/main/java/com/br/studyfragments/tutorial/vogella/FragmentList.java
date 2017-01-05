package com.br.studyfragments.tutorial.vogella;

import android.app.Fragment;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.br.studyfragments.R;

import java.util.Date;
import java.util.List;

/**
 * Created by r028367 on 05/01/2017.
 */

public class FragmentList extends Fragment {

    private OnItemSelectedListener itemListener;
    private List<String> dates;
    ArrayAdapter<String> adapter;

    public List<String> getDates() {
        return dates;
    }

    public void addDate(String date) {
        if(dates != null) {
            dates.add(date);
            adapter.notifyDataSetChanged();
        }
    }

    public FragmentList() {

    }

    private Button addDate;
    private ListView listDate;

    public static final String EXTRA_URL = "TAG";

    public static FragmentList newInstance(String ... args) {
        FragmentList fragment = new FragmentList();
        Bundle bundle = new Bundle();
        for(String arg : args)
            bundle.putString(EXTRA_URL, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            itemListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnItemSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        dates = new ArrayList<>();
        /*
        *
        * */
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        addDate = (Button) view.findViewById(R.id.button_list_frag);
        //addDate.setOnClickListener(clickListener);
        listDate = (ListView) view.findViewById(R.id.frag_list_date);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, dates);
        listDate.setAdapter(adapter);

        listDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*
    * android:onClick="updateDetail"
    * esse atributo que pode ser utilizado num botao,
    * nao funciona em botoes que estao em fragments. Para adicionar
    * um metodo a um botao no fragment temos que implemenar um Listener
    * */
    public void updateDetail(View view) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dt = dateFormat.format(date);
        itemListener.onItemSelected(dt);
    }

    // listener para chamar o metodo updateDetail
    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updateDetail(view);
        }
    };

    public interface OnItemSelectedListener {
        public void onItemSelected(String link);
    }

}
