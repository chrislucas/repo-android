package formmessage.br.com.xplorewifiapi.impl;

import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by r028367 on 25/08/2017.
 */

public class WifiP2pActionListenerImpl implements WifiP2pManager.ActionListener {
    /**
     * The operation succeeded
     */
    @Override
    public void onSuccess() {

    }

    /**
     * The operation failed
     *
     * @param reason The reason for failure could be one of {@link WifiP2pManager#P2P_UNSUPPORTED},
     *               {@link WifiP2pManager#ERROR} or {@link WifiP2pManager#BUSY}
     */
    @Override
    public void onFailure(int reason) {

    }
}
