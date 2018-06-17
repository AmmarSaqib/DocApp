package com.example.ammarsaqib.docapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_byhos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_byhos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_byhos extends DialogFragment {

    ListView listDoc;
    DBManager db;
    listingCursorAdapter adapter;
    Cursor cursor;
    View view;
    Bundle b;

    public list_byhos() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = getArguments();

        String spec = b.getString("Hos");

        // initialising database
        db = new DBManager(getActivity().getApplicationContext());

        // getting the cursor for the data from the database
        cursor = db.getByHos(spec);

        // initialising the cursor adapter
        adapter = new listingCursorAdapter(getActivity().getApplicationContext(),cursor);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_all, container, false);

        // initialising the list view
        listDoc = view.findViewById(R.id.list_all_lv);

        listDoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(parent, view, position, id);
            }
        });

        // setting the adapter
        listDoc.setAdapter(adapter);

        return view;
    }

    public void onClick(AdapterView<?> parent, View view, int position, long id)
    {

        // finding the text view for id and getting the id of the doctor
        TextView doc_id = view.findViewById(R.id.list_all_doc_id);
        int docId = Integer.parseInt(doc_id.getText().toString().substring(4));

        Log.d("list view onclick", "doc id:" + docId );

        // creating a bundle
        Bundle info = new Bundle();
        info.putInt("doctor_id",docId);

        // creating the doctor detail fragment object
        doc_detail docDetail = new doc_detail();

        docDetail.setArguments(info);

        // calling the next fragment to show doctor details
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_replaceable, docDetail);
        ft.addToBackStack("doctor_detail");
        ft.commit();
    }

}
