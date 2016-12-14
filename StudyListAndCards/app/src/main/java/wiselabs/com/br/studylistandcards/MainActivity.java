package wiselabs.com.br.studylistandcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        List<Noticia> noticias = mock();
        RecyclerView rcView = (RecyclerView) findViewById(R.id.layout_recycle_noticias);
        rcView.setAdapter(new AdapterNoticia(noticias, this));
    }

    private List<Noticia> mock() {
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia(1, "", "ref:1/101"));
        return noticias;
    }
}
