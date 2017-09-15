package tipstoapp.br.com.xplorespotlightlib;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import tipstoapp.br.com.xplorespotlightlib.adapter.MenuItemAdapter;
import tipstoapp.br.com.xplorespotlightlib.utils.UtilsView;

public class NavigationViewWithListItem extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_with_list_item);
        root = findViewById(R.id.drawer_layout_menu_with_many_itens);
        setupToolbar();
        setupNavigationView();
    }

    private void setupToolbar() {
        toolbar = UtilsView.find(root, R.id.toolbar_app_bar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }

    private void setupNavigationView() {
        drawerLayout = UtilsView.find(root, R.id.drawer_layout_menu_with_many_itens);
        ActionBarDrawerToggle actionBarDrawerToggle;
        if(drawerLayout != null) {
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open , R.string.close);
            actionBarDrawerToggle.syncState();
            navigationView = UtilsView.find(root, R.id.nav_view_menu_many_items);
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleDrawer();
                break;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true; //super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        }
        else {
            super.onBackPressed();
        }
    }

    // Abre o menu lateral
    public void toggleDrawer() {
        if (drawerLayout != null) {
            if (drawerLayout.isDrawerOpen(navigationView))
                closeDrawer();
            else
                openDrawer();
        }
    }

    // Fecha o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            if(!drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.openDrawer(navigationView);
            }
        }
    }

    protected void closeDrawer() {
        if (drawerLayout != null) {
            if(drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView);
            }
        }
    }
}
