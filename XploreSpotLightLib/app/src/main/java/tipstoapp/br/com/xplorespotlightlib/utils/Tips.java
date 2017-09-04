package tipstoapp.br.com.xplorespotlightlib.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.utils.SpotlightListener;
import com.wooplr.spotlight.utils.SpotlightSequence;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

/**
 * Created by r028367 on 04/09/2017.
 */

public class Tips {

    private Activity activity;
    private List<Functionality> functionalities;
    private SpotlightView.Builder builder;
    private LinkedHashSet<String> uniqueIds;
    private Queue<Data> queue;

    public static class Data {
        private SpotlightView.Builder builder;
        private String uniqueId;

        public Data(SpotlightView.Builder builder, String uniqueId) {
            this.builder    = builder;
            this.uniqueId   = uniqueId;
        }

        public SpotlightView.Builder getBuilder() {
            return builder;
        }

        public void setBuilder(SpotlightView.Builder builder) {
            this.builder = builder;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }
    }


    public interface Callback {
        public void beforeShowingTip(View view);
        public void afterShowingTip();
    }

    private Tips.Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Tips(Activity activity, List<Functionality> functionalties) {
        this.activity           = activity;
        this.functionalities    = functionalties;
        this.queue              = new LinkedList<>();
    }


    public Tips(Callback callback, Activity activity, List<Functionality> functionalties) {
        this.callback           = callback;
        this.activity           = activity;
        this.functionalities    = functionalties;
        this.queue              = new LinkedList<>();
    }

    public Tips(Activity activity, List<Functionality> functionalities, SpotlightView.Builder builder) {
        this.activity           = activity;
        this.functionalities    = functionalities;
        this.builder            = builder;
        this.queue              = new LinkedList<>();
    }

    /**
     * Destaca uma view de uma activity
     *
     * */
    public SpotlightView.Builder showTips(Functionality functionality, ConfigTips configTips) {
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
                .usageId(functionality.getUniqueId());
        return builder;
    }

    public void showDefaultSequencyOfTips(ConfigTips configTips) {
        uniqueIds = new LinkedHashSet<>();
        SpotlightConfig spotlightConfig = configTips.defaultSpotlightConfig();
        SpotlightSequence spotlightSequence = SpotlightSequence.getInstance(activity, spotlightConfig);
        for(Functionality functionality : functionalities) {
            uniqueIds.add(functionality.getUniqueId());
            spotlightConfig.setHeadingTvText(functionality.getTitleTip());
            spotlightConfig.setSubHeadingTvText(functionality.getTextTip());
            spotlightConfig.setMaskColor(Color.parseColor("#dc000000"));
            addSequencyOfTips(spotlightSequence, functionality);
        }

        spotlightSequence.startSequence();
    }

    public void addSequencyOfTips(SpotlightSequence spotlightSequence, Functionality functionality) {
        View view = functionality.getView();
        if( ViewCompat.isAttachedToWindow( view )) {
           spotlightSequence.addSpotlight(functionality.getView()
                    , functionality.getTitleTip(), functionality.getTextTip(), functionality.getUniqueId());
        }
    }

    public void showTips(ConfigTips configTips) {
        uniqueIds = new LinkedHashSet<>();
        for(Functionality functionality : functionalities) {
            if(functionality.getView() != null) {
                String uniqueId = functionality.getUniqueId();
                uniqueIds.add(uniqueId);
                configTips.setHeadingTvText(functionality.getTitleTip());
                configTips.setSubHeadingTvText(functionality.getTextTip());
                configTips.setMaskColor(Color.parseColor("#dc000000"));
                final SpotlightView.Builder builder = showTips(functionality, configTips);
                builder.setListener(new SpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        if ( uniqueIds.contains(s) ) {
                            dequeueObjAnimation();
                            callback.afterShowingTip();
                        }
                    }
                });
                Data data = new Data(builder, uniqueId);
                queue.add(data);
            }
            else {
                Log.e("SHOW_TIPS_VIEW_NULL", functionality.getUniqueId());
            }
        }
        dequeueObjAnimation();
    }

    private void dequeueObjAnimation() {
        if( ! queue.isEmpty()) {
            Data data = queue.poll();
            SpotlightView.Builder builder = data.getBuilder();
            if(builder != null) {
                SpotlightView spotlightView = builder.build();
                if(spotlightView != null) {
                    if(callback != null) {
                        int id = LoadConfigTips.getResourceId(data.getUniqueId(), activity.getApplicationContext());
                        View view = activity.findViewById(id);
                        callback.beforeShowingTip(view);
                    }
                }
                builder.show();
            }
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Fim da animacao", Toast.LENGTH_LONG).show();
        }
    }
}
