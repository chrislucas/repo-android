package com.project.studycontentprovider.contentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.util.Log;

/**
 * Created by r028367 on 18/05/2017.
 */

public class TelephonyContentProvider {

    /**
     *
     * {@link android.provider.Telephony}
     *
     * */

    public static void explore (Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Uri uri = Telephony.Sms.Conversations.CONTENT_URI;
            String querySmsConversation =  Telephony.Sms.Conversations.CONTENT_URI.getQuery();

            if(querySmsConversation != null) {
                Log.i("CONVERSATION_QUERY_SMS", querySmsConversation);
            }
            else if(uri.getQuery() != null) {
                Log.i("QUERY_URI", uri.getQuery());
            }
            if(uri != null) {
                Log.i("URI_TELEPHONY", uri.toString());
            }
        }
    }
}
