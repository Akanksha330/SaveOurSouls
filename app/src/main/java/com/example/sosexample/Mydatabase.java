package com.example.sosexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {


    public static final String DB_Name="database1.db";
    public static final String TABLE_NAME="SOS_data";
    //public static final String DB_id="id";
    public static final String  NAME="name";
    public static final String NUMBER="number";


    public Mydatabase(@Nullable Context context) {
        super(context, DB_Name,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (name TEXT," + "number TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DELETE FROM " + TABLE_NAME);
        onCreate(db);

    }

    public boolean InsertDB(String s1,String s2)
    {
       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,s1);
        cv.put(NUMBER,s2);

        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)

            return false;
        else
            return true;
    }
    public Cursor ViewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

    public void deletedata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME);

    }


}


























