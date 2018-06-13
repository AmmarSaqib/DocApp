package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentOne extends Fragment {

    Button allListing, spec, listedByHsptl, listedByLction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //creating mainfrag to inflate frag one
        View mainfrag = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        // button iniitalising here
        allListing = mainfrag.findViewById(R.id.alllisting);
        spec = mainfrag.findViewById(R.id.Spec);
        listedByHsptl = mainfrag.findViewById(R.id.listedbyhsptl);
        listedByLction = mainfrag.findViewById(R.id.lstdbylocation);
        // adding listeners on buttons
        allListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors
            }
        });

        spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by specialization
            }
        });

        listedByHsptl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by hospitals
            }
        });

        listedByLction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by the location of the user
            }
        });

        //        return super.onCreateView(inflater, container, savedInstanceState);

        return mainfrag;
    }
}
