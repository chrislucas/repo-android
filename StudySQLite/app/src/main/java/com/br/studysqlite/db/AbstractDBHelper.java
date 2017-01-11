package com.br.studysqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by r028367 on 09/01/2017.
 */

public class AbstractDBHelper {

    protected SQLiteDatabase sqLiteDatabase;
    protected HelperDB dbHelper;
    protected Context context;

    private static String ABS_DB_HELPER_SQLEX       = "ABS_DB_HELPER_SQLEX";
    private static String ABS_DB_HELPER_ILLEGALEX   = "ABS_DB_HELPER_IllegalEx";

    public AbstractDBHelper(Context context) {
        try {
            this.dbHelper = new HelperDB(context);
        } catch (IllegalArgumentException e) {
            String message = String.format("Erro ao atualizar as tabelas. Versão atual: %d.\n Verrificar arquivo de versão", HelperDB.getCurVersionDB(context));
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }


    public HelperDB getDbHelper() {
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
            try {
                cursor = db.rawQuery(sql, args);
            } catch(SQLiteException sqlex) {
                Log.e(ABS_DB_HELPER_SQLEX, sqlex.getMessage());
            }
        }
        return cursor;
    }

    public Cursor getCursor(SQLiteDatabase db, String sql) {
        return getCursor(db, sql, null);
    }

    /*
    *
    * A classe HelperDB extende a classe SQLiteOpenHelper, que possui
    * o metodo getWritableDatabase, responsavel por abrir um canal de
    * comunicacao com a base de dados ou cria-la caso nao exista
    * */
    public void open() {
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        if(dbHelper != null) {
            try {
                dbHelper.close();
            } catch (IllegalStateException e) {
                Log.e(ABS_DB_HELPER_ILLEGALEX, e.getMessage());
            }
        }
    }
}
