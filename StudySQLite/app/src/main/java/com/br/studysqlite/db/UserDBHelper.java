package com.br.studysqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.studysqlite.entities.User;

/**
 * Created by r028367 on 09/01/2017.
 */

public class UserDBHelper extends AbstractDBHelper {


    protected String tableName = "Users";
    protected String [] fields;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UserDBHelper(Context context) {
        super(context);
    }

    public Object get() throws IllegalStateException {
        SQLiteDatabase db = getDbHelper().getReadableDatabase();
        String query = String.format("SELECT * FROM %s", tableName);
        Cursor cursor = getCursor(db, query);
        return get(cursor);
    }

    public Object get(Cursor cursor) {
        Object user = new Object();
        return user;
    }

}
