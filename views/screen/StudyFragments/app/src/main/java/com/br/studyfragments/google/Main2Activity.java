package com.br.studyfragments.google;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.br.studyfragments.R;

public class Main2Activity extends FragmentActivity implements
        AnotherListFragment.OnListFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        int id_layout_subs = R.id.frag_subs_layout_another_list_fragment;
        View layout_subs = findViewById(id_layout_subs);
        if(layout_subs != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if(savedInstanceState != null)
                return;

            AnotherListFragment anotherListFragment = AnotherListFragment.newInstance(null);
            Intent intent = getIntent();
            if(intent != null)
                anotherListFragment.setArguments(intent.getExtras());

            fragmentTransaction.add(id_layout_subs, anotherListFragment);
        }
    }

    @Override
    public void onListFragmentInteraction(int pos) {
        Log.i("ListFragmentIt", String.valueOf(pos));
    }
}
