package com.br.sharepreferences.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.sharepreferences.R;

/**
 * A simple {@link FragmentExample1} subclass.
 * Use the {@link FragmentExample1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentExample1 extends BaseFragment {
    
    public FragmentExample1() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static FragmentExample1 newInstance() {
        FragmentExample1 fragmentExample1 = new FragmentExample1();
        fragmentExample1.setTitle("Fragment 1");
        return fragmentExample1;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example, container, false);
    }
    
}
