package com.example.r028367.navscheme.study.swipe.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by r028367 on 16/01/2017.
 *
 * Façamos um exemplo com numero de paginas fixo
 *
 * Nosso Adapter herda de FragmentPagerAdapter, que eh
 * indicado quando o numero de Paginas que vao ser colocadas
 * no ViewPager eh pequeno.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    private static int  NUM_TABS = 3;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = SimpleFragment.constructor(position);
        return frag;
    }



    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
