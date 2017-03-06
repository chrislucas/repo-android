package com.example.r028367.appicomon.samples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.r028367.appicomon.R;
import com.example.r028367.appicomon.samples.httprq.TwitterAPIHttpImplSample;
import com.example.r028367.appicomon.samples.httprq.TwitterAuthAPI;

import java.util.HashMap;
import java.util.Map;

public class ActivitySearchTwitterTest extends AppCompatActivity {

    private EditText search;
    private ListView result;
    private TwitterAuthAPI twitterAuth;
    private TwitterAPIHttpImplSample twitterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_twitter_test);
        search = (EditText) findViewById(R.id.search);
        result = (ListView) findViewById(R.id.list_response_data);

        // mapa com os parametros de consulta
        // chave:valor
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        // essa classe eh uma instancia de IHttpRequestSample, ela
        // eh responsavel por fazer consultas VIA HTTP
        // Para nao rodar na mesma thread que a thread respomsavel
        // por montar a interface grafica
        // as classes que implementa IHttpRequestSample tem uma inner class
        // resposnsavel por criar uma nova Thread e fazer o request.
        // Essas classes Herdan de AsyncTask
        twitterAuth = new TwitterAuthAPI("https://api.twitter.com/oauth2/token", params);
        // Criando uma instancia de AsyncTask para executar em outra Thread o request
        TwitterAuthAPI.HttpRequestTask task = new TwitterAuthAPI.HttpRequestTask(this, twitterAuth);
        /*
        * Executando outra Thread.
        * Esse request eh responsavel por pegar as credenciais para acessar a API do wiiter.
        * Este eh um exemplo de como consumir uma API e processar um JSON de retorno
        *
        * */
        task.execute();
    }

    /*
    *
    * Esse metodo eh executado quando o usuario pressionar o botao OK (id:search)
    *
    * */

    public void search(View view) {
        String url [] = {
            "https://api.twitter.com/1.1/search/tweets.json?q="
        };
        String searchText = search.getText().toString();
        if(searchText.equals("")) {
            Toast.makeText(this, "Caixa de texto em branco", Toast.LENGTH_LONG).show();
            return;
        }
        String accessToken = twitterAuth.getAccessToken();
        if(accessToken != null) {
            Map<String, String> params = new HashMap<>();
            params.put("accessToken", accessToken);
            params.put("searchText", searchText);
            TwitterAPIHttpImplSample twitterAPI = new TwitterAPIHttpImplSample(url[0], params);
            TwitterAPIHttpImplSample.HttpRequestTask task = new TwitterAPIHttpImplSample.HttpRequestTask(this, twitterAPI);
            task.execute();
        }

        else {
            Toast.makeText(this, "Access Token indefinido", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
