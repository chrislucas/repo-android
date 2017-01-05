package com.br.studyfragments.tutorial.vogella;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.studyfragments.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView detailTextView;

    private OnFragmentInteractionListener fragmentInteractionListener;

    public FragmentDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetail newInstance(String param1, String param2) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentDetail newInstance(String ... args) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle bundle = new Bundle();
        for(String arg : args)
            bundle.putString(EXTRA_URL, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final String EXTRA_URL = "TAG";

    /*
    *
    * Ciclo de vida
    * onAttach -> onCreate -> onCreateView ->
    * */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            fragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }



    /*
    *
    * Esse metodo eh chamado somente na criacao do Fragment ou quando
    * quando ela eh destroida no ciclo pelo metodo onDestroy() -> onDeatch()
    * e depois e recolocado no ciclo de vida da aplicacao indo do onDeatch() -> onAttach
    *
    * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String text = bundle.getString(EXTRA_URL);
            detailTextView = (TextView) getView().findViewById(R.id.text_view_detail);
            detailTextView.setText(text);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*
    * a partir daqui, segundo o desenho de ciclo de vida da fragment,
    * ela esta ativa.
    * */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInteractionListener = null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (fragmentInteractionListener != null) {
            fragmentInteractionListener.onFragmentInteraction(uri);
        }
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
     *
     * Entao essa eh uma inner interface para possibilitar que Esse fragment
     * se comunica com a activity Pai ou outras activities
     *
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
