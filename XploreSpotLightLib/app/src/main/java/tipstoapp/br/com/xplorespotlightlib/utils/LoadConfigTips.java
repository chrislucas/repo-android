package tipstoapp.br.com.xplorespotlightlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import tipstoapp.br.com.xplorespotlightlib.entities.Functionality;

/**
 * Created by r028367 on 01/09/2017.
 */

public class LoadConfigTips {

    public static String assetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    public static List<Functionality> getIDNewFeature(Context context, String key) {
        List<Functionality> functionalities = new ArrayList<>();
        try {
            String jsonFile = assetJSONFile("newfeatures.json", context);
            JSONObject jsonObject = (new JSONObject(jsonFile)).getJSONObject(key);
            if(jsonObject != null) {
                JSONArray jsonArray = jsonObject.getJSONArray("views");
                for(int idx=0; idx<jsonArray.length(); idx++) {
                    JSONObject jsonView = jsonArray.getJSONObject(idx);
                    Iterator<String> keys = jsonView.keys();
                    Functionality functionality = new Functionality();
                    while (keys.hasNext()) {
                        String uniqueId = keys.next();
                        JSONObject data = jsonView.getJSONObject(uniqueId);
                        String title    = data.getString("title");
                        String text     = data.getString("text");
                        int resourceId  = getResourceId(uniqueId, context);
                        if(context instanceof  Activity) {
                            View view = ((Activity)context).findViewById(resourceId);
                            if(view != null) {
                                functionality.setView(view);
                            }
                        }
                        functionality.setTitleTip(title);
                        functionality.setTextTip(text);
                        functionality.setUniqueId(uniqueId);
                        functionalities.add(functionality);
                    }
                }
            }
        }
        catch (Exception ioex) {
            String message = ioex.getMessage() == null ? "" : ioex.getMessage();
            Log.e("IOEX", message);
        }
        return functionalities;
    }

    /**
     * Pegar os ids de views atraves das strings R.id."id_view" definidas no arquivo xml de um layout
     * */
    public static int [] getResourceIds(String [] ids, Context context) {
        int [] identifiers = new int[ids.length];
        for(int i=0; i<ids.length; i++) {
            identifiers[i] = getResourceId(ids[i], context);
        }
        return identifiers;
    }

    /**
     * Pegar o int id de uma view atraves da String R.id."id_da_view" definida
     * no arquivo xml
     *
     * @param id: String id definido no arquivo xml do layout
     *          context: Contexto da aplicacao.
     * */
    public static int getResourceId(String id, Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(id, "id", context.getPackageName());
        return identifier;
    }

}
