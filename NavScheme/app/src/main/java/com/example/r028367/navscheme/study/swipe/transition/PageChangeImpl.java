package com.example.r028367.navscheme.study.swipe.transition;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;


/**
 * Created by r028367 on 11/01/2017.
 */

public class PageChangeImpl implements ViewPager.OnPageChangeListener {

    private InteractActivity ia;

    public interface InteractActivity {
        public void passPosition(int pos);
    }


    public PageChangeImpl(InteractActivity ia) {
        this.ia = ia;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("onPageScrolled", String.format("%d %f %d", position, positionOffset, positionOffsetPixels));
        ia.passPosition(position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("onPageSelected", String.format("%d", position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("PageScrollStateChanged", String.format("%d", state));
        if(state == ViewPager.SCROLL_STATE_IDLE) {

        }
/*
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
        }
*/
    }

}
