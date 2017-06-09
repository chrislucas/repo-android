package com.project.getonlinehour.utils.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by r028367 on 17/05/2017.
 */

public class JSONUtils {
    /**
     * Eh possivel converter a string num jsonArray ?
     * */
    public static boolean isJSONArray(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
        } catch (Exception e) {
            Log.e("EXCP_JSONARRAY_UTILS", e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * Eh possivel converter a string num jsonObject ?
     * */
    public static boolean isJSONObject(String response) {
        try {
            JSONObject jsonArray = new JSONObject(response);
        } catch (Exception e) {
            Log.e("EXCP_JSONOBJECT_UTILS", e.getMessage());
            return false;
        }
        return true;
    }
}
