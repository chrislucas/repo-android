package project.contentprovider.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import project.contentprovider.helperdb.HelperDB;

/**
 * Created by r028367 on 17/05/2017.
 * https://developer.android.com/guide/topics/providers/content-provider-creating.html
 */

public class AppContentProvider extends ContentProvider {

    public static final String AUTHORITY            = "project.contentprovider.contentprovider.estudo";
    // Cria uma URI valida a partir de um String
    public static final Uri AUTHORITY_URI           = Uri.parse("content://" + AUTHORITY);
    // Uri.withAppendedPath adiciona um segmento a URI
    public static final String USER_PATH            = "user";
    public static final String FEATURE_PATH         = "feature";
    public static final String USER_FEATURE_PATH    = "userfeature";

    /**
     * Vou criar uma tabela de usuario so pra testar essa ideia de usar
     * ContentProvider
     **/
    public static class User {
        public static final String [] FIELDS_TABLE_USER = {
            "_id"
            ,"nome"
            ,"senha"
        };
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, USER_PATH);
    }

    /**
     * Vou criar uma tabela Feature que representara uma funcionalidade
     * que o App temnha
     **/
    public static class Feature {
        public static final String [] FIELDS_TABLE_FEATURE = {
             "_id"
            ,"code"
            ,"description"
        };
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, FEATURE_PATH);
    }

    /**
     * Vou criar uma tabela UserFeature que vincula um usuario a uma funcionalidade
     * */
    public static class UserFeature {
        public static final String [] FIELDS_TABLE_USER_FEATURE = {
             "id_user"
            ,"id_feature"
        };
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, USER_FEATURE_PATH);
    }

    // content provider sao definidos com a seguinte string
    // content://<authority>/<path>
    // <authority> pode ser com.br.<meuapp>.<nome-do-content-provider>/<nome-da-tabela-ou-arquivo>

    // estudo sobre a classe UriMatcher
    // https://developer.android.com/guide/topics/providers/content-provider-creating.html

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int ID_USER_PATH = 1;
    public static final int ID_FEATURE_PATH = 2;
    public static final int ID_FEATURE_USER_PATH = 3;

    enum Path {
        ID_USER_PATH(1), ID_FEATURE_PATH(2), ID_FEATURE_USER_PATH(3);
        private int path;
        Path(int path) {
            this.path = path;
        }

        public int getPath() {
            return this.path;
        }
    }

    static {
        /**
         * {@link} {@link UriMatcher} mapea as URIS de um content provider
         * e anexa um valor inteiro a cada URI, atraves do metodo addURI
         *
         * */
        // <authority>, <path>
        //URI_MATCHER.addURI("project.contentprovide.provider", "db1", 1);
        /**
         * Pattern Matches
         * Wildcards: * ou #
         * * : (path/*) casa com qualquer string de qualquer tamanho
         * # : (path/#) casa com string de numeros de qualquer tamanho
         * */
        // content://project.contentprovide.provider/db1
        // content://project.contentprovide.provider/db1/#
        //URI_MATCHER.addURI("project.contentprovide.provider", "db1/#", 2);
        URI_MATCHER.addURI(AUTHORITY, USER_PATH, Path.ID_USER_PATH.getPath());
        URI_MATCHER.addURI(AUTHORITY, USER_PATH + "/#", Path.ID_USER_PATH.getPath());
        URI_MATCHER.addURI(AUTHORITY, FEATURE_PATH,  Path.ID_FEATURE_PATH.getPath());
        URI_MATCHER.addURI(AUTHORITY, FEATURE_PATH + "/#",  Path.ID_FEATURE_PATH.getPath());
        URI_MATCHER.addURI(AUTHORITY, USER_FEATURE_PATH, Path.ID_FEATURE_USER_PATH.getPath());
        URI_MATCHER.addURI(AUTHORITY, USER_FEATURE_PATH + "/#", Path.ID_FEATURE_USER_PATH.getPath());
    }

    public void execute(Uri uri) {
        if(URI_MATCHER.match(uri) == Path.ID_USER_PATH.getPath()) {

        }
    }

    /**
     * Implement this to initialize your content provider on startup.
     * This method is called for all registered content providers on the
     * application main thread at application launch time.  It must not perform
     * lengthy operations, or application startup will be delayed.
     * <p>
     * <p>You should defer nontrivial initialization (such as opening,
     * upgrading, and scanning databases) until the content provider is used
     * (via {@link #query}, {@link #insert}, etc).  Deferred initialization
     * keeps application startup fast, avoids unnecessary work if the provider
     * turns out not to be needed, and stops database errors (such as a full
     * disk) from halting application launch.
     * <p>
     * <p>If you use SQLite, {@link SQLiteOpenHelper}
     * is a helpful utility class that makes it easy to manage databases,
     * and will automatically defer opening until first use.  If you do use
     * SQLiteOpenHelper, make sure to avoid calling
     * {@link SQLiteOpenHelper#getReadableDatabase} or
     * {@link SQLiteOpenHelper#getWritableDatabase}
     * from this method.  (Instead, override
     * {@link SQLiteOpenHelper#onOpen} to initialize the
     * database when it is first opened.)
     *
     * @return true if the provider was successfully loaded, false otherwise
     */

    private HelperDB helperDB;
    private SQLiteDatabase sqLiteDatabase;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "dbexample";

    @Override
    public boolean onCreate() {
        // inicia o content provider, como o comentario acima diz.
        helperDB = new HelperDB(getContext(), DB_NAME, null, DB_VERSION);

        return true;
    }

    /**
     * Implement this to handle query requests from clients.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * Example client call:<p>
     * <pre>// Request a specific record.
     * Cursor managedCursor = managedQuery(
     * ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 2),
     * projection,    // Which columns to return.
     * null,          // WHERE clause.
     * null,          // WHERE clause value substitution
     * People.NAME + " ASC");   // Sort order.</pre>
     * Example implementation:<p>
     * <pre>// SQLiteQueryBuilder is a helper class that creates the
     * // proper SQL syntax for us.
     * SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
     *
     * // Set the table we're querying.
     * qBuilder.setTables(DATABASE_TABLE_NAME);
     *
     * // If the query ends in a specific record number, we're
     * // being asked for a specific record, so set the
     * // WHERE clause in our query.
     * if((URI_MATCHER.match(uri)) == SPECIFIC_MESSAGE){
     * qBuilder.appendWhere("_id=" + uri.getPathLeafId());
     * }
     *
     * // Make the query.
     * Cursor c = qBuilder.query(mDb,
     * projection,
     * selection,
     * selectionArgs,
     * groupBy,
     * having,
     * sortOrder);
     * c.setNotificationUri(getContext().getContentResolver(), uri);
     * return c;</pre>
     *
     * @param uri           The URI to query. This will be the full URI sent by the client;
     *                      if the client is requesting a specific record, the URI will end in a record number
     *                      that the implementation should parse and add to a WHERE or HAVING clause, specifying
     *                      that _id value.
     * @param projection    The list of columns to put into the cursor. If
     *                      {@code null} all columns are included.
     * @param selection     A selection criteria to apply when filtering rows.
     *                      If {@code null} then all rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     *                      the values from selectionArgs, in order that they appear in the selection.
     *                      The values will be bound as Strings.
     * @param sortOrder     How the rows in the cursor should be sorted.
     *                      If {@code null} then the provider is free to define the sort order.
     * @return a Cursor or {@code null}.
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection
            , @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (  URI_MATCHER.match(uri) ) {

        }
        return null;
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * <code>vnd.android.cursor.item</code> for a single record,
     * or <code>vnd.android.cursor.dir/</code> for multiple items.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>Note that there are no permissions needed for an application to
     * access this information; if your content provider requires read and/or
     * write permissions, or is not exported, all applications can still call
     * this method regardless of their access permissions.  This allows them
     * to retrieve the MIME type for a URI when dispatching intents.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or {@code null} if there is no type.
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    /**
     * Implement this to handle requests to insert a new row.
     * As a courtesy, call {@link android.content.ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after inserting.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     *
     * @param uri    The content:// URI of the insertion request. This must not be {@code null}.
     * @param values A set of column_name/value pairs to add to the database.
     *               This must not be {@code null}.
     * @return The URI for the newly inserted item.
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * Implement this to handle requests to delete one or more rows.
     * The implementation should apply the selection clause when performing
     * deletion, allowing the operation to affect multiple rows in a directory.
     * As a courtesy, call {@link android.content.ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after deleting.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>The implementation is responsible for parsing out a row ID at the end
     * of the URI, if a specific row is being deleted. That is, the client would
     * pass in <code>content://contacts/people/22</code> and the implementation is
     * responsible for parsing the record number (22) when creating a SQL statement.
     *
     * @param uri           The full URI to query, including a row ID (if a specific record is requested).
     * @param selection     An optional restriction to apply to rows when deleting.
     * @param selectionArgs
     * @return The number of rows affected.
     * @throws android.database.SQLException
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection
            , @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * Implement this to handle requests to update one or more rows.
     * The implementation should update all rows matching the selection
     * to set the columns according to the provided values map.
     * As a courtesy, call {@link android.content.ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after updating.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     *
     * @param uri           The URI to query. This can potentially have a record ID if this
     *                      is an update request for a specific record.
     * @param values        A set of column_name/value pairs to update in the database.
     *                      This must not be {@code null}.
     * @param selection     An optional filter to match rows to update.
     * @param selectionArgs
     * @return the number of rows affected.
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values
            , @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
