package com.androiddeveloper.wifip2p;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by r028367 on 04/01/2017. https://developer.android.com/guide/topics/connectivity/wifip2p.html
 *
 * A api de wifip2p do android permite que os dispositivos
 * com hardware adequado conectem-se 1 a 1 atraves da conexao
 * via wifi.
 *
 * Em relacao ao uso do blueooth, conexoes via WIFI direto
 * podem ser feitas numa distancia maior e a taxa de transferencia
 * tambem eh maior
 */

/*
*
* A API WifiP2p consiste de:
*   Metodos que permitem encontrar, requisitar e conectar a dispositivos
*   sao definidos na classe WifiP2pManager
*
*   Usar a Intent para me informar de eventos como queda de conexão
*   ou um novo dispositivo na rede
*
* */


public class WifiDirectBroadcastReceiver extends BroadcastReceiver {


    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private Activity activity;

    public WifiDirectBroadcastReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, Activity activity) {
        super();
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // FILTRO

        /*
        * Envia mensagem quando Wifip2p do dispostivo ativa ou desativa
        * */
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
            // nao precisa explicar
            if(state ==  WifiP2pManager.WIFI_P2P_STATE_ENABLED) {

            }
        }
        /*
        *   Envia uma mensagem quando eh chamado o metodo
        *   discoverPeers()
        *
        *   Quando chamamos o metodo requestPeers(), solicitamos
        *   uma lista de novos dispositivos
        * */
        else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

        }
        /*
        *   Quando o estado da conexao WIFI muda
        * */
        else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        }
        /*
        *   Quando alguma informacao do dispositivo muda, como por exemplo
        *  o nome do dispositivo
        * */
        else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

        }
    }
}
