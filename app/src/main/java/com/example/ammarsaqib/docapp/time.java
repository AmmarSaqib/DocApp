package com.example.ammarsaqib.docapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

public class time extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Bundle info;
    String hour, minutes;
    DBManager db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBManager(getActivity().getApplicationContext());

        info = getArguments();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        String date_time;

        hour = hourOfDay + "";
        minutes = minute + "";

        int doc_id = info.getInt("doc_id");

        date_time = info.getString("date");

        date_time = date_time + " " + hour + ":" + minutes;

        db.addAppoint(doc_id,date_time,getdocdata(doc_id));

        Toast.makeText(getActivity().getApplicationContext(), "Appointment added to my appoinments", Toast.LENGTH_SHORT).show();
    }

    public doctor_data getdocdata(int id)
    {
        Cursor dat = db.getById(id);

        String name, spec, hos, cnum;
        int fee;

        dat.moveToFirst();

        name = dat.getString(dat.getColumnIndex("Name"));
        spec = dat.getString(dat.getColumnIndex("Specialization"));
        hos = dat.getString(dat.getColumnIndex("Hospital"));
        cnum = dat.getString(dat.getColumnIndex("ContactNo"));
        fee = dat.getInt(dat.getColumnIndex("Fee"));

        doctor_data dData = new doctor_data(name,spec,fee,hos,cnum);

        return dData;

    }

}
