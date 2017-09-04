package tipstoapp.br.com.xplorespotlightlib.entities;

import android.view.View;

/**
 * Created by r028367 on 01/09/2017.
 */

public class Functionality {

    private View view;
    private String uniqueId;
    private String titleTip, textTip;

    public Functionality() {}

    public Functionality(View view, String uniqueId) {
        this.view       = view;
        this.uniqueId   = uniqueId;
    }

    public Functionality(View view, String uniqueId, String titleTip, String textTip) {
        this.view       = view;
        this.uniqueId   = uniqueId;
        this.titleTip   = titleTip;
        this.textTip    = textTip;
    }

    public View getView() {
        return view;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getTitleTip() {
        return titleTip;
    }

    public String getTextTip() {
        return textTip;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setTitleTip(String titleTip) {
        this.titleTip = titleTip;
    }

    public void setTextTip(String textTip) {
        this.textTip = textTip;
    }
}
