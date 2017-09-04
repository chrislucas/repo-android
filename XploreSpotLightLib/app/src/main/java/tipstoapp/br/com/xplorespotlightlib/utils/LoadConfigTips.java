package tipstoapp.br.com.xplorespotlightlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
            // pega o objeto certo usando a chave
            // a chave de cada objeto é o nome Simples da classe (Activity)
            // que contem as Views que quero mapear
            JSONObject jsonObject = (new JSONObject(jsonFile)).getJSONObject(key);
            if(jsonObject != null) {
                /**
                 * Pegar as views mapeadas de uma determinada view
                 * */
                JSONArray jsonViewsParent = jsonObject.getJSONArray("views");
                for(int idx=0; idx<jsonViewsParent.length(); idx++) {
                    JSONObject jsonView = jsonViewsParent.getJSONObject(idx);
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
                                functionality.setTitleTip(title);
                                functionality.setTextTip(text);
                                functionality.setUniqueId(uniqueId);
                                functionalities.add(functionality);
                            }

                            /**
                             * Se essa view possui outras views como filhas abaixo dela
                             * */
                            if(data.has("child")) {
                                /**
                                 * Array de objetos filhos
                                 * */
                                JSONArray jsonViewsChild = data.getJSONArray("child");
                                for(int idx2=0; idx2<jsonViewsChild.length(); idx2++) {
                                    JSONObject jsonViewChild = jsonViewsChild.getJSONObject(idx2);
                                    Iterator<String> keysViewChild = jsonViewChild.keys();
                                    while (keysViewChild.hasNext()) {
                                        functionality = new Functionality();
                                        uniqueId = keysViewChild.next();
                                        if( view instanceof NavigationView ) {
                                            Menu menu = ((NavigationView) view).getMenu();
                                            int idMenuItem = getResourceId(uniqueId, context);
                                            MenuItem menuItem = menu.findItem(idMenuItem);
                                            View v = ((Activity) context).findViewById(menuItem.getItemId());
                                            if(v != null) {

                                            }
                                        }
                                        JSONObject dataChild = jsonViewChild.getJSONObject(uniqueId);
                                        title = dataChild.getString("title");
                                        text = dataChild.getString("text");
                                        functionality.setTitleTip(title);
                                        functionality.setTextTip(text);
                                        functionality.setUniqueId(uniqueId);
                                        functionalities.add(functionality);
                                    }
                                }
                            } // fim if Views child
                        } // fim context instanceof  Activity
                    }   // fim while Views parent
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
        String packageName = context.getPackageName();
        int identifier = resources.getIdentifier(id, "id", packageName);
        return identifier;
    }

}
