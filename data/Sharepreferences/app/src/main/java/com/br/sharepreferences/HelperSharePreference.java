package com.br.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by C.Lucas on 05/03/2017.
 */

public class HelperSharePreference {
    
    private static final String TABLE   = "app";
    
    private static Boolean bool;
    private static String str;
    private static Integer integer;
    
    enum ContextMode {
         MODE_PRIVATE(Context.MODE_PRIVATE)
        ,MODE_APPEND(Context.MODE_APPEND)
        ,MODE_ENABLE_WRITE_AHEAD_LOGGING(Context.MODE_ENABLE_WRITE_AHEAD_LOGGING)
        ,MODE_NO_LOCALIZED_COLLATORS(Context.MODE_NO_LOCALIZED_COLLATORS);
        int mode;
        ContextMode(int mode) {
            this.mode = mode;
        }
        
        public int getMode() {
            return this.mode;
        }
    }
    
    private static SharedPreferences getSharePreference(Context context, ContextMode contextMode) {
        SharedPreferences pref = context.getSharedPreferences(TABLE, contextMode.getMode());
        return pref;
    }
    
    private static SharedPreferences.Editor getEditor(SharedPreferences pref) {
        SharedPreferences.Editor editor = pref.edit();
        return editor;
    }
    
    public static Boolean getBool(Context context, String key) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        bool = pref.getBoolean(key, true);
        return bool;
    }
    
    public static void setBool(Context context, String key, Boolean val) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        SharedPreferences.Editor editor = getEditor(pref);
        editor.putBoolean(key, val);
        /**
         * Para salvar uma informacao usando objeto SharePreference precisado
         * de um Editor que chame o metodo commit
         * O metodo commit funciona de forma sincrona, sendo mais seguro usar
         * em aplicacoes multithread. Se 2 objetojs Sharepreference estao
         * salvando dados na Hashtable, o valor que vai se manter na TABELA
         * sera o do ultimo que executar o commit()
         * */
        editor.commit();
    }
    
    public static String getStr(Context context, String key) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        str = pref.getString(key, "");
        return str;
    }
    
    public static void setStr(Context context, String key, String value) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        SharedPreferences.Editor editor = getEditor(pref);
        editor.putString(key, value);
        editor.commit();
    }
    
    public static Integer getInteger(Context context, String key) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        integer = pref.getInt(key, Integer.MIN_VALUE);
        return integer;
    }
    
    public static void setInteger(Context context, String key, Integer val) {
        SharedPreferences pref = getSharePreference(context, ContextMode.MODE_PRIVATE);
        SharedPreferences.Editor editor = getEditor(pref);
        editor.putInt(key, val);
        editor.commit();
    }
}
