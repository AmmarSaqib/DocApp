package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link doc_detail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class doc_detail extends Fragment {

    // declaring text views
    TextView id, name, spec, hospital, fee;

    // declaring buttons
    Button makeApoint, cancel;

    // declaring image view
    ImageView pic;

    // data base object
    DBManager db;

    // bundle object
    Bundle info;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialising db object
        db = new DBManager(getActivity().getApplicationContext());

        info = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doc_detail, container, false);

        // initialising views in the layout
        initialise_stuff(view);

        // set values to the view
        int id = info.getInt("doctor_id");
        Log.d("Bundle", "Bundle int: "+id);
        set_values(id);
        return view;
    }

    public void set_values(int id)
    {
        String dbName, dbSpec, dbHospital;
        int dbFee;

        // getting cursor for data
        Cursor data = db.getById(id);

        boolean res = data.moveToFirst();
        Log.d("cursor index",data.getCount() + "");

        dbName = data.getString(data.getColumnIndex("Name"));
//        dbName = data.getString(1);
        dbSpec = data.getString(data.getColumnIndex("Specialization"));
        dbHospital = data.getString(data.getColumnIndex("Hospital"));
        dbFee = data.getInt(data.getColumnIndex("Fee"));

        // setting values
        this.id.setText(Integer.toString(id));
        this.name.setText(dbName);
        this.hospital.setText(dbHospital);
        this.fee.setText(Integer.toString(dbFee));
        this.spec.setText(dbSpec);

    }

    public void initialise_stuff(View view)
    {
        // initialising text views
        id = view.findViewById(R.id.info_doc_id);
        name = view.findViewById(R.id.info_doc_name);
        spec = view.findViewById(R.id.info_doc_spec);
        hospital = view.findViewById(R.id.info_doc_hosname);
        fee = view.findViewById(R.id.info_doc_fee);

        // initialising buttons
        makeApoint = view.findViewById(R.id.make_appoint);
        cancel = view.findViewById(R.id.cancel);

        makeApoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the appointment detail dialog fragment
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // closing the current fragment
                getActivity().onBackPressed();
            }
        });

        // initialising image view
        pic = view.findViewById(R.id.info_doc_pic);

    }

}
