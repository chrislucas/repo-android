package com.br.httprequest;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by C.Lucas on 20/12/2016.
 *
 * Aprendendo sobre http://square.github.io/okhttp/
 */

public class ImplOkHttp {

    public String post(String json, String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder rqBuilder = new Request.Builder();
        rqBuilder.url(url);
        //
        MediaType mediaType = MediaType.parse("application/json, charset=utf-8");
        // criando o corpo da requesicao atraves do metodo estatico create
        // Passando como parametro o tipo de info que sera enviado e a info
        RequestBody rqBody = RequestBody.create(mediaType, json);
        // criar um request com metodo post
        rqBuilder.post(rqBody);
        // cria o nosso request
        Request rq = rqBuilder.build();
        // executar a requisica
        Response rs = httpClient.newCall(rq).execute();
        Log.i("rspost", rs.toString());
        String data = rs.body().string();
        return data;
    }

    public String get(String json, String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request rq = new Request.Builder().url(url).build();
        Response rs = httpClient.newCall(rq).execute();
        String data = rs.body().string();
        return data;
    }
}
