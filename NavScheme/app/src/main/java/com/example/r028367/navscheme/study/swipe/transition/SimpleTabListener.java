package com.example.r028367.navscheme.study.swipe.transition;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;


/**
 * Created by r028367 on 16/01/2017.
 */

public class SimpleTabListener implements ActionBar.TabListener {

    private ViewPager viewPager;
/*
    public SimpleTabListener(){
        try {
            throw new Exception("DO NOT USE THIS CONSTRUCTOR");
        } catch(Exception e) {
            Log.e("SimpleTabListener", e.getMessage());
        }
    }
*/
    public SimpleTabListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        int pos = tab.getPosition();
        this.viewPager.setCurrentItem(pos);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
