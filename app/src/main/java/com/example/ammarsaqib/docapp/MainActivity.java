package com.example.ammarsaqib.docapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //wasup nig
//mhmhmm
    
    LinearLayout main_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finding the view in the MainActivity xml
        main_ll = findViewById(R.id.ll_main);

        // calling the function for showing up the TextView dynamically
        createTextView();
        createTextView();
        createTextView();
        createTextView();
        createTextView();
        createTextView();


    }

    public void createTextView()
    {
        // declaring and initialising a TextView
        TextView tv = new TextView(this);

        // deciding layout parameters
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // setting the layout parameters to the TextView
        tv.setLayoutParams(lp);

        // setting text in the TextView
        tv.setText("Hi tesing this thing");

        main_ll.addView(tv);
    }

}


;