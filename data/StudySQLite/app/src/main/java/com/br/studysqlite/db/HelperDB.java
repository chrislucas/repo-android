package com.br.studysqlite.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.br.studysqlite.activities.IActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by r028367 on 09/01/2017.
 */

public class HelperDB extends SQLiteOpenHelper {

    public static final String DB_NAME     = "helper.db";
    public static final String CATEGORY    = "HELPER_DB";

    private Properties properties;
    private Context context;

    private IActivity iActivity;



    public HelperDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public HelperDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public HelperDB(Context context) {
        this(context, DB_NAME, null, getVersion(context));
        setContext(context);
    }


    public HelperDB(Context context, AbstractDBHelper abstractDBHelper) {
        this(context, DB_NAME, null, getVersion(context));
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
        // defineStructureDB();
        boolean wasCreate = readProperties("create_tables.properties", "CREATE_TABLES_PROBLEM", "problemas ao tentar ler o arquivo create_tables.properties");
        if(wasCreate) {
            Log.i(CATEGORY, String.format("CREATE TABLES"));
            executeQueryInProperties(db, "CREATE");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        boolean wasDelete = deleteStructure();
        executeQueryInProperties(db, "DELETE");
        if(wasDelete) {
            boolean wasUpdate = updateStructure();
            if(wasUpdate) {
                Log.i(CATEGORY, String.format("UPDATE TABLES - versao antiga: %d, versao nova: %d", oldVersion, newVersion));
                executeQueryInProperties(db, "UPDATE");
            }
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // esse metodo soh lanca uma exception
        //super.onDowngrade(db, oldVersion, newVersion);

        boolean wasDelete = deleteStructure();
        executeQueryInProperties(db, "DELETE");
        if(wasDelete) {
            boolean wasCreate = defineStructureDB();
            if(wasCreate) {
                Log.i(CATEGORY, String.format("DOWNGRADE, CREATE TABLES"));
                executeQueryInProperties(db, "DEFINE");
            } else {
                Context context = getContext();
                if(context != null) {
                    String message = String.format("Erro ao efetuar Downgrade da versao %d para %d", newVersion, oldVersion);
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void executeQueryInProperties(SQLiteDatabase db, String category) {
        Properties properties = getProperties();
        if(properties != null) {
            db.beginTransaction();
            for(Map.Entry pair : properties.entrySet()) {
                String query = pair.getValue().toString();
                try {
                    db.execSQL(query);
                    String table = pair.getKey().toString();
                    Log.i(category, table);
                } catch(SQLException sqlexp) {
                    Log.e("SQLEXCEPTION_CREATE", sqlexp.getMessage());
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static String getPathDatabase(Context context) {
        return context.getDatabasePath(HelperDB.DB_NAME).getPath();
    }

    public static boolean existsDB(Context context) {
        File file = context.getDatabasePath(HelperDB.DB_NAME);
        return file != null ? file.exists() : false;
    }

    public static boolean checkDB(Context context) {
        SQLiteDatabase db = null;
        try {
            String path = getPathDatabase(context);
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (Exception e) {
            Log.e(CATEGORY, e.getMessage());
            return false;
        } finally {
            return db != null;
        }
    }

    public  static int getCurVersionDB(Context context) {
        SQLiteDatabase db = null;
        int version = -1;
        try {
            String path = getPathDatabase(context);
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            version = db.getVersion();
            db.close();
        } catch (Exception e) {
            Log.e(CATEGORY, e.getMessage());
        }
        return version;
    }

    /*
    *
    * Metodo responsavel por abrir uma conexao com o BD da aplicacao.
    * */

    public SQLiteDatabase getInstanceDB() {
        Context context = getContext();
        if(context != null) {
            SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
            return db;
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

    /*
    *
    * Esse metodo le o arquivo create_tables.properties. O arquivo
    * possui uma lista de queries para criar as tabelas desse aplicativo.
    * essas queries sao armazenadas num objeto Property
    *
    * @return boolean
    * Se foi possivel ler o arquivo retorna TRUE, do contrario FALSE
    * */
    private boolean defineStructureDB() {
        InputStream in;
        //in = getClass().getClassLoader().getResourceAsStream("assets/create_tables.properties");
        try {
            in = getContext().getAssets().open("create_tables.properties");
            if(in != null) {
                setProperties(new Properties());
                try {
                    getProperties().load(in);
                    return true;
                } catch (IOException e) {
                    Log.e(CATEGORY, e.getMessage());
                }
            }
        } catch (IOException e) {
            Log.e("CREATE_TABLES_PROBLEM", "problemas ao tentar ler o arquivo create_tables.properties");
        }
        return false;
    }

    private boolean updateStructure() {
        InputStream in;
        try {
            in = getContext().getAssets().open("update_tables.properties");
            if(in != null) {
                setProperties(new Properties());
                try {
                    getProperties().load(in);
                    return true;
                } catch (IOException e) {
                    Log.e(CATEGORY, e.getMessage());
                }
            }
        } catch (IOException e) {
            Log.e("UPDATE_TABLES_PROBLEM", "problemas ao tentar ler o arquivo update_tables.properties");
        }
        return false;
    }

    private boolean deleteStructure() {
        InputStream in;
        try {
            in = getContext().getAssets().open("delete_tables.properties");
            if(in != null) {
                setProperties(new Properties());
                try {
                    getProperties().load(in);
                } catch (IOException e) {
                    Log.e(CATEGORY, e.getMessage());
                }
            }
            return true;
        } catch(IOException e) {
            Log.e("DELETE_TABLES_PROBLEM", "problemas ao tentar ler o arquivo detele_tables.properties");
        }
        return false;
    }

    private boolean readProperties(String fileName, String category, String messageError) {
        InputStream in;
        try {
            in = getContext().getAssets().open(fileName);
            if(in != null) {
                setProperties(new Properties());
                try {
                    getProperties().load(in);
                } catch (IOException e) {
                    Log.e(CATEGORY, e.getMessage());
                }
            }
        } catch(IOException e) {
            Log.e(category, messageError);
        }
        return false;
    }

    private static int getVersion(Context context)  {
        InputStream in;
        int n = 0;
        try {
            AssetManager assetManager = context.getAssets();
            in = assetManager.open("versiondb.properties");
            //Class clazz = HelperDB.class;
            //in = clazz.getResource("src/main/assets/versiondb.properties").openStream();
            if(in != null) {
                Properties prop = new Properties();
                prop.load(in);
                n = Integer.parseInt(prop.getProperty("version"));
            }
        } catch (IOException e) {
            Log.e(CATEGORY, e.getMessage());
        }
        Log.i(CATEGORY + " VERSION", String.valueOf(n));
        return n;
    }


    public static boolean destroy(Context context) {
        Log.v(CATEGORY, String.format("DELETE %s", DB_NAME));
        boolean D = context.deleteDatabase(DB_NAME);
        return context!= null ?  D : false;
    }
}
