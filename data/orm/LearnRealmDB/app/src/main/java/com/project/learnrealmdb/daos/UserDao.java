package com.project.learnrealmdb.daos;

import android.content.Context;
import android.util.Log;

import com.project.learnrealmdb.models.Contact;
import com.project.learnrealmdb.models.User;
import com.project.learnrealmdb.utils.HelperRealmDB;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by r028367 on 11/04/2017.
 */

public class UserDao {
    private Context context;
    private Realm realm;
    public UserDao(Context context) {
        this.context = context;
        this.realm = HelperRealmDB.getInstance(context);
    }

    public Context getContext() {
        return context;
    }

    public int insertAll(List<User> users) {;
        int id = 0;
        if(users != null ) {
            for(User user : users) {
                id = insert(user);
            }
            return id;
        }
        return id;
    }

    public int getSync(User user) {
        int n = 0;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
        return n;
    }

    public int getAsync(User user) {
        int n = 0;
        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                    }
                },
                new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {

                    }
                }
                , new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {

                    }
                }
        );
        return n;
    }

    public int insert(User user) {
        int id = HelperRealmDB.lastId(user, getContext());
        user.setId(id);
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
        ContactDao contactDao = new ContactDao(getContext());

        for(Contact contact : user.getContacts())
            contactDao.insert(contact);

        Log.i("INSERT_USER", String.format("%s %d", user.getName(), user.getId()));
        return id;
    }
/*
    public int lastId() {
        int key = 0;
        try {
            Realm realm = HelperRealmDB.getInstance(getContext());
            key = realm.where(User.class).max("id").intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            Log.e("EXCEPTION_LAST_KEY", ex.getMessage());
        }
        return key;
    }
*/


    /**
     * {@link RealmResults} extends {@link java.util.AbstractList}
     * {@link java.util.AbstractList} implements {@link List]}
     * */
    public RealmResults<User> getUsers() {
        RealmResults<User> resultQuery = realm.where(User.class).findAll();
        return resultQuery;
    }


    public long count(User user) {
        long q = HelperRealmDB.count(getContext(), user);
        return q;
    }


}
