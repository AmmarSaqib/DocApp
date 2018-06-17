package com.example.ammarsaqib.docapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
 * {@link byHos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link byHos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class byHos extends Fragment {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;


    public byHos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        items.add("Rashid Latif Hospital");
        items.add("Medi Consultant Avenue");
        items.add("My Dentist Clinic");
        items.add("Iqbal Clinics");
        items.add("Dental Art");
        items.add("Union Hospital");
        items.add("Imran Dental Surgery");
        items.add("Ruqayya Medical Centre");
        items.add("International Dental hub");
        items.add("Race View Hospital");
        items.add("Doctors Hospital");
        items.add("Hameedah Memorial Hospital");
        items.add("Akram Medical Complex");
        items.add("General Hospital");
        items.add("Bajwa Hospital");
        items.add("Cavalry Hospital");
        items.add("Shalamar Hospital");
        items.add("Fatima Memorial Hospital");
        items.add("Dr. Huma Kayani Clinic");
        items.add("Hameed Latif Hospital");
        items.add("Chugtai Medical Centre");
        items.add("Hameed Latif Hospital");
        items.add("The Vision");
        items.add("Laser Vision Centre");
        items.add("Omar Hospital and Cardiac Centre");
        items.add("National Hospital and Medical Centre");
        items.add("Masood Hospital");
        items.add("Surgimed Hospital");
        items.add("Punjab Institute of Cardiology");
        items.add("Skin Image Skin and Laser Clinic");
        items.add("Kanan Clinic");
        items.add("Zarqa Laser Skin and Wellness Clinic");
        items.add("Sir Ganga Ram hospital");

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
                info.putString("Hos",items.get(position));

                list_byhos hos = new list_byhos();
                hos.setArguments(info);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_replaceable, hos);
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
