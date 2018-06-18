package com.example.ammarsaqib.docapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link doc_detail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class doc_detail extends Fragment {

    /**
     * The fragment shows the information of the doctor.
     * Provides options for making and appointment
     * or
     * Cancel and go back
     */

    // declaring text views
    TextView id, name, spec, hospital, fee, cnum;

    // declaring image view
    ImageView docImg;

    // declaring buttons
    Button makeApoint, cancel;

    // declaring image view
    ImageView pic;

    // data base object
    DBManager db;

    // bundle object
    Bundle info;
    ////////////////////

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
        Log.d("Bundle", "Bundle int: " + id);
        set_values(id);
        return view;
    }

    public void set_values(int id)
    {
        String dbName, dbSpec, dbHospital, dbImg, dbNum;
        int dbFee;

        // getting cursor for data
        Cursor data = db.getById(id);

        boolean res = data.moveToFirst();
        Log.d("cursor index",data.getCount() + "");

        dbName = data.getString(data.getColumnIndex("Name"));
        dbSpec = data.getString(data.getColumnIndex("Specialization"));
        dbHospital = data.getString(data.getColumnIndex("Hospital"));
        dbFee = data.getInt(data.getColumnIndex("Fee"));
        dbImg = data.getString(data.getColumnIndex("Img"));
        dbNum = data.getString(data.getColumnIndex("ContactNo"));

        // setting values
        this.id.setText(Integer.toString(id));
        this.name.setText(dbName);
        this.hospital.setText(dbHospital);
        this.fee.setText(Integer.toString(dbFee));
        this.spec.setText(dbSpec);
        this.cnum.setText(dbNum);

        // for image
        if (dbImg != "")
            Picasso.get().load(dbImg).into(docImg);

    }

    public void initialise_stuff(View view)
    {
        // initialising text views
        id = view.findViewById(R.id.info_doc_id);
        name = view.findViewById(R.id.info_doc_name);
        spec = view.findViewById(R.id.info_doc_spec);
        hospital = view.findViewById(R.id.info_doc_hosname);
        fee = view.findViewById(R.id.info_doc_fee);
        docImg = view.findViewById(R.id.info_doc_pic);
        cnum = view.findViewById(R.id.info_doc_cnum);

        // initialising buttons
        makeApoint = view.findViewById(R.id.make_appoint);
        cancel = view.findViewById(R.id.cancel);

        makeApoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the date and time picker
                // getting the date and time

                Bundle docid = new Bundle();
                docid.putInt("doc_id",info.getInt("doctor_id"));

                date newFragment = new date();
                newFragment.setArguments(docid);

                newFragment.show(getFragmentManager(), "datePicker");

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
