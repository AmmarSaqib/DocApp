package com.example.ammarsaqib.docapp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileRead {

    String line;
    String [] data;
    InputStream fileReader;
    BufferedReader bufferedReader;
    DBManager db;
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/assests/" ;

    public FileRead(String filename, Context context) {

        // initialising the database manager object
        db = new DBManager(context);

        try {
            // file reader object for reading the file
            fileReader = context.getAssets().open(filename);

            // wrapping the filereader in buffer reader
            bufferedReader = new BufferedReader(new InputStreamReader(fileReader));

            // reading the data and adding to database
            addToDatabase();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToDatabase()
    {
        try
        {
            Log.d("fileread", "addToDatabase: try block");
            while((line = bufferedReader.readLine()) != null) {

                // spliting the line using the comma as delimiter
                data = line.split(",",0);

                Log.d("extracted data", data[0]+data[2]);

                // creating an object for the data of a doctor
                doctor_data docData = new doctor_data(data[0],data[1],Integer.parseInt(data[2]),data[3],data[4]);

                // passing the doctor info object to the db method for insertion
                db.insert_data(docData);

            }

            // closing file reader buffer
            bufferedReader.close();
        }
        catch (IOException e)
        {

        }
    }

}
