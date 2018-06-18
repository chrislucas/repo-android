package xplore.br.com.xplorersparsearrayandroid.asynctasks;

import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by r028367 on 04/10/2017.
 */

public class GetWordsAsyncTask2<P, T, R extends ArrayMap> extends AsyncTask<P, T, R> {

    public interface InteractWith<K, V> {
        public void interact(ArrayMap<K, V> arrayMap);
    }
    private InteractWith interactWith;

    public GetWordsAsyncTask2() {

    }

    public GetWordsAsyncTask2(InteractWith interactWith) {
        this.interactWith = interactWith;
    }

    @Override
    protected R doInBackground(P... ps) {
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        try {
            String path = "http://www.bannedwordlist.com/lists/swearWords.txt";
            URLConnection urlConnection = new URL(path).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String in = null;
            while ((in = reader.readLine()) != null) {
                arrayMap.put(arrayMap.size() % 10, in);
            }

        } catch (Exception e) {
            String message = e.getMessage() == null ? "Sistema não foi capaz de fornmar a exception"
                    : e.getMessage();
            Log.e("EXCEPTION_URL_CONN", message);
        }

        return (R) arrayMap;
    }

    @Override
    protected void onPostExecute(R r) {
        super.onPostExecute(r);
        if(interactWith != null)
            interactWith.interact(r);
    }
}
