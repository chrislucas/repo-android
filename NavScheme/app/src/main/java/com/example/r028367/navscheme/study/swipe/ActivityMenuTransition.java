package com.example.r028367.navscheme.study.swipe;


import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
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
import com.example.r028367.navscheme.study.swipe.fragments.CustomStatePagerAdapter;
import com.example.r028367.navscheme.study.swipe.fragments.SimpleFragment;
import com.example.r028367.navscheme.study.swipe.fragments.entities.AbstractFeature;
import com.example.r028367.navscheme.study.swipe.fragments.entities.Materials;
import com.example.r028367.navscheme.study.swipe.fragments.entities.ServiceOrder;
import com.example.r028367.navscheme.study.swipe.fragments.entities.SyncServiceOrder;
import com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessMaterials;
import com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessServiceOrders;
import com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessSyncServiceOrder;
import com.example.r028367.navscheme.study.swipe.transition.PageChangeImpl;
import com.example.r028367.navscheme.study.swipe.transition.ZoomOutPageTransforner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ActivityMenuTransition extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> listFeatures;

    private ListView menuList;
    private DrawerLayout drawerLayout;
    private String [] items;
    private CharSequence title, drawerTitle;
    private ActionBarDrawerToggle drawerToggle;


    private Map<String, AbstractFeature> features;
    private Map<AbstractFeature, Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transition);

        features = new HashMap<>();
        fragments = new HashMap<>();
        Materials materials = new Materials("Materias", "1");
        ServiceOrder serviceOrder = new ServiceOrder("Ordens de Servicço", "2");
        SyncServiceOrder syncServiceOrder = new SyncServiceOrder("Sincronizar O.S", "3");

        features.put("1", materials);
        features.put("2", serviceOrder);
        features.put("3", syncServiceOrder);

        fragments.put(materials, MenuAccessMaterials.newInstance(materials));
        fragments.put(serviceOrder, MenuAccessServiceOrders.newInstance(serviceOrder));
        fragments.put(syncServiceOrder, MenuAccessSyncServiceOrder.newInstance(syncServiceOrder));

        String [] codes = {"1", "3"};

        createSwipeMenu(codes);

        viewPager = (ViewPager) findViewById(R.id.slider);
        viewPager.setPageTransformer(true, new ZoomOutPageTransforner());

        FragmentManager fm = getSupportFragmentManager();
        pagerAdapter = new CustomStatePagerAdapter(fm, listFeatures);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new PageChangeImpl());

        /*
        * Configuracao do menu lateral
        *
        * */
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        drawerLayout    = (DrawerLayout) findViewById(R.id.activity_menu_transition);
        menuList        = (ListView) findViewById(R.id.side_nav_menu);

        menuList.setAdapter(new ArrayAdapter<String>(
            this
            ,android.R.layout.simple_list_item_1
            ,items)
        );

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


    private void createSwipeMenu(String [] codes) {
        listFeatures = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        for(String code : codes) {
            AbstractFeature feature = features.get(code);
            descriptions.add(feature.getDescription());

            Fragment fragment = fragments.get(feature);
            listFeatures.add(fragment);
        }

        items = new String[descriptions.size()];
        for(int i=0; i<items.length; i++)
            items[i] = descriptions.get(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = listFeatures.get(position);
        String description = "";
        AbstractFeature  feature = null;
        for(Map.Entry<AbstractFeature, Fragment> pair : fragments.entrySet()) {
            if ( pair.getKey().equals(fragment) ) {
                feature = pair.getKey();
                description = feature.getDescription();
                break;
            }
        }

        /*
        if(fragment instanceof MenuAccessMaterials) {
            description = ((MenuAccessServiceOrders) fragment).getServiceOrder().getDescription();
        }
        else if(fragment instanceof MenuAccessMaterials) {
            description = ((MenuAccessMaterials) fragment).getMaterials().getDescription();
        }

        else if(fragment instanceof MenuAccessSyncServiceOrder) {
            description = ((MenuAccessSyncServiceOrder) fragment ).getSyncServiceOrder().getDescription();
        }
        */
        viewPager.setCurrentItem(position);
        menuList.setItemChecked(position, true);
        title = items[position];
        setTitle(description);
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
