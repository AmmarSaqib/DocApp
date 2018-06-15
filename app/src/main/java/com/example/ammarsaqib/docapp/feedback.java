package com.example.ammarsaqib.docapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link feedback.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class feedback extends DialogFragment {

    // email address for mail
    String[] addresses = {"19-11137@formanite.fccollege.edu.pk"};

    // declaring buttons
    Button cancel, send;

    // declaring edit texts
    EditText subject, mail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflating the view
        View feedback = inflater.inflate(R.layout.fragment_feedback,container,false);

        // initialising button
        send = feedback.findViewById(R.id.feedback_send);
        cancel = feedback.findViewById(R.id.feedback_cancel);

        // initialising edit texts
        subject = feedback.findViewById(R.id.feedback_subject_et);
        mail = feedback.findViewById(R.id.feedback_email_et);

        // setting onclicklistener to buttons
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling the method for send the mail
                send_mail();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismissing the dialog
                getDialog().dismiss();
            }
        });
        return feedback;
    }

    public void send_mail()
    {
        /*
        The function creates an intent for sending mail.
        Gets the data from the edit texts put them in the intent
         */

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        // setting the addresses to mail to
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        // setting the subject
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        // setting the body of the text
        intent.putExtra(Intent.EXTRA_TEXT, mail.getText().toString());

        // starting activity
        getActivity().startActivity(intent);
    }

}
