package formmessage.br.com.xplorewifiapi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

public class WifiFilter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String action = intent.getAction();
        /*
        * Envia mensagem quando Wifip2p do dispostivo ativa ou desativa
        * */
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            Toast.makeText(context
                    ,String.format("%s", state ==  WifiP2pManager.WIFI_P2P_STATE_ENABLED ? "habilitado" : "Desabilitado")
                    ,Toast.LENGTH_SHORT)
                .show();

        }
        /*
        *   Envia uma mensagem quando eh chamado o metodo
        *   discoverPeers()
        *
        *   Quando chamamos o metodo requestPeers(), solicitamos
        *   uma lista de novos dispositivos
        * */
        else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
        /*
        *   Quando o estado da conexao WIFI muda
        * */
        else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
        /*
        *   Quando alguma informacao do dispositivo muda, como por exemplo
        *  o nome do dispositivo
        * */
        else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
    }
}
