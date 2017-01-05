package com.example.r028367.studysupportapi;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExampleCoordinatorLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_coordinator_layout);
        FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // argumentos
                // View view: view pai da snackbar
                // String text: Texto que aparece no menu
                // int duration: tempo que o menu fica aberto
                Snackbar.make(view, "Um texto qualquer", Snackbar.LENGTH_SHORT).show();
            }
        });

        /*
        * Usando um float button de uma API de terceiro
        * */
        com.getbase.floatingactionbutton.FloatingActionButton floatButton2 = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.float_btn2);
        floatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Outro texto qualquer", Snackbar.LENGTH_INDEFINITE).show();
            }
        });

        /*
        * Foi criada uma classe Denominada FloatingActionButtonBehavior para
        * definir o comportamento que o botao 'floatButton2' iria ter quando pressionado.
        * Existe um annotation  '@CoordinatorLayout.DefaultBehavior(Class.class)' que
        * permite definir na classe de um boao quao a classe que definine seu comportamento
        * Podemos ver um exemplo disso na class FloatActionButton do android, que possue uma
        * classe aninhada e estatica de que define o comportamento de seu botao flutuante
        * */
    }
}
