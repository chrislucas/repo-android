package xplore.br.com.xplorersparsearrayandroid.asynctasks;

import android.os.AsyncTask;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by r028367 on 04/10/2017.
 */
public class GetWordsAsyncTask<P, T, R extends SparseArrayCompat> extends AsyncTask<P, T, R> {

    public interface InteractWith<T> {
        public void interact(SparseArrayCompat<T> sparseArray);
    }

    private InteractWith interactWith;
    public GetWordsAsyncTask(InteractWith interactWith) {
        this.interactWith = interactWith;
    }

    @Override
    protected R doInBackground(P... params) {
        SparseArrayCompat<String> sparseArrayCompat = new SparseArrayCompat<>();
        try {
            String path = "http://www.bannedwordlist.com/lists/swearWords.txt";
            URLConnection urlConnection = new URL(path).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String in = null;
            while ((in = reader.readLine()) != null) {
                sparseArrayCompat.append(sparseArrayCompat.size(), in);
            }
        }
        catch (Exception e) {
            String message = e.getMessage() == null ? "Sistema não foi capaz de fornmar a exception"
                    : e.getMessage();
            Log.e("EXCEPTION_URL_CONN", message);
        }
        return (R) sparseArrayCompat;
    }

    @Override
    protected void onPostExecute(R sparseArray) {
        super.onPostExecute(sparseArray);
        interactWith.interact(sparseArray);
    }
}
