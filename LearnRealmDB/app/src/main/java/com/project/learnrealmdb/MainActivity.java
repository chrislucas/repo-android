package com.project.learnrealmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.project.learnrealmdb.daos.UserDao;
import com.project.learnrealmdb.models.Category;
import com.project.learnrealmdb.models.Contact;
import com.project.learnrealmdb.models.User;

import java.util.Random;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * https://realm.io/docs/java/latest/
 *
 * */

public class MainActivity extends AppCompatActivity {

    private RealmResults<User> users;

    public static final String names [] = {
        "Christoffer"
        ,"Lucas"
        ,"Fernanda"
        ,"Maria"
    };
    public static final String lastName [] = {
        "Santos"
        ,"Almeida"
        ,"Oliveira"
        ,"Carvalho"
    };

    private Random random = new Random();
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDao = new UserDao(this);
    }

    public void addUser(View view) {
        int max         = names.length;
        int idxName     = random.nextInt(max);
        int idxLastName = random.nextInt(max);
        User user = new User();
        user.setName(String.format("%s %s", names[idxName], lastName[idxLastName]));

        idxName     = random.nextInt(max);
        idxLastName = random.nextInt(max);
        Contact contact = new Contact(Category.A);
        contact.setEmail(String.format("%s.%s@gmail.com.br", names[idxName], lastName[idxLastName]));
        contact.setName( names[idxName] );
        user.getContacts().add(contact);


        idxName     = random.nextInt(max);
        idxLastName = random.nextInt(max);
        contact = new Contact(Category.A);
        contact.setEmail(String.format("%s.%s@gmail.com.br", names[idxName], lastName[idxLastName]));
        contact.setName( names[idxName] );
        user.getContacts().add(contact);

        userDao.insert(user);
    }

    public  void listAllUsers(View view) {
        RealmResults<User> results = userDao.getUsers();
        if(results != null) {
            for (User user : userDao.getUsers()) {
                Log.i("USER_DAO", String.format("%s %d", user.getName(), user.getId()));
                RealmList<Contact> contacts = user.getContacts();
                if(contacts != null) {
                    for(Contact contact : contacts) {
                        Log.i("USER_DAO", String.format("%s %d", contact.getName(), contact.getId()));
                    }
                }

            }
/*
            users.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
                @Override
                public void onChange(RealmResults<User> element) {

                }
            });
*/
        }
    }
}
