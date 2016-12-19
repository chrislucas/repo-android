package wiselabs.com.br.studylistandcards;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import wiselabs.com.br.studylistandcards.adapter.AdapterNoticia;
import wiselabs.com.br.studylistandcards.entity.Noticia;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Noticia> noticias = this.mock();
        RecyclerView rcView = (RecyclerView) findViewById(R.id.layout_recycle_noticias);
        rcView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        //lm.setReverseLayout(true);
        rcView.setLayoutManager(lm);
        AdapterNoticia adapter = new AdapterNoticia(noticias, this);
        rcView.setAdapter(adapter);
        return;
    }

    private List<Noticia> mock() {
        List<Noticia> noticias = new ArrayList<>();
        Noticia[] fakes = {
                new Noticia(
                        1
                        , "Câmara adia audiência extraordinária"
                        , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                        , "ref:1/101"
                )
                , new Noticia(
                2
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        )
                , new Noticia(
                3
                , "Câmara adia audiência extraordinária"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                , "ref:1/101"
        )
                , new Noticia(
                4
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        ),
                new Noticia(
                        5
                        , "Câmara adia audiência extraordinária"
                        , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                        , "ref:1/101"
                )
                , new Noticia(
                6
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        )
                , new Noticia(
                7
                , "Câmara adia audiência extraordinária"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                , "ref:1/101"
        )
                , new Noticia(
                8
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        ),
                new Noticia(
                        9
                        , "Câmara adia audiência extraordinária"
                        , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                        , "ref:1/101"
                )
                , new Noticia(
                10
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        )
                , new Noticia(
                11
                , "Câmara adia audiência extraordinária"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                , "ref:1/101"
        )
                , new Noticia(
                12
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        ),
                new Noticia(
                        13
                        , "Câmara adia audiência extraordinária"
                        , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                        , "ref:1/101"
                )
                , new Noticia(
                14
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        )
                , new Noticia(
                15
                , "Câmara adia audiência extraordinária"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam non metus finibus orci posuere laoreet. Donec sodales porttitor leo, et mollis massa auctor at."
                , "ref:1/101"
        )
                , new Noticia(
                16
                , "Câmara adia reunião com vereadores"
                , "Etiam sollicitudin tincidunt nulla vel venenatis. Vivamus condimentum massa nec lacus porttitor hendrerit."
                , "ref:2/101"
        )

        };
        for (Noticia fake : fakes)
            noticias.add(fake);
        return noticias;
    }
}
