package tipstoapp.br.com.xplorespotlightlib.entities;

import android.view.View;

/**
 * Created by r028367 on 01/09/2017.
 */

public class Functionality {

    private View view;
    private String tag;

    public Functionality(View view, String tag) {
        this.view = view;
        this.tag = tag;
    }

    public View getView() {
        return view;
    }

    public String getTag() {
        return tag;
    }
}
