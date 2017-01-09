package com.example.r028367.tutorialservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // private Button btnStartService;

    /*
    * Para iniciar um servico utilizamos o metodo startService, para
    * para-lo usamos o stopService. Os metodos recebem como argumento
    * uma Intent, a intent que inicia o serviço deve ser usada para encerra-lo,
    * do contrario um erro ocorrera, informando que a intent deve ser passada
    * de forma explicita
    *  Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=SERVICE_1 }
    * */

    private Intent intent;
    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // essa eh a forma explicita de declarar uma intent
        intent = new Intent(this, ExampleService.class);
        /*
        start = (Button) findViewById(R.id.start_service);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        stop = (Button) findViewById(R.id.stop_service);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        */
    }

    public void initService(View view) {
        startService(intent);
        //Log.i("LOG", String.valueOf(System.currentTimeMillis()));
    }

    public void stopService(View view) {
        stopService(intent);
    }

}
