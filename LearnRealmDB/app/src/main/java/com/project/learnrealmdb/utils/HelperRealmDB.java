package com.project.learnrealmdb.utils;

import android.content.Context;
import android.util.Log;

import com.project.learnrealmdb.models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmQuery;

/**
 * Created by r028367 on 11/04/2017.
 */

public class HelperRealmDB {

    private HelperRealmDB() {}

    public static Realm getInstance(Context context) {
       // RealmConfiguration realmConfiguration = new RealmConfiguration().new Builder(context).build();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        return realm;
    }


    public static int lastId(RealmModel realmModel, Context context) {
        int id = 0;
        try {
            Realm realm = HelperRealmDB.getInstance(context);
            Class clazz = realmModel.getClass();
            RealmQuery<User> query = (RealmQuery<User>) realm.where(clazz);
            Number number = query.max("id");
            if(number != null) {
                id = number.intValue() + 1;
            }

        } catch(ArrayIndexOutOfBoundsException ex) {
            Log.e("EXCEPTION_LAST_KEY", ex.getMessage());
        }
        return id;
    }

    public static long count(Context context, RealmModel r) {
        long n = getInstance(context).where(r.getClass()).count();
        return  n;
    }
}
