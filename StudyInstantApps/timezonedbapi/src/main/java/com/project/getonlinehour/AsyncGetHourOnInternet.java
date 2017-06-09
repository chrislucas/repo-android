package com.project.getonlinehour;

import android.os.AsyncTask;

import com.project.getonlinehour.utils.http.ExecuteAsyncHttpRequest;

/**
 * Created by r028367 on 09/06/2017.
 */

public class AsyncGetHourOnInternet extends AsyncTask<Void, Void, Object> {


    // por enquanto vai ficar aqui https://timezonedb.com/login
    public static final String apiKey = "GFZ9GD9DWFY1";

    private ExecuteAsyncHttpRequest executeAsyncHttpRequest;

    public AsyncGetHourOnInternet(ExecuteAsyncHttpRequest executeAsyncHttpRequest) {
        this.executeAsyncHttpRequest = executeAsyncHttpRequest;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public ExecuteAsyncHttpRequest getExecuteAsyncHttpRequest() {
        return executeAsyncHttpRequest;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Object doInBackground(Void... params) {
        Object answer = getExecuteAsyncHttpRequest().executeTask();
        return answer;
    }

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param object The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        executeAsyncHttpRequest.executeAfterAsyncTask();
    }

    /**
     * Runs on the UI thread after {@link #publishProgress} is invoked.
     * The specified values are the values passed to {@link #publishProgress}.
     *
     * @param values The values indicating progress.
     * @see #publishProgress
     * @see #doInBackground
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
