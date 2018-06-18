package xplore.br.com.xplorelistviewscrollprogrammatically;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import xplore.br.com.xplorelistviewscrollprogrammatically.data.BuildComplexDataObject;
import xplore.br.com.xplorelistviewscrollprogrammatically.data.GetWordsOnline;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.AppBaseFragment;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.ListComplexDataFragment;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.RecycleViewComplexDataFragment;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.SimpleListFragment;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
        implements AppBaseFragment.OnFragmentInteractionListener, AppBaseFragment.OnListFragmentInteractionListener {
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        Tab fragment1 = tabLayout.newTab().setText("Bad Word List");
        Tab fragment2 = tabLayout.newTab().setText("ListView Fragment");
        Tab fragment3 = tabLayout.newTab().setText("RecycleView Fragment");

        tabLayout.addTab(fragment1);
        tabLayout.addTab(fragment2);
        tabLayout.addTab(fragment3);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = SimpleListFragment.newInstance();
                        loadFragment(fragment);
                        break;
                    case 1:
                        fragment = ListComplexDataFragment.newInstance();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = RecycleViewComplexDataFragment.newInstance(3);
                        loadFragment(fragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
