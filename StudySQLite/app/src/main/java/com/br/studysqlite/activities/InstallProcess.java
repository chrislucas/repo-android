package com.br.studysqlite.activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.br.studysqlite.db.HelperDB;
import com.br.studysqlite.db.UserDBHelper;

/**
 * Created by r028367 on 10/01/2017.
 */

public class InstallProcess {

    public static void build(Context context) {
        UserDBHelper userDb = new UserDBHelper(context);
        userDb.open();
        //ContextWrapper contextWrapper = (ContextWrapper) context;
        //Log.i("BASE", String.format("%s %s", HelperDB.existsDB(context), HelperDB.getPathDatabase(context)));
        try {
            userDb.get();
        } catch(IllegalStateException e) {
            Log.e("InstallProcess", e.getMessage());
        }

        userDb.close();
        HelperDB.destroy(context);
    }

}
