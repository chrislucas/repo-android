package com.br.maps;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Created by r028367 on 13/02/2017.
 */

public class PermissionUtils {

    public static boolean validate(Activity context, List<String> permissions) {
        for(int i=0; i<permissions.size(); i++) {
            String permission = permissions.get(i);
            int status = ContextCompat.checkSelfPermission(context, permission);
            if(status != PackageManager.PERMISSION_GRANTED) {
                /*
                * shouldShowRequestPermissionRationale, esse metodo verifica se eh necessario
                * pedir a permissao do usuario para poder acessar um recurso do DEVICE, como
                * por exemplo, Lista de contatos, camera, Salvar em SDcard
                *
                * Se o usuario ja disse que nao para essa permissao e marcou a opcao Don't Ask
                * Again, esse metodo retornara false. Se o DEVICE tem alguma politica de nao
                * permitir esse recurso, o metodo tambem retornara false
                * */
                if(ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                }

                else {
                    /*
                    * Numa explioacao sobre o pq de pedir autorizacao para acessar um recurso
                    * precisa ser dada? Entao so precisamos pedir autorizacao
                    * */
                    ActivityCompat.requestPermissions(context, new String[] {permission}, i);
                }
            }
        }

        return false;
    }

}
