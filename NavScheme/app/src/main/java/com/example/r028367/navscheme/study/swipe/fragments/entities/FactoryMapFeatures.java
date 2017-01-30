package com.example.r028367.navscheme.study.swipe.fragments.entities;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessMaterials;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by r028367 on 17/01/2017.
 *
 * Essa classe eh responsavel por montar um mapa de todas
 * as classes que representam um conjunto de funcionalidades
 * do sistema.
 *
 *
 * Por exemplo a classe {@link MaterialsFeatures} sera responsavel por implementar
 * as funcionalidades realacioadas aos Materiais que um usuario tem acesso
 *
 * A classe {@link ServiceOrderFeatures} eh responsavel pelos metodos relacionados
 * a ordem de servico.
 *
 */

public class FactoryMapFeatures {

    private static final String INSTANTIATION_EXCP      = "INSTANTIATION_EXCP";
    private static final String ILLEGAL_ACCESS_EXCP     = "ILLEGAL_ACCESS_EXCP";
    private static final String NO_SUCH_METHOD_EXCP     = "NO_SUCH_METHOD_EXCP";
    private static final String INVOCATION_TARGET_EXCP  = "INVOCATION_TARGET_EXCP";
    private static final String CLASS_NTF_EXCP          = "CLASS_NTF_EXCP";

    /*
    * String: Codigo da funcionalidade
    * AbstractFeatures: Classe modelo que representa um conjunto de funcionalidades
    * */
    private Map<String, AbstractFeatures> featuresSystem;

    /*
    * Um conjutno de funcionalidades (AbstractFeatures)
    * está vinculado a uma View (Fragment). Para acessar
    * as funcionalidades eh necessario acessar o Fragment
    * que da acesso a classe que agrupa tais funcionalidades
    * Dessa forma, achou-se necessario a criacao de um Mapa<Funcionalidade, Tela>
    *     que faz o vinculo, o que nos permite mostrar para o usuario somente
    *     as telas que estao vinculadas as funcionalidades que ele tem permissao
    *     de aessar
    *
    * O mapa abaixo tem a seguinte forma <Funcionalidade, Tela>
    *     Chave: funcionalidade, Valor: Tela que acessa a funcionalidade
    * */
    private Map<AbstractFeatures, Fragment> viewsFeatureSystem;

