package com.br.httprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebClient wc = new WebClient(this);
        String urls [] = {
                 "https://api.github.com/zen"
                ,"https://api.github.com/users/chrislucas"
                ,"https://api.github.com/users/octocat/orgs"
        };
        wc.execute("", urls[1]);
    }
}
