package com.example.r028367.serviceandbinder.com.br.study.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.r028367.serviceandbinder.ExampleService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 *
 * Recomenda-se que ao implementar uma Service, a mesma inicie sua propria THread
 * para não ser executada na mesma Thread que o sistema (UIThread, principal)
 *
 * Para facilitar o trabalho temos a IntentService, que realiza o trabalho
 * de criar uma Thread para rodar em um processo separado.
 *
 *
 * Diferenca entre Service e IntentService.
 *
 * Quando criamos uma classe Filha de Service e iniciamos
 * o servico atraves startService() o metodo onCreate eh executado
 * uma unica vez, porem a cada N execucoes de startService() o metodo
 * onStartCommand() eh executado N vezes, assim concluimos que se
 * quisermos um servico multihread basta deixar na resposabilidade
 * de onStartCommand() criar as Thread, se quisermos um Servico
 * com uma unica Thread deixemos em onCreate()
 *
 * Se criarmos uma classe filha de IntentService, uma unica Thread
 * sera criada, e todas as chamadas a startService() serao enfileiradas
 * utilizando um handler internamente
 *
 *
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.r028367.serviceandbinder.com.br.study.intentservice.action.FOO";
    private static final String ACTION_BAZ = "com.example.r028367.serviceandbinder.com.br.study.intentservice.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.r028367.serviceandbinder.com.br.study.intentservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.r028367.serviceandbinder.com.br.study.intentservice.extra.PARAM2";

    private static final String THREAD_INTENT_SERVICE = "MY_INTENT_SERVICE";

    public MyIntentService() {
        super(THREAD_INTENT_SERVICE);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*
        * Esse metodo esta sendo executado numa Thread
        * apos a executacao o metodo stopSelf() sera executado.
        *
        *
        * A classe IntentService eh abstrata, ela encapsula a criacao
        * de uma Thread Atraves de uma Inner Class chamada ServiceHandler
        * que extende a classe Handler. Essa Inner Class implementa o metodo
        * handleMessage(Message msg) e tem a sacada de executar o metodo
        * onHandlerIntent(Intent intent) que eh nosssa responsabilidade
        * de implementar. Assim o metodo onHandleIntent que sera executado
        * sera a nossa implementacao
        *
        * */
        for(int i=0; i<15; i++) {
            try {
                Log.i("COUNTER", String.valueOf(i));
                Thread.sleep(1000);
            } catch (Exception e) {
                Log.e("EXCEPTION_SLEEP_THREAD", e.getMessage());
            }
        }
        Log.i("FINISH", "finish");
        /*
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        */
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {

    }
}
