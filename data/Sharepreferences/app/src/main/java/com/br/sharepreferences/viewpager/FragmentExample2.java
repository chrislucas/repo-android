package com.br.sharepreferences.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.sharepreferences.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentExample2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentExample2 extends BaseFragment {

    
    public FragmentExample2() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static FragmentExample2 newInstance() {
        FragmentExample2 fragment = new FragmentExample2();
        fragment.setTitle("Fragment 2");
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example2, container, false);
    }
    
}
