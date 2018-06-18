package com.br.studycalendarview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

;

/**
 * Created by r028367 on 13/02/2017.
 */

public class DialogV4Utils extends DialogFragment {

    private ChannelToDialogListener listener;
    private int layout_dialog;
    private String title;
    public static final String ID_LAYOUT        = "ID_LAYOUT";
    public static final String DIALOG_TITLE     = "DIALOG_TITLE";


    public DialogV4Utils() {
        super();
    }

    public static DialogV4Utils newInstance(int layout, String title) {
        DialogV4Utils dialogV4Utils = new DialogV4Utils();
        Bundle bundle = new Bundle();
        bundle.putInt(ID_LAYOUT, layout);
        bundle.putString(DIALOG_TITLE, title);
        dialogV4Utils.setArguments(bundle);
        return dialogV4Utils;
    }


    public static DialogV4Utils newInstance(int layout, String title, ChannelToDialogListener listener) {
        DialogV4Utils dialogV4Utils = new DialogV4Utils();
        Bundle bundle = new Bundle();
        bundle.putInt(ID_LAYOUT, layout);
        bundle.putString(DIALOG_TITLE, title);
        dialogV4Utils.setArguments(bundle);
        dialogV4Utils.listener = listener;
        return dialogV4Utils;
    }


    // after contrutor
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.layout_dialog  = getArguments().getInt(ID_LAYOUT);
            this.title          = getArguments().getString(DIALOG_TITLE);
        }
    }

    // after metodo oncreate
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(layout_dialog != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(layout_dialog, null);
            builder.setView(view);
            builder.setTitle(title);
            builder.setCancelable(false);
            builder.setPositiveButton("Confirmar", listener.onDialogPositiveClick(this));
            builder.setNegativeButton("Cancelar", listener.onDialogPositiveClick(this));
            return builder.create();
        }
        else {
            return super.onCreateDialog(savedInstanceState);
        }
    }

    public interface ChannelToDialogListener {
        public DialogInterface.OnClickListener onDialogNegativeClick(DialogFragment dialog);
        public DialogInterface.OnClickListener onDialogPositiveClick(DialogFragment dialog);
        public void notifyClassImplementation(DialogFragment dialog, View view);
    }


    // after metodo onCreateDialog()
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.layout_dialog, null);
        return view;
    }

    /**
     * Chamado antes do onCreate
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChannelToDialogListener) {
            this.listener = (ChannelToDialogListener) context;
        } else {
            // throw new RuntimeException(context.toString() + " must implement ChannelToDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    // after onCreateView
    @Nullable
    @Override
    public View getView() {
        View view = super.getView();
        listener.notifyClassImplementation(this, view);
        return view;
    }
}
