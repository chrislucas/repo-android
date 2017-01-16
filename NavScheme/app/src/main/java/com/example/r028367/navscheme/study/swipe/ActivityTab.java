package com.example.r028367.navscheme.study.swipe;

// import android.support.v7.app.ActionBarActivity; obsoleto
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.r028367.navscheme.R;
import com.example.r028367.navscheme.study.swipe.fragments.TabPagerAdapter;
import com.example.r028367.navscheme.study.swipe.transition.SimpleTabListener;


public class ActivityTab extends AppCompatActivity {


    private ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initSimplePagerApater();
    }

    public void initSimplePagerApater() {
        FragmentManager fm = getSupportFragmentManager(); // getFragmentManager();
        PagerAdapter pagerAdapter = new TabPagerAdapter(fm);
        viewPager.setAdapter(pagerAdapter);
        SimpleTabListener listener = new SimpleTabListener(viewPager);
        actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.addTab(actionBar.newTab().setText("Tab 1").setTabListener(listener));
        actionBar.addTab(actionBar.newTab().setText("Tab 2").setTabListener(listener));
        actionBar.addTab(actionBar.newTab().setText("Tab 3").setTabListener(listener));
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
               actionBar.setSelectedNavigationItem(position);
            }
        });
    }
}
