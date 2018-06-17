package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


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
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                list_all listAll = new list_all();
                ft.replace(R.id.fragment_replaceable, listAll);
                ft.addToBackStack("list_all");
                ft.commit();

            }
        });

        spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by specialization
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                bySpec listAll = new bySpec();
                ft.replace(R.id.fragment_replaceable, listAll);
                ft.addToBackStack("doc_spec");
                ft.commit();
            }
        });

        listedByHsptl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by hospitals
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                byHos listAll = new byHos();
                ft.replace(R.id.fragment_replaceable, listAll);
                ft.addToBackStack("doc_hos");
                ft.commit();
            }
        });

        listedByLction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the activity that lists all Doctors by the location of the user
                Toast.makeText(getActivity().getApplicationContext(),"Not Implemented", Toast.LENGTH_SHORT).show();
            }
        });

        //        return super.onCreateView(inflater, container, savedInstanceState);

        return mainfrag;
    }
}
