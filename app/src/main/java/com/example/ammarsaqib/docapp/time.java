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

    /**
     * Time picker dialog fragment for showing up a dialog for selecting time for the doctor.
     */

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

        date_time = date_time + " - " + hour + ":" + minutes;


        /**
         * Adding the appointment made to the database using the doctor's id
         * received from the previous fragment i.e. the date picker fragment.
         */
//        db.addAppoint(doc_id,date_time,getdocdata(doc_id));
        db.addAppoint(doc_id,date_time);

        Toast.makeText(getActivity().getApplicationContext(), "Appointment added to my appoinments", Toast.LENGTH_SHORT).show();
    }

}
