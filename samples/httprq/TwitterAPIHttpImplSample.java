package com.example.r028367.appicomon.samples.httprq;


import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.r028367.appicomon.R;
import com.example.r028367.appicomon.utils.httprq.AbstractHttpRequest;
import com.example.r028367.appicomon.utils.httprq.lib.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 06/01/2017.
 */

public class TwitterAPIHttpImplSample extends AbstractHttpRequest implements IHttpRequestSample {

    public TwitterAPIHttpImplSample(String url, Map<String, String> paramsRequest) {
        super(url, paramsRequest);
    }

    public TwitterAPIHttpImplSample(String url) {
        super(url);
    }


    @Override
    public Object runOnBackground() {

        JSONObject jsonObject = null;
        try {
            String accessToken = getParamsRequest().get("accessToken");
            String searchText = getParamsRequest().get("searchText");
            String newUrl = Uri.parse(String.format("%s%s", getUrl(), searchText)).toString();
            String body = HttpRequest
                    .get(newUrl)
                    .connectTimeout(40000)
                    .authorization(String.format("Bearer %s", accessToken))
                    .body();
           jsonObject = new JSONObject(body);
        }
        catch(JSONException jsonexp) {

        }

        catch (Exception e) {
            Log.e("EXCEPTION_RQ_T", e.getMessage());
        }

        return jsonObject;
    }


    public static class HttpRequestTask extends AsyncTask<String, Integer, List<String>> {

        private IHttpRequestSample httpRequestImpl;
        private Activity context;
        private ProgressDialog dialog;

        public HttpRequestTask() {
            try {
                throw new Exception("Essa classe obriga a informar uma instancia de IHttpRequestSample");
            } catch (Exception e) {}
        }

        public HttpRequestTask(Activity context, IHttpRequestSample httpRequestImpl) {
            this.context = context;
            this.httpRequestImpl = httpRequestImpl;
        }

        public Activity getContext() {
            return context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getContext());
            dialog.setTitle("DoInBackground");
            dialog.setMessage("Fazendo um processo em background");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressNumberFormat(null);
            dialog.setProgressPercentFormat(null);
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            if(result != null) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext()
                        ,android.R.layout.simple_list_item_1
                        ,result);

                adapter.notifyDataSetChanged();

                getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListView listView = (ListView) getContext().findViewById(R.id.list_response_data);
                        listView.setAdapter(adapter);
                    }
                });
            }
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
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected List<String> doInBackground(String... params) {
            JSONObject jsonObject = (JSONObject) httpRequestImpl.runOnBackground();
            if(jsonObject != null) {
                try {
                    JSONArray array = jsonObject.getJSONArray("statuses");
                    List<String> list = new ArrayList<>();
                    for(int i=0; i<array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String text = object.getString("text");
                        JSONObject user = object.getJSONObject("user");
                        String name = user.getString("screen_name");
                        list.add(String.format("%s: %s", name, text));
                    }
                    return list;
                } catch (JSONException e) {
                   Log.e("TWITTER_API_ARRAY_EXCP", e.getMessage());
                }
            }
            return null;
        }
    }
}
