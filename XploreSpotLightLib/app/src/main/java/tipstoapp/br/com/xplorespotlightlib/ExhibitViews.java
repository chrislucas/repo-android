package tipstoapp.br.com.xplorespotlightlib;

import android.app.TaskStackBuilder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import java.util.List;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;
import tipstoapp.br.com.xplorespotlightlib.utils.ConfigTips;
import tipstoapp.br.com.xplorespotlightlib.utils.LoadConfigTips;
import tipstoapp.br.com.xplorespotlightlib.utils.Tips;

public class ExhibitViews extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Tips.Callback{

    private EditText editText;
    private SwitchCompat aSwitch;

    private PreferencesManager preferencesManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    public void beforeShowingTip(View view) {
        if( view instanceof NavigationView) {
            openDrawer();
        }
    }

    @Override
    public void afterShowingTip() {
        closeDrawer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_views);
        preferencesManager = new PreferencesManager(this);
        setupToolbar();
        setupNavigationMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferencesManager.resetAll();
    }

    private void setupTipToViews() {
        List<Functionality> functionalities = LoadConfigTips.getIDNewFeature(this, getClass().getSimpleName());
        Tips tips = new Tips(this, this, functionalities);
        ConfigTips configTips = new ConfigTips();
        configTips.setColorHeadingTvColor(ContextCompat.getColor(this, R.color.light_blue));
        tips.showTips(configTips);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_app_bar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }

    private void setupNavigationMenu() {
        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout_main_menu);
        ActionBarDrawerToggle actionBarDrawerToggle;
        if(drawerLayout != null) {
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open , R.string.close);
            actionBarDrawerToggle.syncState();
            navigationView = (NavigationView) findViewById(R.id.nav_view_main_menu);
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
        int id = item.getItemId();
        switch (id) {}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                toggleDrawer();
                break;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setupTipToViews();
        return super.onPrepareOptionsMenu(menu);
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
