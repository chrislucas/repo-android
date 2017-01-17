package com.example.r028367.navscheme.study.swipe.fragments.entities;

import java.lang.reflect.InvocationTargetException;
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

    /*
    * String: Codigo da funcionalidade
    * AbstractFeatures: Classe modelo que representa um conjunto de funcionalidades
    *
    *
    * */
    private Map<String, AbstractFeatures> featuresSystem;

    public FactoryMapFeatures() {
        featuresSystem = new HashMap<>();
        try {

            /*
            * O banco com as funcionalidades deve ter
            * Codigo, Descricao e o nome da classe que representa a funcionalidade
            * */

            String className    = "MateriaisFeatures";
            String description  = "Menu de Materiais";
            String code         = "130";

            Class c = Class.forName(className);
            Object [] arguments = {description, code};
            AbstractFeatures features = (AbstractFeatures) c.getConstructor(MaterialsFeatures.class).newInstance(arguments);
            featuresSystem.put(code, features);
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void getFeatures() {

    }

}
