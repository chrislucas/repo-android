package xplore.br.com.xplorersparsearrayandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

public class MainActivity extends AppCompatActivity {


    private SparseArray<String> words;
    private GetWordsAsyncTask getWordsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class GetWordsAsyncTask<Param, Progress, Result extends SparseArray<String>>
            extends AsyncTask <Param, Progress, Result extends SparseArray<String>>  {

    }
}
