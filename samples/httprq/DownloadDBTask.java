package com.example.r028367.appicomon.samples.httprq;

import android.os.AsyncTask;

import com.example.r028367.appicomon.entities.daos.Entity;

import java.util.List;

/**
 * Created by r028367 on 12/01/2017.
 */

public class DownloadDBTask extends AsyncTask<List<String>, Integer, List<Entity>> {

    @Override
    protected List<Entity> doInBackground(List<String>... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Entity> entities) {
        super.onPostExecute(entities);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(List<Entity> entities) {
        super.onCancelled(entities);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
