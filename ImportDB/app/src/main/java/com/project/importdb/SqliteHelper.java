package com.project.importdb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by r028367 on 03/03/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private Context context;
    private String dbName;
    private Integer versionDb;

    public static final int SQLITE_NOTIFICATION = 0x11;

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.context = context;
    }


    public SqliteHelper(Context context, String dbName, int version) {
        super(context, dbName, null, version);
        this.context    = context;
        this.dbName     = dbName;
        this.versionDb  = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getWritableDatabase() {
        return this.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.getReadableDatabase();
    }

    public Cursor getCursor(SQLiteDatabase db, String sql, String[] args) {
        Cursor cursor = null;
        if(db != null) {
            try {
                cursor = db.rawQuery(sql, args);
            }
            catch(SQLiteException sqlex) {
                //Toast.makeText(context, "Erro ao tentar realizar uma Consulta", Toast.LENGTH_SHORT).show();
                NotificationUtils.createHeapUpNotification(context, new Intent(), "", "Erro ao tentar realizar uma Consulta", SQLITE_NOTIFICATION);
                Log.e("ABSTRACT_HELPER_SQLEX", sqlex.getMessage());
            }
            finally {
                return cursor;
            }
        }
        return cursor;
    }

    public Cursor getCursor(SQLiteDatabase db, String sql) {
        return getCursor(db, sql, null);
    }

}
