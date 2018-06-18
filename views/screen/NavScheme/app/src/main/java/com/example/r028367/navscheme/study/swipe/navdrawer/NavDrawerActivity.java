package com.example.r028367.navscheme.study.swipe.navdrawer;


import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.r028367.navscheme.R;
import com.example.r028367.navscheme.study.swipe.fragments.SimpleFragment;

public class NavDrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView menuList;
    private DrawerLayout drawerLayout;
    private String [] items;
    private CharSequence title, drawerTitle;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        drawerLayout    = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuList        = (ListView) findViewById(R.id.side_nav_menu);

        items = getResources().getStringArray(R.array.menu_mock);

        menuList.setAdapter(new ArrayAdapter<String>(
                 this
                ,android.R.layout.simple_list_item_1
                ,items));

        menuList.setOnItemClickListener(this);
        title = drawerTitle = getTitle();
        /*
        * Classe Filha da puta
        * ActionBarDrawerToggle na api v4 support o contrutor
        * e esse abaixo, na v7 ele muda
        * */
        drawerToggle = new ActionBarDrawerToggle(
                 this
                ,drawerLayout
                ,R.drawable.ic_drawer
                ,R.string.drawer_open
                ,R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(title);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle(drawerTitle);
                getSupportActionBar().setTitle(title);
                supportInvalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment   = SimpleFragment.constructor(position);
        FragmentManager fm  = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_drawer, fragment);
        ft.commit();
        menuList.setItemChecked(position, true);
        title = items[position];
        setTitle(title);
        drawerLayout.closeDrawer(menuList);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen =  drawerLayout.isDrawerOpen(menuList);
        //menu.findItem().setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
