package com.example.r028367.navdrawertutorial;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();
    }


    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        /*
        * Para controlar o click no Navigation View, implementar a Interface OnNavigationItemSelectedListener
        * */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                 @Override
                 public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                     int id = item.getItemId();
                     Log.i("ITEM_ID", String.valueOf(id));
                     switch (id) {
                         case R.id.home:
                             drawerLayout.closeDrawers();
                             break;
                         case R.id.settings:
                             break;
                         case R.id.trash:
                             drawerLayout.closeDrawers();
                             break;
                         case R.id.logout:
                             finish();
                             break;
                     }
                     return true;
                 }
            }
        );

        View header = navigationView.getHeaderView(0);
        //TextView email = (TextView) header.findViewById(R.id.text_view_email);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_layout_drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this
                ,drawerLayout
                ,toolbar
                ,R.string.drawer_open
                ,R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
