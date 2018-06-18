package formmessage.br.com.xplorewifiapi.impl;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by r028367 on 25/08/2017.
 */

public class ConnectionInfoListenerimpl implements WifiP2pManager.ConnectionInfoListener {

    private WifiP2pInfo info;

    public ConnectionInfoListenerimpl(WifiP2pInfo info) {
        this.info = info;
    }

    /**
     * The requested connection info is available
     *
     * @param info Wi-Fi p2p connection info
     */
    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

    }
}
