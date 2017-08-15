package app.com.xplore.xplorespannabletext.custom.view;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by r028367 on 15/08/2017.
 */

public class CustomClickableSpan extends ClickableSpan {


    public CustomClickableSpan() {
        super();
    }

    /**
     * Performs the click action associated with this span.
     *
     * @param widget
     */
    @Override
    public void onClick(View widget) {

    }

    /**
     * Makes the text underlined and in the link color.
     *
     * @param ds
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
    }

    /**
     * Returns "this" for most CharacterStyles, but for CharacterStyles
     * that were generated by {@link #wrap}, returns the underlying
     * CharacterStyle.
     */
    @Override
    public CharacterStyle getUnderlying() {
        return super.getUnderlying();
    }
}
