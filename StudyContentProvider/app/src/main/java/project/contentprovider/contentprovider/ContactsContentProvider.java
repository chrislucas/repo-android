package project.contentprovider.contentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

/**
 * Created by r028367 on 18/05/2017.
 */

public final class ContactsContentProvider {

    /**
     * {@link android.provider.ContactsContract}
     * classe que representa o content provider dos contatos do usuario android
     * */

    public static void exploreContactsContractClass(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri authorityUri = ContactsContract.AUTHORITY_URI;

        /**
         *
         * Informacoes de um contato (Nome, telefone, _ID)
         * {@link android.provider.ContactsContract.Contacts}, {@link android.provider.ContactsContract.Contacts.Photo}
         *
         * {@link android.provider.ContactsContract.RawContacts}
         * */

        Uri uriContacts    = ContactsContract.Contacts.CONTENT_URI;
        Uri lookupUri      = ContactsContract.Contacts.getLookupUri(contentResolver, uriContacts);
        exploreLookupUri(contentResolver, lookupUri);
/*
        Uri uriContactsRaw = ContactsContract.RawContacts.CONTENT_URI;
        lookupUri = ContactsContract.RawContacts.getContactLookupUri(contentResolver, uriContactsRaw);
        exploreLookupUri(contentResolver, lookupUri);
        */

        lookupUri = ContactsContract.Data.getContactLookupUri(contentResolver, ContactsContract.Data.CONTENT_URI);
        exploreLookupUri(contentResolver, lookupUri);
    }

    private static void exploreLookupUri(ContentResolver contentResolver, Uri uri) {
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(contentResolver, uri);
        if(lookupUri != null) {
            List<String> segments = lookupUri.getPathSegments();
            Log.i("USER_INFO", lookupUri.getUserInfo());

            if(segments != null) {
                for(String segment : segments) {
                    Log.i("SEGMENT_URI", segment);
                }
            }
        }
    }


    public static void exploreContactsTable(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String [] projection    = new String [] {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
        String selection        = null;
        String args []          = null;
        String sortOrder        = null;

        Cursor cursor = contentResolver.query(uri, projection, selection, args, sortOrder);
        if(cursor != null /*&& cursor.moveToFirst()*/) {
            int i = 0, count = cursor.getCount();
            while(cursor.moveToNext()) {
                //cursor.move(i++);
                int idxName   = cursor.getColumnIndex(projection[0]);
                int idxHasPhone = cursor.getColumnIndex(projection[1]);
                String formatted = String.format("%s\n%s\n", cursor.getString(idxName), cursor.getString(idxHasPhone));
                Log.i("EXPLORE_CONTACT_TABLE", formatted);
                i++;
            }
            Log.i("I", String.valueOf(i));
        }
    }

    public static void exploreFilterContactTable(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, "a");
        Log.i("AUTHORITY", uri.getAuthority());
        Log.i("INFO_URI", uri.toString());
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
        String [] projections = {ContactsContract.Contacts.DISPLAY_NAME};
        try {
            Cursor cursor = contentResolver.query(uri, projections, null, null, sortOrder);
            if(cursor != null) {
                while (cursor.moveToNext() ) {
                    int idxName = cursor.getColumnIndex(projections[0]);
                    String formatted = String.format("%s", cursor.getString(idxName));
                    Log.i("EXPLORE_FILTER_CONTACT", formatted);
                }
            }
        } catch (SQLException sqlexp) {
            Log.e("SQLEXP_", sqlexp.getMessage());
        }

    }

}