    public FactoryMapFeatures() {
        featuresSystem      = new HashMap<>();
        viewsFeatureSystem  = new HashMap<>();
        try {
            /*
            * O banco com as funcionalidades deve ter
            * Codigo, Descricao e o nome da classe que representa a funcionalidade
            *
            * Faremos um loop pelas funcionalidades e montaremos o mapa
            * */
            List<AbstractFeatures> list = new ArrayList<>();
            for(AbstractFeatures feature : list) {}
            // argumentos para o metodo construtor da classe MaterialsFeatures
            Object [] arguments = new Object[2];
            // nome da classe que representa um grupo de Funcionalidades
            String classFeatureName  = "com.example.r028367.navscheme.study.swipe.fragments.entities.MaterialsFeatures";
            // descricao da classe
            String description       = "Menu de Materiais";
            // codigo
            String code              = "130";
            // Classe que representa um conjunto de funcionalidades
            Class classFeature = Class.forName(classFeatureName);
            arguments[0] = description;
            arguments[1] = code;
            // construtor de uma funcionalidade
            Constructor<?> constructorFeature = classFeature.getConstructor(String.class, String.class);
            // Pegando a instancia especialiazada de AbstractFeatures usando reflection
            // nao confundir o metodo newInstance da classe Construtor com o metodo newInstance dos Fragments que eu implementei
            AbstractFeatures abstractFeatures = (AbstractFeatures) constructorFeature.newInstance(arguments); // Construtor.newInstance
            // adicionar no mapa o Codigo e a Classe Funcionalidade
            featuresSystem.put(code, abstractFeatures);

            // Classe (Tela) que da acesso a funcionalidade
            String classViewName = "com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessMaterials";
            // Pegando a classe que representa a tela (Um Fragment no meu caso)
            Class classView      = Class.forName(classViewName);
            //Constructor<?> constructorMenu = classView.getConstructor();
            //Object instanceView = constructorFeature.newInstance();

            /*
            * Para criar uma instancia de uma Tela que da acesso a uma funcionalidade
            * devo chamar o metodo "newInstance", esse metodo recebe como argumento
            * uma especializacao da classe AbstractFeatures
            * */
            Class [] argsMethod = new Class[1];
            /*
            * O metodo newInstance recebe uma (instancia) especializada da class AbstractFeatures
            * Nesse caso MaterialsFeatures; para criarmos uma instancia foi usado reflection
            * */
            argsMethod[0] = abstractFeatures.getClass();
            /*
            * Para invocar o metodo newInstance.invoke() devo
            * passar como argumento um vetor de Objects
            * */
            Object [] argsInstance = new Object[1];
            argsInstance[0] = abstractFeatures;
            // para chamar o metodo getDeclaredMethod os argumentos passsado sao
            // String: Nome do metodo, Class[] arguments
            Method method =  classView.getDeclaredMethod("newInstance", argsMethod);
            // como o metodo eh estatico eu nao preciso criar
            Fragment viewFeature = (Fragment) method.invoke(null, argsInstance);
            viewsFeatureSystem.put(abstractFeatures, viewFeature);

            /* ====================================================================== */

            // nome da classe que representa um grupo de Funcionalidades
            classFeatureName    = "com.example.r028367.navscheme.study.swipe.fragments.entities.ServiceOrderFeatures";
            description         = "Ordens de Serviço";
            code                = "132";
            classFeature = Class.forName(classFeatureName);
            arguments[0] = description;
            arguments[1] = code;

            // construtor de uma funcionalidade
            constructorFeature = classFeature.getConstructor(String.class, String.class);

            // Pegando a instancia especialiazada de AbstractFeatures usando reflection
            // nao confundir o metodo newInstance da classe Construtor com o metodo newInstance dos Fragments que eu implementei
            abstractFeatures = (AbstractFeatures) constructorFeature.newInstance(arguments); // Construtor.newInstance
            // adicionar no mapa o Codigo e a Classe Funcionalidade
            featuresSystem.put(code, abstractFeatures);

            // Classe (Tela) que da acesso a funcionalidade
            classViewName = "com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessServiceOrders";
            // Pegando a classe que representa a tela (Um Fragment no meu caso)
            classView      = Class.forName(classViewName);
            //Constructor<?> constructorMenu = classView.getConstructor();
            //Object instanceView = constructorFeature.newInstance();

            /*
            * Para criar uma instancia de uma Tela que da acesso a uma funcionalidade
            * devo chamar o metodo "newInstance", esse metodo recebe como argumento
            * uma especializacao da classe AbstractFeatures
            * */
            argsMethod = new Class[1];
            /*
            * O metodo newInstance recebe uma (instancia) especializada da class AbstractFeatures
            * Nesse caso MaterialsFeatures; para criarmos uma instancia foi usado reflection
            * */
            argsMethod[0] = abstractFeatures.getClass();
            /*
            * Para invocar o metodo newInstance.invoke() devo
            * passar como argumento um vetor de Objects
            * */
            argsInstance = new Object[1];
            argsInstance[0] = abstractFeatures;
            // para chamar o metodo getDeclaredMethod os argumentos passsado sao
            // String: Nome do metodo, Class[] arguments
            method =  classView.getDeclaredMethod("newInstance", argsMethod);
            // como o metodo eh estatico eu nao preciso criar
            viewFeature = (Fragment) method.invoke(null, argsInstance);
            viewsFeatureSystem.put(abstractFeatures, viewFeature);


            /* ====================================================================== */

            // nome da classe que representa um grupo de Funcionalidades
            classFeatureName   = "com.example.r028367.navscheme.study.swipe.fragments.entities.SyncServiceOrder";
            description        = "Sincronizar OS";
            code               = "134";
            classFeature = Class.forName(classFeatureName);
            arguments[0] = description;
            arguments[1] = code;

            // construtor de uma funcionalidade
            constructorFeature = classFeature.getConstructor(String.class, String.class);

            // Pegando a instancia especialiazada de AbstractFeatures usando reflection
            // nao confundir o metodo newInstance da classe Construtor com o metodo newInstance dos Fragments que eu implementei
            abstractFeatures = (AbstractFeatures) constructorFeature.newInstance(arguments); // Construtor.newInstance
            // adicionar no mapa o Codigo e a Classe Funcionalidade
            featuresSystem.put(code, abstractFeatures);

            // Classe (Tela) que da acesso a funcionalidade
            classViewName = "com.example.r028367.navscheme.study.swipe.fragments.menus.MenuAccessSyncServiceOrder";
            // Pegando a classe que representa a tela (Um Fragment no meu caso)
            classView      = Class.forName(classViewName);
            //Constructor<?> constructorMenu = classView.getConstructor();
            //Object instanceView = constructorFeature.newInstance();

            /*
            * Para criar uma instancia de uma Tela que da acesso a uma funcionalidade
            * devo chamar o metodo "newInstance", esse metodo recebe como argumento
            * uma especializacao da classe AbstractFeatures
            * */
            argsMethod = new Class[1];
            /*
            * O metodo newInstance recebe uma (instancia) especializada da class AbstractFeatures
            * Nesse caso MaterialsFeatures; para criarmos uma instancia foi usado reflection
            * */
            argsMethod[0] = abstractFeatures.getClass();
            /*
            * Para invocar o metodo newInstance.invoke() devo
            * passar como argumento um vetor de Objects
            * */
            argsInstance = new Object[1];
            argsInstance[0] = abstractFeatures;
            // para chamar o metodo getDeclaredMethod os argumentos passsado sao
            // String: Nome do metodo, Class[] arguments
            method =  classView.getDeclaredMethod("newInstance", argsMethod);
            // como o metodo eh estatico eu nao preciso criar
            viewFeature = (Fragment) method.invoke(null, argsInstance);
            viewsFeatureSystem.put(abstractFeatures, viewFeature);
        }
        catch (InstantiationException e) {
            Log.e(INSTANTIATION_EXCP, e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e(ILLEGAL_ACCESS_EXCP, e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e(NO_SUCH_METHOD_EXCP, e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e(INVOCATION_TARGET_EXCP, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(CLASS_NTF_EXCP, e.getMessage());
        }

    }

    public Map<String, AbstractFeatures> getFeatures() {
        return this.featuresSystem;
    }

    public Map<AbstractFeatures, Fragment> getViewsFeatureSystem() {
        return this.viewsFeatureSystem;
    }

}
