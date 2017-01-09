package com.br.studysqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by r028367 on 09/01/2017.
 */

public class AbstractDBHelper {

    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    private Context context;

    public AbstractDBHelper(Context context) {
        this.dbHelper = new DBHelper(context);
        this.sqLiteDatabase = dbHelper.getWritableDatabase(); //dbHelper.getInstanceDB();

    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public Context getContext() {
        return context;
    }

    public Cursor getCursor(SQLiteDatabase db, String sql, String [] args) {
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(sql, args);
        }
        return cursor;
    }

    public Cursor getCursor(SQLiteDatabase db, String sql) {
        return getCursor(db, sql, null);
    }

    public void close() {
        if(dbHelper != null)
            dbHelper.close();
    }
}
