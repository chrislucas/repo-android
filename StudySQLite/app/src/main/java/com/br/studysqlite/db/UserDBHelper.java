package com.br.studysqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by r028367 on 09/01/2017.
 */

public class UserDBHelper extends AbstractDBHelper {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    private static final String [] FIELDS = {"_id", "name", "pwd"};

    public UserDBHelper(Context context) {
        super(context);
    }
}
