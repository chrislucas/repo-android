package com.br.sharepreferences.viewpager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.br.sharepreferences.R;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */
        
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FragmentExample1.newInstance());
        fragmentList.add(FragmentExample2.newInstance());
        fragmentList.add(FragmentExample3.newInstance());
        
        TabFragment tabFragment = TabFragment.getInstance(fragmentList);
        
        ft.add(R.id.layout_to_replace, tabFragment);
        ft.commit();
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    
}
