package com.example.ammarsaqib.docapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link date.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link date#newInstance} factory method to
 * create an instance of this fragment.
 */
public class date extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Bundle info;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        info = new Bundle();
        info.putInt("doc_id",getArguments().getInt("doc_id"));

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        Log.d("date",  day + " " + month );

        String year1 = String.valueOf(year);
        String month1 = String.valueOf(month + 1);
        String day1 = String.valueOf(day);

        String date = day1 + "/" + month1 + "/" + year1;

        time newFragment = new time();

        info.putString("date",date);
        newFragment.setArguments(info);

        newFragment.show(getFragmentManager(), "timePicker");

    }


}
