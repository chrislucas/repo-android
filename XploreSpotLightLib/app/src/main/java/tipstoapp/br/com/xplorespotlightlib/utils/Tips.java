package tipstoapp.br.com.xplorespotlightlib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.utils.SpotlightSequence;

import java.util.List;

import tipstoapp.br.com.xplorespotlightlib.XploreLibFeature;
import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

/**
 * Created by r028367 on 04/09/2017.
 */

public class Tips {

    private Activity activity;
    private List<Functionality> functionalities;
    private SpotlightView.Builder builder;

    public Tips(Activity activity, List<Functionality> functionalties) {
        this.activity = activity;
        this.functionalities = functionalties;
    }

    public Tips(Activity activity, List<Functionality> functionalities, SpotlightView.Builder builder) {
        this.activity = activity;
        this.functionalities = functionalities;
        this.builder = builder;
    }

    public void showTipDefault() {
        SpotlightView.Builder builder = new SpotlightView.Builder(activity);
        /**
         * TODO configurar a biblioteca Spotlight
         * */
        for(Functionality functionality : functionalities) {
            /**
             * TODO adicionar as views e as tags de cada funcionalidade
             * para que a biblioteca exiba uma dica dela
             * */

        }
    }

    /**
     * Destaca uma view de uma activity
     *
     * */
    public void showTips(Functionality functionality, ConfigTips configTips) {
        SpotlightView.Builder builder = new SpotlightView.Builder(activity);
        builder.introAnimationDuration(configTips.getIntroAnimationDuration())
                .enableRevealAnimation(configTips.isEnableRevealAnimation())
                .performClick(configTips.isPerformClick())
                .fadeinTextDuration(configTips.getFadeinTextDuration())
                .headingTvColor(configTips.getColorHeadingTvColor())        // ContextCompat.getColor(context, R.color.light_blue)
                .headingTvSize(configTips.getHeadingTvSize())
                .headingTvText(configTips.getHeadingTvText())
                .subHeadingTvColor(configTips.getColorSubHeadingTvColor())  // ContextCompat.getColor(context, R.color.white)
                .subHeadingTvSize(configTips.getSubHeadingTvSize())
                .subHeadingTvText(configTips.getSubHeadingTvText())
                .maskColor(configTips.getMaskColor())                       // Color.parseColor("#dc000000")
                .target(functionality.getView())
                .lineAnimDuration(configTips.getLineAnimDuration())
                .lineAndArcColor(configTips.getLineAndArcColor())           // Color.parseColor("#eb273f")
                .dismissOnTouch(configTips.isDismissOnTouch())
                .dismissOnBackPress(configTips.isDismissOnBackPress())
                .enableDismissAfterShown(configTips.isEnableDismissAfterShown())
                .usageId(functionality.getUniqueId())
                .show();
    }

    public void showDefaultSequencyOfTips(ConfigTips configTips) {
        SpotlightConfig spotlightConfig = configTips.defaultSpotlightConfig();
        SpotlightSequence spotlightSequence = SpotlightSequence.getInstance(activity, spotlightConfig);
        for(Functionality functionality : functionalities) {
            spotlightConfig.setHeadingTvText(functionality.getTitleTip());
            spotlightConfig.setSubHeadingTvText(functionality.getTextTip());
            spotlightConfig.setMaskColor(Color.parseColor("#dc000000"));
            addSequencyOfTips(spotlightSequence, functionality);
        }
        spotlightSequence.startSequence();
    }

    public void addSequencyOfTips(SpotlightSequence spotlightSequence, Functionality functionality) {
        spotlightSequence.addSpotlight(functionality.getView()
                , functionality.getTitleTip(), functionality.getTextTip(), functionality.getUniqueId());
    }

    public void showTips(ConfigTips configTips) {
        for(Functionality functionality : functionalities) {
            configTips.setHeadingTvText(functionality.getTitleTip());
            configTips.setSubHeadingTvText(functionality.getTextTip());
            showTips(functionality, configTips);
        }
    }
}
