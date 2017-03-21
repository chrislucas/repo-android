package com.br.studycalendarview;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by r028367 on 21/03/2017.
 */

public class VincularMinhaAgenda extends AsyncTask<Void, Void, Void> {

    // essa interface pode ser util caso quem chame essa
    // AsyncTask queria saber quando o metodo onPostExecute foi executado
    interface ChannelWithCaller {
        public void execute();
    }

    private ChannelWithCaller channelWithCaller;
    private Context context;

    public VincularMinhaAgenda(Context context, ChannelWithCaller channelWithCaller) {
        this.context = context;
        this.channelWithCaller = channelWithCaller;
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        this.channelWithCaller.execute();
    }
}
