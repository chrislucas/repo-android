package tipstoapp.br.com.xplorespotlightlib;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.JsonReader;
import android.util.Log;

import com.wooplr.spotlight.SpotlightView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

/**
 * Created by r028367 on 01/09/2017.
 */

public class Feature {

    private Activity activity;
    private List<Functionality> functionalties;
    private SpotlightView.Builder builder;
    private Context context;

    public Feature(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public Feature(Activity activity, Context context, List<Functionality> functionalties) {
        this.activity = activity;
        this.functionalties = functionalties;
        this.context = context;
    }

    public Feature(Activity activity, Context context, List<Functionality> functionalties, SpotlightView.Builder builder) {
        this.activity = activity;
        this.functionalties = functionalties;
        this.builder = builder;
        this.context = context;
    }

    public void showTipDefault() {
        SpotlightView.Builder builder = new SpotlightView.Builder(activity);
        /**
         * TODO configurar a biblioteca Spotlight
         * */
        for(Functionality functionality : functionalties) {
            /**
             * TODO adicionar as views e as tags de cada funcionalidade
             * para que a biblioteca exiba uma dica dela
             * */

        }
    }


    /**
     * Destaca uma view de uma activity
     * */
    public void showTipDefault(Functionality functionality, String headingTvText) {
        SpotlightView.Builder builder = new SpotlightView.Builder(activity);
        builder.introAnimationDuration(400)
                .enableRevealAnimation(false)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(ContextCompat.getColor(context, R.color.light_blue))
                .headingTvSize(32)
                .headingTvText(headingTvText)
                .subHeadingTvColor(ContextCompat.getColor(context, R.color.white))
                .maskColor(Color.parseColor("#dc000000"))
                .target(functionality.getView())
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(functionality.getTag())
                .show();
    }


    public static String[] getIDNewFetueare(String key, Context context) {
        String [] ids = null;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("newfeatures.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String k = jsonReader.nextName();
                Log.i("KEY", k);
            }
            jsonReader.endObject();
        } catch (IOException e) {
            String message = e.getMessage() == null ? "Sistema não foi capaz de informar qual foi a exceção" : e.getMessage();
            Log.e("EXCP_READ_FEATURES", message);
        }
        return ids;
    }


    public static int [] getResourceIds(String [] ids, Context context) {
        int [] identifiers = new int[ids.length];
        for(int i=0; i<ids.length; i++) {
            identifiers[i] = getResourceId(ids[i], context);
        }
        return identifiers;
    }

    public static int getResourceId(String id, Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(id, "id", context.getPackageName());
        return identifier;
    }

}
