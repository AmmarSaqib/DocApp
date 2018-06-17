package com.example.ammarsaqib.docapp;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class myappoint extends Fragment {

    DBManager db;
    ListView myAppoint;
    Cursor cursor;
    myappointCursorAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBManager(getActivity().getApplicationContext());

        cursor = db.getAppoint();

        Log.d("cursor count", cursor.getCount()+"");

        adapter = new myappointCursorAdapter(getActivity().getApplicationContext(),cursor);

    }

    public myappoint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myappoint, container, false);

        myAppoint = view.findViewById(R.id.list_myappoint);

        myAppoint.setAdapter(adapter);

        return view;
    }

}
