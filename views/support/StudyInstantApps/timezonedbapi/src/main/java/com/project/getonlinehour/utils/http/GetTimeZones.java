package com.project.getonlinehour.utils.http;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.project.getonlinehour.TimeZone;
import com.project.getonlinehour.utils.json.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by r028367 on 09/06/2017.
 *
 * API https://timezonedb.com/references/list-time-zone
 */

public class GetTimeZones implements ExecuteAsyncHttpRequest {

    private HashMap<String, String> params;
    private String url;

    public GetTimeZones(HashMap<String, String> params, String url) {
        this.params = params;
        this.url = url;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public List<TimeZone> executeTask() {
        List<TimeZone> timeZoneList = new ArrayList<>();
        try {
            /**
             * https://stackoverflow.com/questions/13240530/free-rest-api-to-get-current-time-as-string-timezone-irrelevant
             * https://timezonedb.com/api
             * http://www.geonames.org/export/ws-overview.html
             * */
            HttpRequest request = HttpRequest.post(getUrl(), getParams(), true);
            String response = request.body();
            if(JSONUtils.isJSONArray(response)) {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    getDataTimeZone(jsonObject);
                }
            }
            else if(JSONUtils.isJSONObject(response)) {
                JSONObject jsonObject = new JSONObject(response);
                if(jsonObject.has("zones")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("zones");
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        TimeZone timeZone = getDataTimeZone(o);
                        timeZoneList.add(timeZone);
                        if(timeZone.getCountryCode().equals("BR")) {
                            //"dd/MM/yyyy HH:mm:ss"
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                            simpleDateFormat.applyPattern("dd/MM/yyyy hh:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            long T = timeZone.getTimestamp();
                            long O = calendar.getTimeInMillis();
                            calendar.setTimeInMillis(timeZone.getTimestamp());
                            // https://timezonedb.com/unix-time
                            Log.i("TIMESTAMP", String.format("%s - %s \n %d %d", timeZone.getZoneName()
                                    , simpleDateFormat.format(calendar.getTime())
                                    , T
                                    , O
                            ));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            Log.e("EXCEPTION_REQUEST", e.getMessage());
        }
        return timeZoneList;
    }

    private TimeZone getDataTimeZone(JSONObject jsonObject) throws JSONException {
        long timestamp      = jsonObject.getLong("timestamp");
        String countryCode  = jsonObject.getString("countryCode");
        String countryName  = jsonObject.getString("countryName");
        String zoneName     = jsonObject.getString("zoneName");
        long gmtOffset      = jsonObject.getLong("gmtOffset");
        TimeZone timeZone = new TimeZone( (timestamp + gmtOffset) * 1000, gmtOffset, countryCode, countryName, zoneName);
        return timeZone;
    }

    @Override
    public void executeAfterAsyncTask() {
        return;
    }
}
