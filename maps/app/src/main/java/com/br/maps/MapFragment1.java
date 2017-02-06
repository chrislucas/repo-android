package com.br.maps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment1 extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;


    private GoogleMap googleMap;
    private static Context context;

    private OnFragmentInteractionListener mListener;

    public MapFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MapFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment1 newInstance(Context ctx, String param1) {
        MapFragment1 fragment = new MapFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        context = ctx;
        return fragment;
    }


    public static MapFragment1 newInstance(Context ctx) {
        MapFragment1 fragment = new MapFragment1();
        context = ctx;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_map1, container, false);

        /*
        * Metodo getChildFragmentManager
        *
        * retorna um FragmentManager para gerenciar
        * fragments dentro de um fragment
        * Recupera um fragment dentro do layout, no nosso caso o
        * Fragment eh o mapa
        * */
        SupportMapFragment mapFragment2 = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if(mapFragment2 != null) {
            mapFragment2.getMapAsync(this);
        }
        return viewRoot;
    }

    /*
    *
    * Executado quando o mapa terminar sua inicialização
    * */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            googleMap.setMyLocationEnabled(true);
                /*
                *
                * Poli USP
                * -23.5570464,-46.7328786,15
                * IME USP
                * -23.5595922,-46.7313849,15
                * X
                * -23.5506027,-46.3775692
                * */
            double latitude = -23.5506027, longitude = -46.3775692;
            LatLng location = new LatLng(latitude, longitude);
            int zoom = 13;
            // Posiciona o mapa na coordenada X com zoom 13
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, zoom);
            googleMap.moveCamera(update);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("Meu Mapa")
                    .snippet("")            //
                    .position(location);
            googleMap.addMarker(markerOptions);
            // tipo
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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
