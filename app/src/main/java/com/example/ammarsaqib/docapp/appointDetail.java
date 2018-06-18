package com.example.ammarsaqib.docapp;

import android.support.v4.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class appointDetail extends DialogFragment {

    Button close, cancel_appoint;
    TextView name, hospital, specialization, fee, date, docID, cnum;
    ImageView  docImg;
    Bundle id;
    DBManager db;
    Cursor cursor;
    int doc_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialising bundle (by getting arguments and getting the doctor's id
        id = getArguments();
        doc_id = id.getInt("doctor_id");

        // initialising database object
        db =  new DBManager(getActivity().getApplicationContext());

        // getting data of a doctor with a certain id
        cursor = db.getById_withDate(doc_id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflating the view
        View view = inflater.inflate(R.layout.fragment_appoint_detail, container, false);

        // initialising the text views and the buttons
        initialise_stuff(view);

        // set values in the text views
        set_stuff();

        return view;
    }

    public void set_stuff()
    {
        String dbName, dbSpec, dbHospital, dbDate, dbImg, dbNum;
        int dbFee;

        boolean res = cursor.moveToFirst();
        Log.d("cursor index",cursor.getCount() + "");

        dbName = cursor.getString(cursor.getColumnIndex("Name"));
        dbSpec = cursor.getString(cursor.getColumnIndex("Specialization"));
        dbHospital = cursor.getString(cursor.getColumnIndex("Hospital"));
        dbFee = cursor.getInt(cursor.getColumnIndex("Fee"));
        dbDate = cursor.getString(cursor.getColumnIndex("Date"));
        dbImg = cursor.getString(cursor.getColumnIndex("Img"));
        dbNum = cursor.getString(cursor.getColumnIndex("ContactNo"));

        // setting values
        this.docID.setText(Integer.toString(doc_id));
        this.name.setText(dbName);
        this.hospital.setText(dbHospital);
        this.fee.setText(Integer.toString(dbFee));
        this.specialization.setText(dbSpec);
        this.date.setText(dbDate);
        this.cnum.setText(dbNum);

        // for image
        if (dbImg != "")
            Picasso.get().load(dbImg).into(docImg);
    }

    public void initialise_stuff(View view)
    {
        // initialising text views
        docID = view.findViewById(R.id.appoint_det_id);
        name = view.findViewById(R.id.appoint_det_name);
        hospital = view.findViewById(R.id.appoint_det_hosname);
        specialization = view.findViewById(R.id.appoint_det_spec);
        fee = view.findViewById(R.id.appoint_det_fee);
        date = view.findViewById(R.id.appoint_det_date);
        docImg = view.findViewById(R.id.appoint_det_pic);
        cnum = view.findViewById(R.id.appoint_det_cnum);

        // initialising buttons
        close = view.findViewById(R.id.close);
        cancel_appoint = view.findViewById(R.id.cancel_appoint);

        // setting onclick listeners to the buttons
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        cancel_appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a function to delete the record of the
                // doctor from the appointments table in the database

                db.delById(doc_id);

                // dismissing after deleting record
                getDialog().dismiss();
            }
        });
    }

}
