package com.example.ammarsaqib.docapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public static final String DBNAME = "docwhat.db";

    public DBManager(Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table for doctor information
        String sql = "CREATE TABLE doctor" +
                "(_id integer, Name text, Specialization text, Fee integer, Hospital text, ContactNo text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no need for this at this moment

    }

    public void insert_data(doctor_data data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name",data.getName());
        cv.put("Fee",data.getFee());
        cv.put("Hospital",data.getHospital());
        cv.put("Specialization",data.getSpecialization());
        cv.put("ContactNo",data.getContact_no());

        db.insert("doctor", null, cv);
    }

    public Cursor getAll()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM doctor", null);
        return cursor;
    }

}
