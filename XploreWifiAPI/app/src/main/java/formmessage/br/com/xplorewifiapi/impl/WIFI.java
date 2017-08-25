package formmessage.br.com.xplorewifiapi.impl;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by r028367 on 25/08/2017.
 */

public class WIFI {

    private WifiP2pManager.ActionListener actionListener;
    private WifiManager wifiManager;
    private Context context;

    public WIFI(Context ctx) {
        wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        context = ctx;
    }
}
