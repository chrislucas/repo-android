package com.br.maps.activities;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.br.maps.fragments.MapFragment1;
import com.br.maps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.HashMap;
import java.util.Map;

import android.Manifest;


public class MapActivity1 extends AppCompatActivity implements
        MapFragment1.OnFragmentInteractionListener{

    public static HashMap<Integer, String> PERMISSION_REQUEST;

    static {
        PERMISSION_REQUEST = new HashMap<>();
        PERMISSION_REQUEST.put(1, Manifest.permission.INTERNET);
        PERMISSION_REQUEST.put(2, Manifest.permission.ACCESS_WIFI_STATE);
        PERMISSION_REQUEST.put(3, Manifest.permission.ACCESS_WIFI_STATE);
        PERMISSION_REQUEST.put(4, Manifest.permission.ACCESS_NETWORK_STATE);
        PERMISSION_REQUEST.put(5, Manifest.permission.READ_PHONE_STATE);
        PERMISSION_REQUEST.put(6, Manifest.permission.ACCESS_COARSE_LOCATION);
        PERMISSION_REQUEST.put(7, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map1);
        checkPermissions();
        if(savedInstanceState == null) {
            MapFragment1  map = MapFragment1.newInstance(this);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.model_layout, map);
            ft.commit();
            SupportMapFragment supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);
            supportMapFragment.getMapAsync(map);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private boolean checkPermissions() {
        for(Map.Entry<Integer, String> pair: PERMISSION_REQUEST.entrySet()) {
            String permission = pair.getValue();
            Integer key = pair.getKey();
            int status = ContextCompat.checkSelfPermission(this, permission);
            if(status != PackageManager.PERMISSION_GRANTED) {
                /*
                * shouldShowRequestPermissionRationale, esse metodo verifica se eh necessario
                * pedir a permissao do usuario para poder acessar um recurso do DEVICE, como
                * por exemplo, Lista de contatos, camera, Salvar em SDcard
                *
                * Se o usuario ja disse que nao para essa permissao e marcou a opcao Don't Ask
                * Again, esse metodo retornara false. Se o DEVICE tem alguma politica de nao
                * permitir esse recurso, o metodo tambem retornara false
                * */
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                }
                else {
                    /*
                    * Numa explioacao sobre o pq de pedir autorizacao para acessar um recurso
                    * precisa ser dada? Entao so precisamos pedir autorizacao
                    * */
                    ActivityCompat.requestPermissions(this, new String[] {permission}, key);
                }
            }
        }
        return true;
    }
}
