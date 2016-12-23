package com.br.httprequest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by C.Lucas on 19/12/2016.
 */

public class WebClient extends AsyncTask<String, Void, String> {

    public static final String BASEURL = "https://api.github.com/users/octocat/orgs";

    private Activity activity;

    public WebClient(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public String postExample(String json, String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        //connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
        connection.setRequestMethod("POST");
        //
        connection.setDoInput(true);

        //PrintStream pStream = new PrintStream(connection.getOutputStream());
        //PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
        //pStream.println(json);
        //Log.i("STREAM_REQUEST", pStream.toString());
        connection.connect();
        //OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

        Log.i("RESPONSE_CODE", String.valueOf(connection.getResponseCode()));
        String rs = String.valueOf(connection.getResponseCode());
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            rs = reader(connection.getInputStream(), "UTF-8");
        }
        return rs;
    }

    public String service(String _url, String methodHttp, Map<String, String> properties) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return null;
    }

    public String getExample() throws IOException {
        URL url = new URL(BASEURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String rs = this.reader(connection.getInputStream() , "UTF-8");
        return null;
    }

    @NonNull
    private String reader(InputStream is, String charset) throws IOException{
        InputStreamReader sReader = new InputStreamReader(is, charset);
        BufferedReader buffer = new BufferedReader(sReader);
        StringBuilder builder = new StringBuilder();
        String in;
        while( (in = buffer.readLine()) != null ) {
            builder.append(in);
        }
        return builder.toString();
    }

    @Override
    protected String doInBackground(String... params)  {
        String response = null;
        try {
            response = postExample(params[0], params[1]);
        } catch(IOException ioex) {
            Log.e("ERROR_REQUEST", String.format("Message: %s, Cause: %s", ioex.getMessage(), ioex.getCause()));
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s != null) {
            super.onPostExecute(s);
            TextView text = (TextView) getActivity().findViewById(R.id.text_result);
            if(text != null)
                text.setText(s);
        }
        else {
            Log.e("ERROR_NULL_MESSAGE", "Request retornou uma mensagem Nula");
        }
    }
}
