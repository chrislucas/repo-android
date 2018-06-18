package formmessage.br.com.xplorewifiapi.impl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by r028367 on 25/08/2017.
 */

public class DnsServiceResponseListenerImpl implements WifiP2pManager.DnsSdServiceResponseListener {
    /**
     * The requested Bonjour service response is available.
     * <p>
     * <p>This function is invoked when the device with the specified Bonjour
     * registration type returned the instance name.
     *
     * @param instanceName     instance name.<br>
     *                         e.g) "MyPrinter".
     * @param registrationType <br>
     *                         e.g) "_ipp._tcp.local."
     * @param srcDevice        source device.
     */
    @Override
    public void onDnsSdServiceAvailable(String instanceName
            , String registrationType, WifiP2pDevice srcDevice) {

    }
}
