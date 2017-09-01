package tipstoapp.br.com.xplorespotlightlib;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

public class MainActivity extends AppCompatActivity {


    private PreferencesManager mPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.switchview);
        Functionality functionality = new Functionality(view, String.valueOf(view.getId()));

        /**
         * A classe {@link PreferencesManager} eh composta por um atributo {@link SharedPreferences}
         *
         * Ler sober
         * {@link android.preference.PreferenceManager}
         * https://developer.android.com/reference/android/preference/PreferenceManager.html
         * */
        mPreferencesManager = new PreferencesManager(this);
        showIntro(functionality);
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
        super.onStop();
        mPreferencesManager.resetAll();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
