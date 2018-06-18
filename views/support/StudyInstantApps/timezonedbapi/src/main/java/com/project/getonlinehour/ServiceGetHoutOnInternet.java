package com.project.getonlinehour;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;

import com.project.getonlinehour.utils.http.ExecuteAsyncHttpRequest;
import com.project.getonlinehour.utils.http.GetTimeZones;

import java.util.HashMap;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceGetHoutOnInternet extends IntentService {

    public ServiceGetHoutOnInternet() {
        super("ServiceGetHoutOnInternet");
    }

    private String timeZone;
    public static final String TIME_ZONE = "TIME_ZONE";

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //final String action = intent.getAction();
            doRequestExample();
        }
    }

    public void doRequestExample() {
        String url = "http://api.timezonedb.com/v2/list-time-zone" ;
        HashMap<String, String> params = new HashMap<>();
        params.put("key", AsyncGetHourOnInternet.apiKey);
        params.put("format", "json");
        ExecuteAsyncHttpRequest<TimeZone> getTimeZones = new GetTimeZones(params, url);
        AsyncTask<Void, Void, Object> asyncTask = new AsyncGetHourOnInternet(getTimeZones);
        asyncTask.execute();
    }

}
