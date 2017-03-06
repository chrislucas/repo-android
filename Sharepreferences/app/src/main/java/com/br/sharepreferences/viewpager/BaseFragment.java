package com.br.sharepreferences.viewpager;

import android.support.v4.app.Fragment;

/**
 * Created by C.Lucas on 06/03/2017.
 */

public abstract class BaseFragment extends Fragment {
    private String title;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
