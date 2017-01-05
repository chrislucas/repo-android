package com.androiddeveloper.wifip2p;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androiddeveloper.wifip2p.DeviceListFragment.DeviceActionListener;

import com.example.r028367.studynearfieldcommunication.R;



public class ActivityWifiP2P extends AppCompatActivity implements
        WifiP2pManager.ChannelListener, DeviceActionListener {

    private IntentFilter intentFilter;
    private BroadcastReceiver receiver;
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;

    private DeviceDetailFragment deviceDetailFragment;
    private DeviceListFragment deviceListFragment;

    public static final String TAG = "wifidirectdemo";

    private boolean retryChannel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifip2p);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        * Criando um intent filter e adicionado as acoes que sao esperadas
        * para verificacao numa conexao WIFI
        * */
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);

        /*
        * Uma vez tendo definido wifiP2pManager e o Channel (Canal de comunicacao WIFI)
        * podemos chamar o metodo receive de BroadcastReceive e capturar as intensts
        * */
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);

        FragmentManager fragmentManager =  getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        deviceListFragment = new DeviceListFragment();
        fragmentTransaction.add(R.id.container_subs_frag_list, deviceListFragment);
        fragmentTransaction.commit();

        fragmentTransaction = fragmentManager.beginTransaction();
        deviceDetailFragment = new DeviceDetailFragment();
        fragmentTransaction.add(R.id.container_subs_frag_detail, deviceDetailFragment);
        fragmentTransaction.commit();


        //deviceDetailFragment = (DeviceDetailFragment) fragmentManager.findFragmentById(R.id.device_details);
        //deviceListFragment = (DeviceListFragment) fragmentManager.findFragmentById(R.id.device_list_frag);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onChannelDisconnected() {
        String messageOnChannelDisconnected = "";
        if(wifiP2pManager != null && ! retryChannel) {
            retryChannel = true;
            resetData();
            messageOnChannelDisconnected = "Problema no canal de comunicacao WIFI.";
            /*
            *
            * class Looper
            *
            *
            * Method getMainLooper()
            *
            * */
            wifiP2pManager.initialize(this, getMainLooper(), this);
        } else {
            messageOnChannelDisconnected = "Problema no canal de comunicacao Permanente, Tente disconectar a interface p2p";
        }

        Toast.makeText(this, messageOnChannelDisconnected, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetails(WifiP2pDevice device) {
        deviceDetailFragment.showDetails(device);
    }

    @Override
    public void cancelDisconnect() {
        if(wifiP2pManager != null && deviceListFragment != null  ) {
            if (deviceListFragment.getDevice() != null ) {
                int status = deviceListFragment.getDevice().status;
                if(status == WifiP2pDevice.CONNECTED) {
                    disconnect();
                }

                else if(status == WifiP2pDevice.AVAILABLE || status == WifiP2pDevice.INVITED) {
                    wifiP2pManager.cancelConnect(channel, new WifiP2pManager.ActionListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(ActivityWifiP2P.this, "Conexao abortada", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int reason) {
                            Toast.makeText(ActivityWifiP2P.this, "Requisicao falhou. Abortar conexao", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }
    }

    @Override
    public void connect(WifiP2pConfig config) {
        wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {}

            @Override
            public void onFailure(int reason) {
                Log.d("ONFAILURE_CONNECT", String.valueOf(reason));
            }
        });
    }

    @Override
    public void disconnect() {
        deviceDetailFragment.resetViews();
        wifiP2pManager.removeGroup(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                deviceDetailFragment.getView().setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int reason) {
                Log.d(TAG, "Falha ao disconectaar");
            }
        });
    }


    public void resetData() {
        if(deviceListFragment != null) {
            deviceListFragment.clearPeers();
        }

        if(deviceDetailFragment != null) {
            deviceDetailFragment.resetViews();
        }
    }
}
