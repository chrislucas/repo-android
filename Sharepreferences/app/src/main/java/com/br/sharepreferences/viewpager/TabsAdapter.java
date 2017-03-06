package com.br.sharepreferences.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ConfigurationHelper;

import java.util.List;

/**
 * Created by C.Lucas on 05/03/2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    
    private Context context;
    private List<Fragment> pageFragment;
    
    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }
    
    public TabsAdapter(Context context, FragmentManager fm, List<Fragment> pageFragment) {
        super(fm);
        this.context = context;
        this.pageFragment = pageFragment;
    }
    
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = pageFragment.get(position);
        return fragment;
    }
    
    @Override
    public int getCount() {
        return pageFragment.size();
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        BaseFragment fragment = (BaseFragment) pageFragment.get(position);
        return fragment.getTitle(); // super.getPageTitle(position);
    }
}
