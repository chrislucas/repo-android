package tipstoapp.br.com.xplorespotlightlib;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import java.util.Random;

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


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
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
                .headingTvSize(20)
                .headingTvText("Teste da lib")
                .subHeadingTvColor(ContextCompat.getColor(this, R.color.white))
                .subHeadingTvSize(15)
                .subHeadingTvText("Lib marota")
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
        preferencesManager.resetAll();
        switch (v.getId()) {
            case R.id.switchAnimation:
                isRevealEnabled = ! isRevealEnabled;
                switchAnimation.setText(isRevealEnabled ? "Revelar" : "FadeIn");
                break;
            case R.id.reset:
                break;
            case R.id.resetAndPlay:
                handlerResetAndPlay.postDelayed(runnableResetAndPlay, 400);
                break;
            case R.id.changePosAndPlay:
                DisplayMetrics displaymetrics = new DisplayMetrics();
                this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int screenWidth = displaymetrics.widthPixels;
                int screenHeight = displaymetrics.heightPixels;

                Random random = new Random();
                int x = random.nextInt( (screenWidth - Utils.dpToPx(16)) - 16) + 16;
                int y = random.nextInt( (screenHeight - Utils.dpToPx(16)) - 16) + 16;

                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                layoutParams.setMargins(Utils.dpToPx(16), Utils.dpToPx(16), x, y);
                fab.setLayoutParams(layoutParams);

                break;
            case R.id.startSequence:
                handlerStartSequence.postDelayed(runnableStartSequence, 400);
                break;
        }
    }

    private Handler handlerResetAndPlay     = new Handler();
    private Handler handlerStartSequence    = new Handler();
    private Runnable runnableResetAndPlay   = new Runnable() {
        @Override
        public void run() {
            showIntro(new Functionality(fab, String.valueOf(fab.getId())));
        }
    };

    private static final String INTRO_CARD = "fab_intro";
    private static final String INTRO_SWITCH = "switch_intro";
    private static final String INTRO_RESET = "reset_intro";
    private static final String INTRO_REPEAT = "repeat_intro";
    private static final String INTRO_CHANGE_POSITION = "change_position_intro";
    private static final String INTRO_SEQUENCE = "sequence_intro";

    private Runnable runnableStartSequence = new Runnable() {
        @Override
        public void run() {
            SpotlightConfig spotlightConfig = null;
            SpotlightSequence spotlightSequence = SpotlightSequence
                    .getInstance(XploreLibFeature.this, spotlightConfig);
            spotlightSequence.addSpotlight(switchAnimation, "", "", INTRO_SWITCH);
            spotlightSequence.addSpotlight(reset, "", "", INTRO_RESET);
            spotlightSequence.addSpotlight(resetAndPlay, "", "", INTRO_REPEAT);
            spotlightSequence.addSpotlight(changePosAndPlay, "", "", INTRO_CHANGE_POSITION);
            spotlightSequence.addSpotlight(startSequence, "", "", INTRO_SEQUENCE);
            spotlightSequence.addSpotlight(fab, "", "", INTRO_CARD);
            spotlightSequence.startSequence();
        }
    };
}
