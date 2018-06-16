package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_all.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_all#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_all extends DialogFragment {

    ListView listDoc;
    DBManager db;
    listingCursorAdapter adapter;
    Cursor cursor;

    public list_all() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialising database
        db = new DBManager(getActivity().getApplicationContext());

        // getting the cursor for the data from the database
        cursor = db.getAll();

        // initialising the cursor adapter
        adapter = new listingCursorAdapter(getActivity().getApplicationContext(),cursor);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_all, container, false);

        // initialising the list view
        listDoc = view.findViewById(R.id.list_all_lv);

        // setting the adapter
        listDoc.setAdapter(adapter);

        return view;
    }

}
