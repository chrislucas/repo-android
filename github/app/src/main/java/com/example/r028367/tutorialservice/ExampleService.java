package com.example.r028367.tutorialservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/*
*
* Um compoenente que permite fazer operacoes longaas em segundo plano
* sem atrapalhar o desempenho da aplicacao.
*
* Um compoenente da aplicacao pode iniciar um serviço e qunado tal componente
* for destruido (sair da memoria) o seviço pode continuar, indefinidamente,
* se necessario.
*
* Um serviço possue 2 formas, essencialmente
*
* Started
* Quando um componente da aplicacao executar o metodo onStartCommand
* Um serviço iniciado geralmente executa uma opperacao e nao retorna nenhuma informacao.
*
*
* Binded
*
* Um servico eh vinculado quando um componente chama o metodo
* bindService. Um servico vinculado oferece uma inerface servidor-cliente
* onde os compoenentes a aplicacao podem solicitar algo do servico. O servico
* fica ativo enquanto houver componentes vinculados ao servico
*
* */

public class ExampleService extends Service implements Runnable {
    public static final String CATEGORY = "SERVICE_CREATE_EXAMPLE";
    public static final int MAX_EXEC = 10;
    private int count;
    private boolean status;

    public ExampleService() {
    }

/*
Obsoleto
https://android-developers.googleblog.com/2010/02/service-api-changes-starting-with.html
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count = 0;
        status = true;
        new Thread(this, String.format("ExampleService %d", startId)).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }
/*
*
* O sistema chama esse metodo sempre que um serviço eh criado pela primeira vez,
* antes de executar o metodo onStartCommand ou onBind, para que possamos fazer alguma
* configuracao inicial.
* Se o serviço estiver em execucao, esse metodo nao eh executado
* */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CATEGORY, "Inicio Exemplo Service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        status = false;
        Log.i(CATEGORY, "Service onDestroy");
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void run() {
        while(status && count < MAX_EXEC) {
            Log.i(CATEGORY, String.format("%d", count));
            count++;
        }
        Log.i(CATEGORY, "Fim Exemplo Service");
        stopSelf();
    }

    private void doSomething() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iexcp) {
            Log.e(CATEGORY, iexcp.getMessage());
        }
    }
}
