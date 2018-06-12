package com.example.r028367.navscheme.study.swipe.fragments.menus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r028367.navscheme.R;
import com.example.r028367.navscheme.study.swipe.fragments.entities.SyncServiceOrder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuAccessSyncServiceOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuAccessSyncServiceOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuAccessSyncServiceOrder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public SyncServiceOrder getSyncServiceOrder() {
        return syncServiceOrder;
    }

    public void setSyncServiceOrder(SyncServiceOrder syncServiceOrder) {
        this.syncServiceOrder = syncServiceOrder;
    }

    private SyncServiceOrder syncServiceOrder;

    private OnFragmentInteractionListener mListener;

    public MenuAccessSyncServiceOrder() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    /*
    public static MenuAccessSyncServiceOrder newInstance() {
        MenuAccessSyncServiceOrder fragment = new MenuAccessSyncServiceOrder();
        return fragment;
    }
    */

    public static MenuAccessSyncServiceOrder newInstance(SyncServiceOrder syncServiceOrder) {
        MenuAccessSyncServiceOrder fragment = new MenuAccessSyncServiceOrder();
        fragment.setSyncServiceOrder(syncServiceOrder);
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
        return inflater.inflate(R.layout.fragment_menu_access_sync_service_order, container, false);
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

    @Override
    public void onResume() {
        super.onResume();
        String title = syncServiceOrder.getDescription();
        if(title != null)
            getActivity().setTitle(title);
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
