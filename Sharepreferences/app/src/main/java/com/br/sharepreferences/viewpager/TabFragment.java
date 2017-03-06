package com.br.sharepreferences.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.sharepreferences.R;

import java.util.List;

/**
 * Created by C.Lucas on 05/03/2017.
 */

public class TabFragment extends Fragment {
    
    private List<Fragment> pageFragment;
    
    public TabFragment() {}
    
    public static TabFragment getInstance(List<Fragment> fragments) {
        TabFragment tab = new TabFragment();
        tab.pageFragment = fragments;
        return tab;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        /**
         * Esse metodo limita o numero de Fragments que devem ser mantidos
         * em memoria, junto com o FragmentExample1 que esta sendo visualizado
         * pelo usuario.
         * Segundo a documentacao, isso eh uma forma de otimizacao de
         * carregamento de fragments na viewpager
         * */
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager(), pageFragment));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        // normal color, selecte color
        // tabLayout.setTabTextColors(R.color.white, R.color.light_gray);
        return view;
    }
}
