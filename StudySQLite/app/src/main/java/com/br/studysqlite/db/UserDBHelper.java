package com.br.studysqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.br.studysqlite.entities.User;

/**
 * Created by r028367 on 09/01/2017.
 */

public class UserDBHelper extends AbstractDBHelper {

    protected String tableName = "Users";
    // os campos serao montados dinamicamente, atraves da leitura de um arquivo properties
    //protected String [] fields;
    protected static final String [] FIELDS = {"_id", "name", "pwd", "imei"};

    private static final String CAT = "USER_DB_HELPER";

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UserDBHelper(Context context) {
        super(context);
    }

    public User get() throws IllegalStateException {
        SQLiteDatabase db = getDbHelper().getReadableDatabase();
        String query = String.format("SELECT * FROM %s", tableName);
        Cursor cursor = getCursor(db, query);
        return get(cursor);
    }

    public User get(String imei) {
        SQLiteDatabase db = getDbHelper().getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE imei = %s", tableName, imei);
        Cursor cursor = getCursor(db, query);
        return get(cursor);
    }

    public User get(String pwd, String imei) {
        SQLiteDatabase db = getDbHelper().getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE imei = %s AND pwd = %s", tableName, imei, pwd);
        Cursor cursor = getCursor(db, query);
        return get(cursor);
    }

    public User get(Cursor cursor) {
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            try {
                user = new User();
                int idxFieldId = cursor.getColumnIndex(FIELDS[0])
                        ,idxFieldName = cursor.getColumnIndex(FIELDS[1])
                        ,idxFieldPwd  = cursor.getColumnIndex(FIELDS[2])
                        ,idxFieldImei = cursor.getColumnIndex(FIELDS[3]);
                user.setId(cursor.getInt(idxFieldId));
                user.setName(cursor.getString(idxFieldName));
                user.setPwd(cursor.getString(idxFieldPwd));
                user.setImei(cursor.getString(idxFieldImei));
            } catch(Exception e) {
                Toast.makeText(getContext(), "Erro ao tentar buscar um usuario", Toast.LENGTH_LONG).show();
            }
        }
        return user;
    }

    public long insert(User user) {
        SQLiteDatabase db = getDbHelper().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("pwd", user.getPwd());
        contentValues.put("imei", user.getImei());
        long id = -1;
        if(db != null) {
            id = db.insert(tableName, null, contentValues);
            try {
                id = db.insertOrThrow(tableName, null, contentValues);
            } catch (SQLException sqlexp) {
                String message = String.format("Erro ao inserir usuario %s", sqlexp.getMessage());
                Log.e(CAT, message);
            }
            if(id == -1) {
                Log.e(CAT, "Erro ao inserir usuario");
            }
            db.close();
        }
        return id;
    }

}
