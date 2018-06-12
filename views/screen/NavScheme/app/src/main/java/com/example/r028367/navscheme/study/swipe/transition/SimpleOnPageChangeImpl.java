package com.example.r028367.navscheme.study.swipe.transition;

import android.support.v4.view.ViewPager;

/**
 * Created by r028367 on 11/01/2017.
 */

public class SimpleOnPageChangeImpl extends ViewPager.SimpleOnPageChangeListener {
    public SimpleOnPageChangeImpl() {
        super();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
    }
}
