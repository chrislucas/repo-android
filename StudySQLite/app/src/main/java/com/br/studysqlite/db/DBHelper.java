package com.br.studysqlite.db;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by r028367 on 09/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME     = "db";
    private static final String CATEGORY    = "DBHelper";
    public static final int VERSION         = 1;

    private Properties properties;
    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        setContext(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     *
     *           Metodo abstrato da classe SQLiteOpenHelper
     *
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        * Esse metodo le um arquivo create_tables.properties com
        * as queries para criar as tabelas e adiciona
        * num objeto Properies
        *
        * */
        boolean wasCreate = createStructre();
        //
        if(wasCreate) {
            Properties properties = getProperties();
            if(properties != null) {
                for(Map.Entry pair : properties.entrySet()) {
                    String query = pair.getValue().toString();
                    try {
                        db.execSQL(query);
                    } catch(SQLException sqlexp) {
                        Log.e("SQLEXCEPTION_CREATE", sqlexp.getMessage());
                    }
                }
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static boolean existsDB(ContextWrapper context) {
        String SUFFIX_PATH = "/data/data/com.br.studysqlite/databases/";
        //File file = context.getDatabasePath(SUFFIX_PATH.concat(DB_NAME));
        //return file != null ? file.exists() : false;
        return false;
    }


    public static boolean existsDB() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (Exception e) {
            Log.e("TEST_DB_EXISTS", e.getMessage());
        }
        return db != null;
    }

    /*
    *
    * Metodo responsavel por abrir uma conexao com o BD da aplicacao.
    * */

    public SQLiteDatabase getInstanceDB() {
        Context context = getContext();
        if(context != null) {
            return context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        }
        return null;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public synchronized boolean destroyDB(Activity activity) {
        return activity != null ? activity.deleteDatabase(DB_NAME) : false;
    }

    private boolean createStructre() {
        InputStream in;
        in = getClass().getClassLoader().getResourceAsStream("assets/create_tables.properties");
        if(in != null) {
            setProperties(new Properties());
            try {
                getProperties().load(in);
                return true;
            } catch (IOException e) {
                Log.e(CATEGORY, e.getMessage());
            }
        }
        Log.e("CREATE_TABLES_PROBLEM", "problemas ao tentar ler o arquivo create_tables.properties");
        return false;
    }

    private void updateStructure() {
        return;
    }

    private void deleteStructure() {
        InputStream in;
        in = getClass().getClassLoader().getResourceAsStream("assets/detele_tables.properties");
        if(in != null) {
            setProperties(new Properties());
            try {
                getProperties().load(in);
            } catch (IOException e) {
                Log.e(CATEGORY, e.getMessage());
            }
        }
    }

    private static int getVersion() {
        InputStream in;
        in = DBHelper.class.getResourceAsStream("assets/versiondb.properties");
        int n = 0;
        if(in != null) {
            Properties prop = new Properties();
            try {
                prop.load(in);
                n = Integer.parseInt(prop.getProperty("version"));
            } catch (IOException e) {
                Log.e(CATEGORY, e.getMessage());
            }
        }
        return n;
    }
}
