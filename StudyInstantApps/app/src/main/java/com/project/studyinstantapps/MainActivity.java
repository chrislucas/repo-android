package com.project.studyinstantapps;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.project.getonlinehour.ServiceGetHoutOnInternet;


/**
 *
 * Support Android App Links
 *
 * Em projetos Instant App a navegação entre activities e feita atraves de URLS, instant apps
 * nao tem permissao de chamar activities diretamente
 *
 * Ambos, android app e instant app devem implementar o recurso de Android App Links para
 * a navegacao entre activities. Esse recusro foi implementado na versao 6.0 android
 *
 * Recursos nao suportados
 * Servicos em background
 *
 * Broadcast Receivers no arquivo manifest
 *
 * Acesso externo a content providers
 *
 * Content Providers
 *
 * Push notification atualmente nao funciona
 *
 * Instant App nao acessam Identificadores do dispositivo, como o IMEI
 *
 * Instant App sao baseados em modulos. Antes de aprender como criar instant apps
 * devemos aprender sobre como criar modulos de aplicacoes no android
 * https://developer.android.com/studio/projects/android-library.html
 * */

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = new Intent(this, ServiceGetHoutOnInternet.class);
        startService(intent);
    }

}
