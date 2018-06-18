package com.br.studysqlite.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.br.studysqlite.R;
import com.br.studysqlite.entities.User;

import java.util.ArrayList;

public class ActivityListUsers extends AppCompatActivity {

    public static final String CATEGORY_LU = "LIST_USERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        boolean installed = getIntent().getBooleanExtra(MainActivity.INSTALL_APP, false);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setVisibility(installed ? View.INVISIBLE: View.VISIBLE);
        ArrayList<User> users = getIntent().getParcelableArrayListExtra(MainActivity.INTENT_LU);
        if(users != null) {
            for(User user : users) {
                Log.i(CATEGORY_LU, String.format("ID: %d RE: %s", user.getId(), user.getName()));
            }
        }
    }
}
