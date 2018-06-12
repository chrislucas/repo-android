package com.example.r028367.navscheme.study.swipe.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r028367.navscheme.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    private int position;
    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment constructor(int pos) {
        SimpleFragment simpleFragment = new SimpleFragment();
        simpleFragment.position = pos;
        return simpleFragment;
    }

    private static SimpleFragment constructor() throws Exception {
        /*
        SimpleFragment simpleFragment = new SimpleFragment();
        simpleFragment.position = 1;
        return simpleFragment;
        */
        throw new Exception("DO NOT USE THIS METHOD");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_simple, container, false);
        TextView text = (TextView)  root.findViewById(R.id.item);
        text.setText(String.format("Tab %d", this.position + 1));
        return root;
    }

}
