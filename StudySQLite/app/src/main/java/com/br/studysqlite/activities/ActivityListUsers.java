package com.br.studysqlite.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.br.studysqlite.R;
import com.br.studysqlite.entities.User;

import java.util.ArrayList;

public class ActivityListUsers extends AppCompatActivity {

    public static final String CATEGORY_LU = "LIST_USERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        ArrayList<User> users = getIntent().getParcelableArrayListExtra(MainActivity.INTENT_LU);
        for(User user : users) {
            Log.i(CATEGORY_LU, String.format("ID: %d RE: %s", user.getId(), user.getName()));
        }
    }
}
