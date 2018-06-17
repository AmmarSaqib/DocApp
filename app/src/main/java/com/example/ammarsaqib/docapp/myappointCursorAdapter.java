package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class myappointCursorAdapter extends CursorAdapter {

    public myappointCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_myappoint, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // declaring and initialising text view
        TextView doc_name = view.findViewById(R.id.myappoint_doc_name);
        TextView doc_spec = view.findViewById(R.id.myappoint_doc_spec);
        TextView hos_name = view.findViewById(R.id.myappoint_doc_hosname);
        TextView dateTv = view.findViewById(R.id.myappoint_date);

        // declaring variable for data
        String docName, docSpec, hosName, date;
        int id;

        // getting data from cursor and placing them in variables
        docName = cursor.getString(cursor.getColumnIndex("Name"));
        docSpec = cursor.getString(cursor.getColumnIndex("Specialization"));
        hosName = cursor.getString(cursor.getColumnIndex("Hospital"));
        date = cursor.getString(cursor.getColumnIndex("Date"));

        // setting values in text views
        doc_name.setText(docName);
        doc_spec.setText(docSpec);
        hos_name.setText(hosName);
        dateTv.setText(date);

    }
}
