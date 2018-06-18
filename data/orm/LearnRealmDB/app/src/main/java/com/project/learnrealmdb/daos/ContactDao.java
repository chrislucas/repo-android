package com.project.learnrealmdb.daos;

import android.content.Context;

import com.project.learnrealmdb.models.Contact;
import com.project.learnrealmdb.utils.HelperRealmDB;

import io.realm.Realm;

/**
 * Created by r028367 on 11/04/2017.
 */

public class ContactDao {
    private Context context;
    private Realm realm;

    public ContactDao(Context context) {
        this.context = context;
        this.realm = HelperRealmDB.getInstance(context);
    }

    public Context getContext() {
        return context;
    }

    public void insert(final Contact contact) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.beginTransaction();
                Contact c   = realm.createObject(Contact.class);
                int id      = HelperRealmDB.lastId(contact, getContext());
                c.setId(id);
                c.setName(contact.getName());
                c.setCategory(contact.getCategory());
                c.setEmail(contact.getEmail());
                realm.commitTransaction();
            }
        });
    }
}
