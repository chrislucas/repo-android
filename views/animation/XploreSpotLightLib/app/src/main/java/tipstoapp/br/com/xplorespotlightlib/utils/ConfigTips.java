package tipstoapp.br.com.xplorespotlightlib.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.wooplr.spotlight.SpotlightConfig;

/**
 * Created by r028367 on 04/09/2017.
 */

public class ConfigTips {
    @ColorRes
    private static final int DEFAULT_BLACK_COLOR        = Color.parseColor("#aa000000");
    @ColorRes
    private static final int DEFAULT_TANSL_BLACK_COLOR  = Color.parseColor("#dc000000");
    @ColorRes
    private static final int DEFAULT_WHITE_COLOR        = Color.parseColor("#ffffffff");

    @ColorInt
    private int colorHeadingTvColor         = DEFAULT_WHITE_COLOR;
    @ColorInt
    private int colorSubHeadingTvColor      = DEFAULT_WHITE_COLOR;
    @ColorInt
    private int maskColor                   = DEFAULT_BLACK_COLOR;
    @ColorInt
    private int lineAndArcColor             = DEFAULT_WHITE_COLOR;
    private int fadeinTextDuration          = 400;      // default
    private CharSequence headingTvText      = "Titulo Padrão";
    private CharSequence subHeadingTvText   = "SubTitulo Padrão";
    private long introAnimationDuration     = 400;      // default
    private long lineAnimDuration           = 400;      // default

    private int headingTvSize               = 15;
    private int subHeadingTvSize            = 13;

    private boolean enableRevealAnimation = true
            , performClick = true
            , dismissOnTouch = true
            ,dismissOnBackPress = true
            ,enableDismissAfterShown = false;

    public int getColorHeadingTvColor() {
        return colorHeadingTvColor;
    }

    public void setColorHeadingTvColor(int colorHeadingTvColor) {
        this.colorHeadingTvColor = colorHeadingTvColor;
    }

    public int getColorSubHeadingTvColor() {
        return colorSubHeadingTvColor;
    }

    public void setColorSubHeadingTvColor(int colorSubHeadingTvColor) {
        this.colorSubHeadingTvColor = colorSubHeadingTvColor;
    }

    public int getFadeinTextDuration() {
        return fadeinTextDuration;
    }

    public void setFadeinTextDuration(int fadeinTextDuration) {
        this.fadeinTextDuration = fadeinTextDuration;
    }

    public CharSequence getHeadingTvText() {
        return headingTvText;
    }

    public void setHeadingTvText(CharSequence headingTvText) {
        this.headingTvText = headingTvText;
    }

    public CharSequence getSubHeadingTvText() {
        return subHeadingTvText;
    }

    public void setSubHeadingTvText(CharSequence subHeadingTvText) {
        this.subHeadingTvText = subHeadingTvText;
    }

    public int getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public int getLineAndArcColor() {
        return lineAndArcColor;
    }

    public void setLineAndArcColor(int lineAndArcColor) {
        this.lineAndArcColor = lineAndArcColor;
    }

    public long getIntroAnimationDuration() {
        return introAnimationDuration;
    }

    public void setIntroAnimationDuration(long introAnimationDuration) {
        this.introAnimationDuration = introAnimationDuration;
    }

    public int getHeadingTvSize() {
        return headingTvSize;
    }

    public void setHeadingTvSize(int headingTvSize) {
        this.headingTvSize = headingTvSize;
    }

    public int getSubHeadingTvSize() {
        return subHeadingTvSize;
    }

    public void setSubHeadingTvSize(int subHeadingTvSize) {
        this.subHeadingTvSize = subHeadingTvSize;
    }


    public long getLineAnimDuration() {
        return lineAnimDuration;
    }

    public void setLineAnimDuration(long lineAnimDuration) {
        this.lineAnimDuration = lineAnimDuration;
    }

    public boolean isEnableRevealAnimation() {
        return enableRevealAnimation;
    }

    public void setEnableRevealAnimation(boolean enableRevealAnimation) {
        this.enableRevealAnimation = enableRevealAnimation;
    }

    public boolean isPerformClick() {
        return performClick;
    }

    public void setPerformClick(boolean performClick) {
        this.performClick = performClick;
    }

    public boolean isDismissOnTouch() {
        return dismissOnTouch;
    }

    public void setDismissOnTouch(boolean dismissOnTouch) {
        this.dismissOnTouch = dismissOnTouch;
    }

    public boolean isDismissOnBackPress() {
        return dismissOnBackPress;
    }

    public void setDismissOnBackPress(boolean dismissOnBackPress) {
        this.dismissOnBackPress = dismissOnBackPress;
    }

    public boolean isEnableDismissAfterShown() {
        return enableDismissAfterShown;
    }

    public void setEnableDismissAfterShown(boolean enableDismissAfterShown) {
        this.enableDismissAfterShown = enableDismissAfterShown;
    }


    public SpotlightConfig defaultSpotlightConfig() {
        SpotlightConfig spotlightConfig = new SpotlightConfig();
        spotlightConfig.setHeadingTvText(headingTvText);
        spotlightConfig.setSubHeadingTvText(subHeadingTvText);

        spotlightConfig.setHeadingTvSize(headingTvSize);
        spotlightConfig.setSubHeadingTvSize(subHeadingTvSize);

        spotlightConfig.setHeadingTvColor(colorHeadingTvColor);
        spotlightConfig.setSubHeadingTvColor(colorSubHeadingTvColor);
        spotlightConfig.setMaskColor(maskColor);
        spotlightConfig.setLineAndArcColor(lineAndArcColor);

        spotlightConfig.setFadingTextDuration(fadeinTextDuration);
        spotlightConfig.setLineAnimationDuration(lineAnimDuration);

        spotlightConfig.setDismissOnBackpress(dismissOnBackPress);
        spotlightConfig.setDismissOnTouch(dismissOnTouch);
        spotlightConfig.setPerformClick(performClick);
        spotlightConfig.setRevealAnimationEnabled(enableRevealAnimation);

        return spotlightConfig;
    }
}
