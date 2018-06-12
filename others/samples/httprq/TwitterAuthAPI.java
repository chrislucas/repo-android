package com.example.r028367.appicomon.samples.httprq;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.example.r028367.appicomon.utils.httprq.AbstractHttpRequest;
import com.example.r028367.appicomon.utils.httprq.lib.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 06/01/2017.
 */

public class TwitterAuthAPI extends AbstractHttpRequest implements IHttpRequestSample {

    public TwitterAuthAPI(String url, Map<String, String> paramsRequest) {
        super(url, paramsRequest);
    }

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Object runOnBackground() {
        /*
        * Implementacao da requisicao
        * */
        String data = authenticationTwitterAPI();
        setAccessToken(data);
        return data;
    }

    private String authenticationTwitterAPI() {
        String data = null;
        try {
            String response = HttpRequest
                    .post(getUrl())
                    .connectTimeout(40000)
                    .authorization("Basic " + generateKeys())
                    .form(getParamsRequest())
                    .body();
            JSONObject jsonObject = new JSONObject(response);
            data = jsonObject.getString("access_token");
        }

        catch(JSONException jsonexp) {
            Log.e("TWITTER_AUTH_EXCP", jsonexp.getMessage());
        }

        catch(Exception e) {
            Log.e("EXCEPTION_RQ", e.getMessage());
        }

        return data;
    }

    private String generateKeys()  {
        String key ="ocEL25NbST766M5SYZlSLbcnU", secret = "xmoL0nS4YGMq6IHPawe7suKJTq3lbCo9RU5mZvlVE5hZ2ExO11";
        String token = String.format("%s:%s", key, secret);
        String encoded = Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
        return encoded;
    }

    public static class HttpRequestTask extends AsyncTask<String, Integer, List<String>> {

        private IHttpRequestSample httpRequestImpl;
        private ProgressDialog dialog;
        private Context context;

        public HttpRequestTask() {
            try {
                throw new Exception("Essa classe obriga a informar uma instancia de IHttpRequestSample");
            } catch (Exception e) {}
        }


        public HttpRequestTask(Context context, IHttpRequestSample httpRequestImpl) {
            this.context = context;
            this.httpRequestImpl = httpRequestImpl;
            this.dialog = new ProgressDialog(context);
        }

        public Context getContext() {
            return context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*
            if(dialog != null) {
                ( (Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
            */
            this.dialog.setTitle("DoInBackground");
            this.dialog.setMessage("Fazendo um processo em background");
            this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.dialog.setIndeterminate(true);
            this.dialog.setCancelable(false);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setProgressNumberFormat(null);
            this.dialog.setProgressPercentFormat(null);
            //se ocorrer algum exception no perido em que um dialog
            // estiver aberto, a activity for destroida o aplicativo
            // ira lancar um exception de vazamento de memoria
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            if(dialog != null && dialog.isShowing())
                dialog.dismiss();
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
        protected List<String> doInBackground(String... params) {
            Object data = httpRequestImpl.runOnBackground();
            return new ArrayList<>();
        }

    }

}
