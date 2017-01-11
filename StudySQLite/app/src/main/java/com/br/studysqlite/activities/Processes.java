package com.br.studysqlite.activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.br.studysqlite.db.HelperDB;
import com.br.studysqlite.db.UserDBHelper;
import com.br.studysqlite.entities.User;

/**
 * Created by r028367 on 10/01/2017.
 */

public class Processes {

    public static void build(Context context) {
        UserDBHelper userDb = new UserDBHelper(context);
        userDb.open();
        //ContextWrapper contextWrapper = (ContextWrapper) context;
        //Log.i("BASE", String.format("%s %s", HelperDB.existsDB(context), HelperDB.getPathDatabase(context)));
        userDb.close();
        //HelperDB.destroy(context);
    }

    public static void checkIfDbVersionChanged(Context context) {
        UserDBHelper userDb = new UserDBHelper(context);
        userDb.open();
        userDb.close();
    }

    public static void insertTest(Context context) {
        UserDBHelper userDb = new UserDBHelper(context);
        try {
            User user = new User();
            user.setName("Nome qualquer");
            user.setPwd("uahsusahsauhas");
            user.setImei("123456");
            userDb.insert(user);
            User user1 = userDb.get("123456");
        } catch(IllegalStateException e) {
            Log.e("Processes", e.getMessage());
        }
    }

    public static User findTest(Context context) {
        UserDBHelper userDb = new UserDBHelper(context);
        User user = userDb.get("123456");
        return user;
    }

}
