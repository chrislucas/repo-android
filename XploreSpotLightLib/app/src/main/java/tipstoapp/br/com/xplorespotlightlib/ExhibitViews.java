package tipstoapp.br.com.xplorespotlightlib;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import java.util.List;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;
import tipstoapp.br.com.xplorespotlightlib.utils.ConfigTips;
import tipstoapp.br.com.xplorespotlightlib.utils.LoadConfigTips;
import tipstoapp.br.com.xplorespotlightlib.utils.Tips;

public class ExhibitViews extends AppCompatActivity {

    private EditText editText;
    private SwitchCompat aSwitch;

    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_views);

        preferencesManager = new PreferencesManager(this);

        List<Functionality> functionalities = LoadConfigTips.getIDNewFeature(this, getClass().getSimpleName());
        Tips tips = new Tips(this, functionalities);
        ConfigTips configTips = new ConfigTips();
        configTips.setColorHeadingTvColor(ContextCompat.getColor(this, R.color.light_blue));
        tips.showDefaultSequencyOfTips(configTips);
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
}
