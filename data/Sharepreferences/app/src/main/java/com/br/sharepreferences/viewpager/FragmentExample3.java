package com.br.sharepreferences.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.sharepreferences.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExample3 extends BaseFragment {
    
    
    public FragmentExample3() {
        // Required empty public constructor
    }
    
    public static FragmentExample3 newInstance() {
        FragmentExample3 fragment = new FragmentExample3();
        fragment.setTitle("Fragment 3");
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example3, container, false);
    }
    
}
