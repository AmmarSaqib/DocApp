package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bySpec.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bySpec#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bySpec extends Fragment {

    /*
    The fragment load the list of Specializations to select from.
     */

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;


    public bySpec() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        items.add("Dentist");
        items.add("General Physician");
        items.add("General Surgeon");
        items.add("Eye Surgeon");
        items.add("Eye Specialist");
        items.add("Cardiologist");
        items.add("Dermatologist");
        items.add("Neuro Surgeon");
        items.add("Neurologist");
        items.add("Orthopedic Surgeon");
        items.add("Physiotherapist");
        items.add("Gynecologist");
        items.add("Ent Surgeon");

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spec_list, items);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_by_spec, container, false);

        lv = view.findViewById(R.id.docspec);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle info = new Bundle();
                info.putString("Spec",items.get(position));

                list_byspec sp = new list_byspec();
                sp.setArguments(info);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_replaceable, sp);
                ft.addToBackStack("doc_by_spec");

                ft.commit();
            }
        });

        lv.setAdapter(adapter);

        return view;
    }

    public void onClick(int position, long id)
    {

    }

}
