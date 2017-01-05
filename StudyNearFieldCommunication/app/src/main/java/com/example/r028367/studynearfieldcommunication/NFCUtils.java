package com.example.r028367.studynearfieldcommunication;

import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;

/**
 * Created by r028367 on 04/01/2017.
 *
 * https://www.sitepoint.com/learn-android-nfc-basics-building-a-simple-messenger/
 */

public class NFCUtils implements
         NfcAdapter.OnNdefPushCompleteCallback
        ,NfcAdapter.CreateNdefMessageCallback {

    public boolean checkNFC(Context context) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        return nfcAdapter != null;
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        return null;
    }
}
