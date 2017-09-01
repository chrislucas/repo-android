package tipstoapp.br.com.xplorespotlightlib;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

public class XploreLibFeature extends AppCompatActivity implements View.OnClickListener {


    private TextView switchAnimation, reset, resetAndPlay, changePosAndPlay, startSequence;
    private FloatingActionButton fab;

    private PreferencesManager preferencesManager;
    private boolean isRevealEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_lib_feature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        switchAnimation = (TextView) findViewById(R.id.switchAnimation);
        switchAnimation.setOnClickListener(this);
        reset = (TextView) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        resetAndPlay = (TextView) findViewById(R.id.resetAndPlay);
        resetAndPlay.setOnClickListener(this);
        changePosAndPlay = (TextView) findViewById(R.id.changePosAndPlay);
        changePosAndPlay.setOnClickListener(this);
        startSequence = (TextView) findViewById(R.id.startSequence);
        startSequence.setOnClickListener(this);
        preferencesManager = new PreferencesManager(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showIntro(new Functionality(fab, String.valueOf(fab.getId())));
            }
        }, 500);

        //Feature.getIDNewFetueare(toString(), this);
    }


    private void showIntro(Functionality functionality) {
        SpotlightView.Builder builder = new SpotlightView.Builder(this);

        builder.introAnimationDuration(400)
                .enableRevealAnimation(false)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(ContextCompat.getColor(this, R.color.light_blue))
                .headingTvSize(32)
                .headingTvText("Teste da lib")
                .subHeadingTvColor(ContextCompat.getColor(this, R.color.white))
                .maskColor(Color.parseColor("#dc000000"))
                .target(functionality.getView())
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(functionality.getTag())
                .show();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        preferencesManager.resetAll();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switchAnimation:
                break;
            case R.id.reset:
                break;
            case R.id.resetAndPlay:
                break;
            case R.id.changePosAndPlay:

                DisplayMetrics displaymetrics = new DisplayMetrics();
                this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int screenWidth = displaymetrics.widthPixels;
                int screenHeight = displaymetrics.heightPixels;

                break;
            case R.id.startSequence:
                break;
        }
    }
}
