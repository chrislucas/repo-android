package com.example.r028367.appicomon.samples.httprq;

import android.os.AsyncTask;

import com.example.r028367.appicomon.utils.httprq.AbstractHttpRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 06/01/2017.
 */

public class GitHubHttpImplSample extends AbstractHttpRequest implements IHttpRequestSample {

    public GitHubHttpImplSample(String url, Map<String, String> paramsRequest) {
        super(url, paramsRequest);
    }

    public GitHubHttpImplSample(String url) {
        super(url);
    }

    @Override
    public Object runOnBackground() {
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


