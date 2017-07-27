package components.com.project.fullscreenmode;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFragment = MyFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.model_layout, myFragment);
        fragmentTransaction.commit();
        Fragment fragment = fragmentManager.findFragmentByTag(MyFragment.TAG_FRAGMENT);
        if(fragment != null) {}

        UtilsSystemBar.hideImmersiveStockySystemBar(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // myFragment.toggleHideyActionBar();
                // UtilsSystemBar.hideImmersiveStockySystemBar(this);
                // UtilsSystemBar.hideImmersiveSystemBar(this)
                // ;
                if(! UtilsSystemBar.isVisible(this) ) {
                    UtilsSystemBar.showSystemBar(this);
                }
                break;
            case MotionEvent.ACTION_UP:
                final AppCompatActivity that = this;
                if(!UtilsSystemBar.isHidden(that)) {
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UtilsSystemBar.hideImmersiveStockySystemBar(that);
                        }
                    }, 200);
                }

                break;
        }
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item != null && item.getItemId() == R.id.action_fullscreen) {
            myFragment.toggleHideyActionBar();
        }
        return true;
    }
}
