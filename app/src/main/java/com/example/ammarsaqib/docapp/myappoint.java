package com.example.ammarsaqib.docapp;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class myappoint extends Fragment {

    DBManager db;
    ListView myAppoint = null;
    Cursor cursor;
    myappointCursorAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBManager(getActivity().getApplicationContext());

//        cursor = db.getAppoint();

//        adapter = new myappointCursorAdapter(getActivity().getApplicationContext(),cursor);

//        adapter.notifyDataSetChanged();
    }

    public myappoint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myappoint, container, false);

        // initialising the list view
        myAppoint = view.findViewById(R.id.list_myappoint);

        // setting an onclicklistener to the items in the list view
        myAppoint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(parent, view, position, id);
            }
        });

        cursor = db.getAppoint();
        adapter = new myappointCursorAdapter(getActivity().getApplicationContext(),cursor);
        adapter.notifyDataSetChanged();

        // setting adapter to view
        myAppoint.setAdapter(adapter);

        return view;
    }

    public void onClick(AdapterView<?> parent, View view, int position, long id)
    {
        // finding the text view for id and getting the id of the doctor
        TextView doc_id = view.findViewById(R.id.myappoint_doc_id);
        int docId = Integer.parseInt(doc_id.getText().toString());

        Log.d("list view onclick", "doc id:" + docId );

        // creating a bundle
        Bundle info = new Bundle();
        info.putInt("doctor_id",docId);

        // creating object for the my appointment detail
        appointDetail appointDetail = new appointDetail();

        // setting arguments for the appointment detail
        appointDetail.setArguments(info);

        // showing the dialog
        FragmentManager fm = getFragmentManager();
        appointDetail.show(fm, "my_appoint_detail");
    }

    public void updateList()
    {
        cursor = db.getAppoint();
        adapter = new myappointCursorAdapter(getActivity().getApplicationContext(),cursor);
        adapter.notifyDataSetChanged();
    }

}
