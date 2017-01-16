package com.example.r028367.navscheme.study.swipe.fragments.menus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r028367.navscheme.R;
import com.example.r028367.navscheme.study.swipe.fragments.entities.Materials;
import com.example.r028367.navscheme.study.swipe.fragments.entities.ServiceOrder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuAccessMaterials extends Fragment {

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    private Materials materials;

    public Materials getMaterials() {
        return materials;
    }

    public MenuAccessMaterials() {
        // Required empty public constructor
    }
/*
    public static MenuAccessMaterials newInstance() {
        MenuAccessMaterials fragment = new MenuAccessMaterials();
        return fragment;
    }
*/
    public static MenuAccessMaterials newInstance(Materials materials) {
        MenuAccessMaterials fragment = new MenuAccessMaterials();
        fragment.setMaterials(materials);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_access_materials, container, false);
    }

}
