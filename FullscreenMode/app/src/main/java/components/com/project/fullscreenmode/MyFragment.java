package components.com.project.fullscreenmode;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }



    public static final String TAG_FRAGMENT = "TAG_FRAGMENT";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View view = getActivity().getWindow().getDecorView();
        /**
         *
         * */
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int height = view.getHeight();
                Log.i(TAG_FRAGMENT, String.format("Tamanho do fragment %s", height));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item != null && item.getItemId() == R.id.action_fullscreen) {
            toggleHideyActionBar();
        }
        return true;
    }

    /**
     *
     * fonte
     * https://developer.android.com/training/system-ui/immersive.html#nonsticky
     * Flags
     * SYSTEM_UI_FLAG_IMMERSIVE | SYSTEM_UI_FLAG_HIDE_NAVIGATION | SYSTEM_UI_FLAG_FULLSCREEN
     *
     * Quando combinamos essas flags utilizando bitwise podemos esconder a actionBar do android,
     * trazendo o que os developers chamam de "verdadeira experiencia de imersao de tela"
     * (Belo nome para colocar em fullscreen)
     *
     * Segundo a documentacao, mesmo em fullscreen, a activity continua com recebendo
     * evento de touch na tela. Se o usuario pressionar a tela na posicao onde originalmente
     * a actionBar esta, ela aparecera
     *
     * A acao do touch modifica os valores de  SYSTEM_UI_FLAG_HIDE_NAVIGATION e SYSTEM_UI_FLAG_FULLSCREEN
     * e dispara o metodo  onSystemUiVisibilityChange na implemetacao de
     * View.OnSystemUiVisibilityChangeListener. Ainda eh possivel fazer a barra se esconder
     * depois de alguns segundos
     *
     * FLAGS
     * https://developer.android.com/reference/android/view/View.html#SYSTEM_UI_FLAG_HIDE_NAVIGATION
     * */

    public void toggleHideyActionBar() {
        int systemUIVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        int newSystemUIVisibility = systemUIVisibility;
        /*
        *
        * https://developer.android.com/reference/android/view/View.html#SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        *
        * SYSTEM_UI_FLAG_IMMERSIVE_STICKY eh uma flag que serve para modificar
        * SYSTEM_UI_FLAG_FULLSCREEN and SYSTEM_UI_FLAG_HIDE_NAVIGATION,
        * Ela so vai funcionar se combinada com uma das duas
        *
        * SYSTEM_UI_FLAG_IMMERSIVE eh uma flag que serve para modificar
        * SYSTEM_UI_FLAG_HIDE_NAVIGATION
        *
        * Ela so vai funcionar se combinada com a tag acima
        *
        * */

        /**
         * systemUIVisibility comeca == 0 pois ainda nao foi definida
         * assim 0 | qualquercoisa = qualquercoisa
         * */
        //boolean toggleImmersiveMode = (systemUIVisibility | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == systemUIVisibility;
        boolean toggleImmersiveMode = (systemUIVisibility | View.SYSTEM_UI_FLAG_IMMERSIVE) == systemUIVisibility;
        Log.i(TAG_FRAGMENT, String.format("%s Modo imersivo", toggleImmersiveMode ? "Desativar" : "Ativar"));
        /**
         * Usar o bit xor para 'desligar os bits semelhantes' 0 ^ 0 && 1 ^ 1 == 0
         *
         * */
        // esconder navigation bar
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            newSystemUIVisibility ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // esconder status bar
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            newSystemUIVisibility ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        /**
         * Modo imversivo e compativel com a api 19 do android
         *
         * */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //newSystemUIVisibility ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            newSystemUIVisibility ^= View.SYSTEM_UI_FLAG_IMMERSIVE;
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(newSystemUIVisibility);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.my_fragment, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
