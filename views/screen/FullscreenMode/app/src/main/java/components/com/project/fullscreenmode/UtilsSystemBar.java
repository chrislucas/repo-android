package components.com.project.fullscreenmode;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by r028367 on 27/07/2017.
 */

public class UtilsSystemBar {


    public static final int HIDE_IMMERSIVE_STOCKY_SYSTEM_BAR =
             View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;


    public static final int HIDE_IMMERSIVE_SYSTEM_BAR = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE;

    public static final int SHOW_SYSTEM_BAR =
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    public static void hideImmersiveStockySystemBar(AppCompatActivity appCompatActivity) {
        int visibility = HIDE_IMMERSIVE_STOCKY_SYSTEM_BAR;
        changeSystemUIVisibility(appCompatActivity, visibility);
    }


    public static void hideImmersiveSystemBar(AppCompatActivity appCompatActivity) {
        int visibility = HIDE_IMMERSIVE_SYSTEM_BAR;
        changeSystemUIVisibility(appCompatActivity, visibility);
    }

    public static void showSystemBar(AppCompatActivity appCompatActivity) {
        int visibility = SHOW_SYSTEM_BAR;
        changeSystemUIVisibility(appCompatActivity, visibility);
    }

    public static boolean isHidden(AppCompatActivity appCompatActivity) {
        int v = appCompatActivity.getWindow().getDecorView().getSystemUiVisibility();
        return (v == HIDE_IMMERSIVE_STOCKY_SYSTEM_BAR) || (v == HIDE_IMMERSIVE_SYSTEM_BAR) || v == View.INVISIBLE;
    }

    public static boolean isVisible(AppCompatActivity appCompatActivity) {
        int v =  appCompatActivity.getWindow().getDecorView().getSystemUiVisibility();
        return v == View.VISIBLE || v == SHOW_SYSTEM_BAR;
    }

    private static void changeSystemUIVisibility(AppCompatActivity appCompatActivity, int visibility) {
        appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(visibility);
    }

}
