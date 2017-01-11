package com.br.studysqlite.activities;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.br.studysqlite.R;
import com.br.studysqlite.db.HelperDB;
import com.br.studysqlite.db.UserDBHelper;
import com.br.studysqlite.entities.User;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IActivity {

    public static final String INTENT_LU = "LIST_USERS";
    public static final String INSTALL_APP = "INSTALL_APP";

    private int statusProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Processes.checkIfDbVersionChanged(this);
        boolean check = HelperDB.checkDB(this);
        if( ! check ) {
            Processes.build(this);
        }

        else {
            Log.i("MainActivity", String.valueOf(HelperDB.getCurVersionDB(this)));
            // teste besta
            Processes.insertTest(this);
            Processes.findTest(this);
            Intent intent = new Intent(this, ActivityListUsers.class);
            // teste besta
            intent.putExtra(INSTALL_APP, check);
            startActivity(intent);
        }
    }

    @Override
    public void communication(int status) {
        this.statusProcess = status;
    }

    private void passListParcelable() {
        ArrayList<User> users = new ArrayList<>();
        Random random = new Random();
        users.add(new User(random.nextInt(100), "Alonso"));
        users.add(new User(random.nextInt(100), "Fernando"));
        users.add(new User(random.nextInt(100), "Matias"));
        users.add(new User(random.nextInt(100), "Aloisio"));

        //Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList(INTENT_LU,  users);
        //bundle.putSerializable("Teste", new ArrayList<Activities>());

        // soh funciona na API 16
        //startActivity(intent, bundle);

        // Fato interessante, a class Intent nao tem um metodo putSerializableArrayListExtra
        // porem a classe bundle tem, soh que para eu passar um bundle de uma activity
        // para outra preciso usar o metodo startActivity(Intent, Bundle) que soh existe na api 16

        Intent intent = new Intent(this, ActivityListUsers.class);
        // teste besta
        intent.putExtra(INSTALL_APP, true);
        intent.putParcelableArrayListExtra(INTENT_LU, users);
        startActivity(intent);
    }

}
