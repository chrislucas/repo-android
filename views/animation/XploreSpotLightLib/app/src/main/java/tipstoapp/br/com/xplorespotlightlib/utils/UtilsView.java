package tipstoapp.br.com.xplorespotlightlib.utils;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by r028367 on 12/09/2017.
 */

public class UtilsView {

    public static <CustomView extends View> CustomView find(View root, @IdRes int idRes) {
        CustomView vw = (CustomView) root.findViewById(idRes);
        return vw;
    }

}
