package com.example.r028367.appicomon.samples.httprq;

import android.os.AsyncTask;
import android.util.Log;

import com.example.r028367.appicomon.utils.httprq.AbstractHttpRequest;
import com.example.r028367.appicomon.utils.httprq.lib.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 06/01/2017.
 */

public class GoogleApiHttpImplSample extends AbstractHttpRequest implements IHttpRequestSample {

    public static final String JGONEXCP_REQUEST = "TAG_JGONEXCP_REQUEST";

    public GoogleApiHttpImplSample(String url) {
        super(url);
    }

    public GoogleApiHttpImplSample(String url, Map<String, String> paramsRequest) {
        super(url, paramsRequest);
    }

    public String getUrl() {
        return super.getUrl();
    }

    public String requestTest() {
        HttpRequest httpRQ = HttpRequest.get(getUrl());
        String body = httpRQ.body();
        return body;
    }


    @Override
    public JSONObject runOnBackground() {
        String response = requestTest();
        JSONObject object = null;
        try {
            object = new JSONObject(response);
            JSONArray array = object.names();
            array.length();
            for(int i=0; i<array.length(); i++) {
                Log.i("TAG_EXEC_ITERATOR_ARRAY", array.get(i).toString());
            }
            Iterator keys = object.keys();
            while(keys.hasNext()) {
                keys.next();
            }

        } catch(JSONException jsonexcp) {
            Log.e(JGONEXCP_REQUEST, jsonexcp.getMessage());
        }
        return object;
    }

    public String processRequest(HttpRequest httpRequest) {
        return null;
    }


    public static class HttpRequestTask extends AsyncTask<String, Integer, List<String>> {

        private IHttpRequestSample httpRequestImpl;

        public HttpRequestTask() {
            try {
                throw new Exception("Essa classe obriga a informar uma instancia de IHttpRequestSample");
            } catch (Exception e) {}
        }

        public HttpRequestTask(IHttpRequestSample httpRequestImpl) {
            this.httpRequestImpl = httpRequestImpl;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(List<String> strings) {
            super.onCancelled(strings);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected List<String> doInBackground(String... params) {
            httpRequestImpl.runOnBackground();
            return null;
        }
    }

}
