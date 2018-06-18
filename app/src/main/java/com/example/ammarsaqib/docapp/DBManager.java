package com.example.ammarsaqib.docapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    /**
     * Class for interacting with the database.
     */

    // database name
    public static final String DBNAME = "docwhat.db";

    public DBManager(Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table for doctor information
        String sql = "CREATE TABLE doctor" +
                "(_id integer PRIMARY KEY, Name text, Specialization text, Fee integer, Hospital text, ContactNo text, Img text)";
        db.execSQL(sql);

        // creating table for appointment record
        sql = "CREATE TABLE appointments" +
                "(_id integer, Date text, FOREIGN KEY(_id) REFERENCES doctor(_id))";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no need for this at this moment

    }

    public void insert_data(doctor_data data)
    {
        /**
         * Inserting data into the doctor table
         */

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name",data.getName());
        cv.put("Fee",data.getFee());
        cv.put("Hospital",data.getHospital());
        cv.put("Specialization",data.getSpecialization());
        cv.put("ContactNo",data.getContact_no());
        cv.put("Img",data.getImg());

        db.insert("doctor", null, cv);
    }

    public Cursor getAll()
    {
        /**
         * Executes a query to get all the doctor in the doctor table
         * return: Cursor -> pointing to the data
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM doctor", null);
        return cursor;
    }

    public Cursor getById(int id)
    {
        /**
         * int id -> id of the doctor to run query for
         * Executes a query to get a doctor's record with a certain id passed to the function.
         * return: Cursor -> pointing to the data
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM doctor WHERE _id ="+id ,null);
        return cursor;
    }

    public Cursor getBySpec(String spec)
    {
        /**
         * String spec -> specialization of the doctor to run query for
         * Executes a query to get all doctor records with a certain Specialization passed to the function.
         * return: Cursor -> pointing to the data
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM doctor WHERE Specialization ='"+spec+"'" ,null);
        return cursor;
    }

    public Cursor getByHos(String hos)
    {
        /**
         * String hos -> Hospital of the doctor to run query for
         * Executes a query to get all doctor records with a certain Hospital passed to the function.
         * return: Cursor -> pointing to the data
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM doctor WHERE Hospital ='"+hos+"'" ,null);
        return cursor;
    }

    public void addAppoint(int id, String date)
    {
        /**
         * int id -> id of the doctor
         * String date -> date and time of the appointment
         * The function adds the id and date in the appointments table
         */
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("_id",id);
        cv.put("Date",date);

        db.insert("appointments", null, cv);
    }

    public Cursor getAppoint()
    {
        /**
         * The function runs query to get all the doctor's information
         * whose id are in the appointment table
         * return: Cursor -> pointing to the records
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT appointments._id, appointments.Date," +
                "doctor.Name, doctor.Specialization, doctor.Fee, doctor.Hospital, ContactNo " +
                " FROM appointments " +
                " JOIN doctor " +
                "ON appointments._id = doctor._id",null);
        return cursor;
    }

    public Cursor getById_withDate(int id)
    {
        /**
         * The function runs query to get all the doctor's information
         * whose id are in the appointment table
         * return: Cursor -> pointing to the records
         */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT appointments._id, appointments.Date," +
                "doctor.Name, doctor.Specialization, doctor.Fee, doctor.Hospital, doctor.ContactNo , doctor.Img" +
                " FROM appointments " +
                " JOIN doctor " +
                "ON appointments._id = doctor._id " +
                "WHERE appointments._id = '" + id + "'" ,null);
        return cursor;
    }

    public void delById(int id)
    {
        /**
         * int id -> id of the doctor to be removed
         * The function deletes a record in the appointment table with the id passed
         */
        SQLiteDatabase db = getWritableDatabase();
//        db.rawQuery("DELETE FROM appointments" +
//                " WHERE _id = '" + id + "';", null);
        db.delete("appointments","_id = '" + id + "'",null);
    }

}
