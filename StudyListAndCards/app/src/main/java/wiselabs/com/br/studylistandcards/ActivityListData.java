package wiselabs.com.br.studylistandcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wiselabs.com.br.studylistandcards.adapter.AdapterNoticia;
import wiselabs.com.br.studylistandcards.entity.Noticia;

public class ActivityListData extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView rcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        List<Noticia> noticias = this.mock();
        rcView = (RecyclerView) findViewById(R.id.my_recycler_view);
        /*
        *
        * Se o conteudo que preenche a RecycleView (conteudo da classe Adapter) possui um tamanho fixo
        * o metodo abaixo ajuda o android a criar uma lista com uma melhor performance
        * */
        rcView.setHasFixedSize(true);
        /*
        * Com um gerenciador de layout Linear, nos teremos
        * uma lista com comportamento de ListView, se a orientacao
        * for vertical, se for horizontal, teremos u comportamento de galeria
        * */
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager = new GridLayoutManager(this, 2);

        rcView.setLayoutManager(linearLayoutManager);
        AdapterNoticia adapter = new AdapterNoticia(noticias, this);
        rcView.setAdapter(adapter);
        return;
    }

    public void mudarLayout(View view) {
        RecyclerView.LayoutManager lm = rcView.getLayoutManager() instanceof GridLayoutManager
                ? linearLayoutManager : gridLayoutManager;
        rcView.setLayoutManager(lm);
    }

    public void adicionar(View view) {

    }

    private  List<Noticia> mock() {
        List<Noticia> noticias = new ArrayList<>();
        Noticia [] fakes = {
                new Noticia(
                    1
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    2
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                )
                ,new Noticia(
                    3
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    4
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                ),
                new Noticia(
                    5
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    6
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                )
                ,new Noticia(
                    7
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    8
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                ),
                new Noticia(
                    9
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    10
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                )
                ,new Noticia(
                    11
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    12
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                ),
                new Noticia(
                    13
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                        14
                        ,"Câmara adia reunião com vereadores"
                        ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                        ,"ref:2/101"
                )
                ,new Noticia(
                    15
                    ,"Câmara adia audiência extraordinária"
                    ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                    ,"ref:1/101"
                )
                ,new Noticia(
                    16
                    ,"Câmara adia reunião com vereadores"
                    ,"Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                    ,"ref:2/101"
                )

        };
        for(Noticia fake : fakes)
            noticias.add(fake);
        return noticias;
    }
}
