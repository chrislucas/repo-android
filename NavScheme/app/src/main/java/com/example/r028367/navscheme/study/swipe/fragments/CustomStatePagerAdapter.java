package com.example.r028367.navscheme.study.swipe.fragments;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Created by r028367 on 16/01/2017.
 */

public class CustomStatePagerAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    private List<Fragment> fragments;
    private PagerAdapter pagerAdapter;

    public CustomStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CustomStatePagerAdapter(FragmentManager fm, List<Fragment> fragments, PagerAdapter pagerAdater) {
        super(fm);
        this.fragments = fragments;
        this.pagerAdapter = pagerAdater;
    }

    public CustomStatePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getItemPosition(Object object) {
        int i=0;
        for( ;i<getCount(); i++)
            if(getItem(i).equals(object))
                break;
        return i;
    }

    @Override
    public Fragment getItem(int position) {
        //position = getOverridePosition(position);
        return fragments.get(position);
    }

    public int getOverridePosition(int position) {
        return position %= getCount();
    }

    @Override
    public int getCount() {
        //return Integer.MAX_VALUE;
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
