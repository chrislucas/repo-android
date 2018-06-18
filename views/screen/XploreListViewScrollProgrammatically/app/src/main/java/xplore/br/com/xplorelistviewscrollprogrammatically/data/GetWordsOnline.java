package xplore.br.com.xplorelistviewscrollprogrammatically.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 26/09/2017.
 */

public class GetWordsOnline<Params, Progress, Result extends List<String>> extends AsyncTask<Params, Progress, Result> {

    public static final String URL_PATH = "http://www.bannedwordlist.com/lists/swearWords.txt";

    public interface Callback {
        public void beforeProcess();
        public void afterProcess(List<String> result);
    }

    private GetWordsOnline.Callback callback;

    public GetWordsOnline(GetWordsOnline.Callback callback) {
        this.callback = callback;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.beforeProcess();
    }

    @Override
    protected Result doInBackground(Params[] params) {
        List<String> result = new ArrayList<>();
        try {
            URL url = new URL(URL_PATH);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ( (line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }
        catch (Exception e) {
            String message = e.getMessage() == null ? "S.O não foi capaz de informar a exceção" : e.getMessage();
            Log.e("EXCP_URL_CONNECTION", message);
        }

        return (Result) result;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        callback.afterProcess(result);
    }
}
