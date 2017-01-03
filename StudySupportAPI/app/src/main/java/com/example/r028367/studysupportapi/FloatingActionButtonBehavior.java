package com.example.r028367.studysupportapi;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Created by r028367 on 03/01/2017.
 */

public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
        //super(context, attrs);
    }


    /*
    *
    * */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        //return super.layoutDependsOn(parent, child, dependency);
        // retorna TRUE somente para instancias de SnackbarLayout
        boolean isInstance =  dependency instanceof Snackbar.SnackbarLayout;
        return isInstance;
    }

    /*
    * Esse metodo sera chamado toda a vezes que a View 'dependency' foi alterada
    * dentro do CoordinatorLayout.
    *
    * */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        //return super.onDependentViewChanged(parent, child, dependency);
        // View Snackbar -> dependency
        View snackbar = dependency;
        // quando a snackbar for mostrada, deslocaremos o botao para cima, coordenadata 'Y'
        float translationY = Math.min(0, snackbar.getTranslationY() - snackbar.getHeight());
        FloatingActionButton floatButton = child;
        floatButton.setTranslationY(translationY);
        return true;
    }
}
