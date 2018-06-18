package tipstoapp.br.com.xplorepatheffect;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityXplorePathEffect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }


    private static final class MyView extends View {

        private Paint paint;
        private Path path;
        private PathEffect [] arrayPathEffect;
        @ColorInt
        private int [] colors;
        /**
         * Simple constructor to use when creating a view from code.
         *
         * @param context The Context the view is running in, through which it can
         *                access the current theme, resources, etc.
         */
        public MyView(Context context) {
            super(context);
        }
    }

}
