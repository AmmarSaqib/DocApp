package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

public class listingCursorAdapter extends CursorAdapter {

    public listingCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_all_doc, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // declaring and initialising text view
        TextView doc_name = view.findViewById(R.id.list_all_doc_name);
        TextView doc_spec = view.findViewById(R.id.list_all_doc_spec);
        TextView hos_name = view.findViewById(R.id.list_all_doc_hosname);
        TextView doc_id = view.findViewById(R.id.list_all_doc_id);

        // for image
        ImageView doc_img = view.findViewById(R.id.list_all_doc_img);

        // declaring variable for data
        String docName, docSpec, hosName, img;
        int id;

        // getting data from cursor and placing them in variables
        id = cursor.getInt(cursor.getColumnIndex("_id"));
        docName = cursor.getString(cursor.getColumnIndex("Name"));
        docSpec = cursor.getString(cursor.getColumnIndex("Specialization"));
        hosName = cursor.getString(cursor.getColumnIndex("Hospital"));

        // for image
        img = cursor.getString(cursor.getColumnIndex("Img"));

        // setting values in text views
        doc_name.setText(docName);
        doc_spec.setText(docSpec);
        hos_name.setText(hosName);
        doc_id.setText("id# "+ id);

        if (img != "")
            Picasso.get().load(img).into(doc_img);

    }

}
