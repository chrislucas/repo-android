package com.example.r028367.navscheme.study.swipe.fragments.menus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r028367.navscheme.R;
import com.example.r028367.navscheme.study.swipe.fragments.entities.ServiceOrderFeatures;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuAccessServiceOrders.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuAccessServiceOrders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuAccessServiceOrders extends Fragment {

    private OnFragmentInteractionListener mListener;

    public void setServiceOrder(ServiceOrderFeatures serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    private ServiceOrderFeatures serviceOrder;

    public ServiceOrderFeatures getServiceOrder() {
        return serviceOrder;
    }

    public MenuAccessServiceOrders() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
/*
    public static MenuAccessServiceOrders newInstance() {
        MenuAccessServiceOrders fragment = new MenuAccessServiceOrders();
        return fragment;
    }
*/

    public static MenuAccessServiceOrders newInstance(ServiceOrderFeatures serviceOrder) {
        MenuAccessServiceOrders fragment = new MenuAccessServiceOrders();
        fragment.setServiceOrder(serviceOrder);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_access_service_orders, container, false);
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
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
        */
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

    @Override
    public void onResume() {
        super.onResume();
        String title = serviceOrder.getDescription();
        if(title != null)
            getActivity().setTitle(title);
    }
}
