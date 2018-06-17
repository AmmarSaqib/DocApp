package com.example.ammarsaqib.docapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link aboutus.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class aboutus extends DialogFragment {

    Button cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // creating the inflater for about us
        View about_us = inflater.inflate(R.layout.fragment_aboutus,container,false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        // initialising the button
        cancel = about_us.findViewById(R.id.aboutus_dismiss);
        cancel.setOnClickListener(new View.OnClickListener() {

            // setting on click for dismissing the dialog
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return about_us;
    }
}
