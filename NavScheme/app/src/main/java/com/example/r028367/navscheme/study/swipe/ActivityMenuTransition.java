package com.example.r028367.navscheme.study.swipe;


import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.r028367.navscheme.study.swipe.fragments.entities.AbstractFeatures;
import com.example.r028367.navscheme.study.swipe.fragments.entities.FactoryMapFeatures;
import com.example.r028367.navscheme.study.swipe.transition.PageChangeImpl;
import com.example.r028367.navscheme.study.swipe.transition.ZoomOutPageTransforner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityMenuTransition extends AppCompatActivity
        implements AdapterView.OnItemClickListener, PageChangeImpl.InteractActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> listFragmentsFeatures;

    private ListView menuList;
    private DrawerLayout drawerLayout;
    private String [] itemsMenuFragment;
    private CharSequence title, drawerTitle;
    private ActionBarDrawerToggle drawerToggle;

    /*
    * Mapa<Codigo funcionalitade, Funcionalidade>
    * Esse mapa vincula uma funcionalidade a seu Codigo
    * Esse eh um exemplo que pretendo usar como solucao num app
    * de um cliente
    * */
    Map<String, AbstractFeatures> featuresSystem;

    /*
        * Mapa<Funcionalidade, Tela de acao>
        *     Esse mapa vincula uma funcionalidade a
        *     uma tela de acao (Fragment). Essa e a tela inicial
        *     que da Acesso a Acao(Funcionalidade)
        *     Por exemplo
        *     MaterialsFeatures eh uma especificacao de AbstracFeature
        *     ou seja MaterialsFeatures representa uma funcionalidade do sistema
        *     Map<MaterialsFeatures, MenuAccessMaterials>
        *         Uma funcionalidade esta atrelada a sua tela de acao, dessa
        *         forma podemos montar dinamicamente um menu que o usuario
        *
        *
        * */
    private Map<AbstractFeatures, Fragment> fragmentsSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transition);
        FactoryMapFeatures factory = new FactoryMapFeatures();
        // mapa com todas as Classes que implementam toda a regra de negocio
        // Exemplo: A classe que implementa funcionalidades para materiais
        // A classe que
        // funcionalidades do sistema
        featuresSystem  = factory.getFeatures();
        // telas que dao acesso ao sistema
        fragmentsSystem = factory.getViewsFeatureSystem();

        String [] codes = {"130", "132", "134"};
        /*
        *
        * Criar a lista de Fragments que sera usada para o efeito
        * de Swipe.
        *
        * Essa lista de fragmentsSystem eh criada conforme as funcionalistades que
        * o usuario tera acesso
        * */
        createSwipeMenu(codes);
        /*
        * ViewPager: https://developer.android.com/reference/android/support/v4/view/ViewPager.html
        * */
        viewPager = (ViewPager) findViewById(R.id.slider);
        viewPager.setPageTransformer(true, new ZoomOutPageTransforner());
        FragmentManager fm = getSupportFragmentManager();
        //
        pagerAdapter = new CustomStatePagerAdapter(fm, listFragmentsFeatures);
        //
        viewPager.setAdapter(pagerAdapter);
        //
        viewPager.addOnPageChangeListener(new PageChangeImpl(this));
        /*
        * Configuracao do menu lateral
        *
        * */
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        drawerLayout    = (DrawerLayout) findViewById(R.id.activity_menu_transition);
        menuList        = (ListView) findViewById(R.id.side_nav_menu);
        menuList.setAdapter(new ArrayAdapter<String>(
            this
            ,android.R.layout.simple_list_item_1
            , itemsMenuFragment)
        );

        // Se o usuario tiver acesso a pelo menos uma funcionalidade
        // a descricao dela sera o titulo da pagina
        if(itemsMenuFragment[0] != null)
            setTitle(itemsMenuFragment[0]);

        menuList.setOnItemClickListener(this);
        // Metodo implementado pela Activity para pegar o titulo
        title = drawerTitle = getTitle();
        /*
        * Classe Filha da puta
        * ActionBarDrawerToggle na api v4 support o contrutor
        * e esse abaixo, na v7 ele muda
        *
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
                actionBar.setTitle(title);
                //supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(title);
                //supportInvalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }


    private void createSwipeMenu(String [] codes) {
        listFragmentsFeatures = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        for(String code : codes) {
            if(featuresSystem.containsKey(code)) {
                AbstractFeatures feature = featuresSystem.get(code);
                descriptions.add(feature.getDescription());
                Fragment fragment = fragmentsSystem.get(feature);
                listFragmentsFeatures.add(fragment);
            }
        }

        itemsMenuFragment = new String[descriptions.size()];
        for(int i = 0; i< itemsMenuFragment.length; i++)
            itemsMenuFragment[i] = descriptions.get(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment currentFragment = listFragmentsFeatures.get(position);
        String description = "";
        AbstractFeatures feature = null;
        for(Map.Entry<AbstractFeatures, Fragment> pair : fragmentsSystem.entrySet()) {
            Fragment fragment = pair.getValue();
            boolean eq = fragment.getClass().getName().equals(currentFragment.getClass().getName());
            boolean eq2 = fragment.equals(currentFragment);
            if ( eq ) {
                feature     = pair.getKey();
                description = feature.getDescription();
                title = description;
                setTitle(title);
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

    @Override
    public void passPosition(int pos) {
        setTitle(itemsMenuFragment[pos]);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPrepareOptionsPanel(view, menu);
    }
}
