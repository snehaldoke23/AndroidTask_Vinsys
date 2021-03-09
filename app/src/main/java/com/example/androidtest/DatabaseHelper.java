package com.example.androidtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "register_db";
    //private final int version;
    //private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }
    //  Table names
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String KEY_VISITOR_FNAME = "first_name";
    private static final String KEY_VISITOR_LNAME = "last_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER= "phone_number";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VISITOR_TABLE = "CREATE TABLE " + TABLE_USER + " (" + KEY_VISITOR_FNAME + " TEXT," + KEY_VISITOR_LNAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PHONE_NUMBER + " TEXT" +")";
        db.execSQL(CREATE_VISITOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    }

    //insertng in database
    public long insert(DataEntry dataEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VISITOR_FNAME,dataEntry.getFname());
        contentValues.put(KEY_VISITOR_LNAME,dataEntry.getLname());
        contentValues.put(KEY_EMAIL,dataEntry.getEmail());
        contentValues.put(KEY_PHONE_NUMBER,dataEntry.getPhoneno());

        long res = db.insert(TABLE_USER , null, contentValues);
        return res;
    }

    public ArrayList<DataEntry> getAllData(){
        ArrayList<DataEntry> dataEntries = new ArrayList<>();

        //select all Query
        String selectQuery = "SELECT * FROM "+ TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataEntry dataEntry = new DataEntry();
                dataEntry.setFname(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_FNAME)));
                dataEntry.setLname(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_LNAME)));
                dataEntry.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                dataEntry.setPhoneno(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)));
                dataEntries.add(dataEntry);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return dataEntries;
    }



    //checking if emai exists
    public boolean chkemail (String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where Email=?", new String[]{Email});
        if(cursor.getCount()>0) return false;
        else return true;


    }
}
