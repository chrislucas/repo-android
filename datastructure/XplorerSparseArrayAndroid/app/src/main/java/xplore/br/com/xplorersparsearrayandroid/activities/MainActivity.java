package xplore.br.com.xplorersparsearrayandroid.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xplore.br.com.xplorersparsearrayandroid.R;
import xplore.br.com.xplorersparsearrayandroid.fragments.ListBadWordFragment;
import xplore.br.com.xplorersparsearrayandroid.fragments.OnListFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private ListBadWordFragment listBadWordFragment;

    @Override
    public void onListFragmentInteraction() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        listBadWordFragment = ListBadWordFragment.newInstance(this);
        ft.replace(R.id.frame, listBadWordFragment);
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
