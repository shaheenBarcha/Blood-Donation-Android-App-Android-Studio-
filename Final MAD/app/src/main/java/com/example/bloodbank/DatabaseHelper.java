package com.example.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String db_name = "DBBloodBank";
    public static final String tbl_name = "LoginUser";
    public static final String col_id = "ID";
    public static final String col_name = "name";
    public static final String col_pass = "password";
    public static final String col_username = "username";
    public static final String col_email = "email";
    public static final String col_gender = "gender";
    public static final String col_bgroup = "blood_group";

    public static final String tbl_donor = "UserDonor";
    public static final String id = "ID";
    public static final String col_namee = "name";
    public static final String col_fname = "fname";
    public static final String col_age = "age";
    public static final String col_address = "address";
    public static final String col_number = "number";

    public static final String tbl_userneedblood = "UserNeedBlood";
    public static final String i_d = "ID";
    public static final String patient_name = "pname";
    public static final String patient_cnic = "pcnic";
    public static final String col_hospital = "hospital";
    public static final String attendee_name = "attendant";
    public static final String attendee_number = "attendant_number";
    public static final String patient_number = "patient_no";
    public static final String gender = "gen";
    public static final String bgroup = "b_group";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+tbl_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,username TEXT, email TEXT, password TEXT, gender TEXT, blood_group TEXT)");
        db.execSQL("create table "+tbl_donor+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,fname TEXT, age TEXT, address TEXT, number TEXT)");
        db.execSQL("create table "+tbl_userneedblood+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, patient_name TEXT, cnic TEXT, hospital TEXT, attendee TEXT, attendee_number TEXT, patient_number TEXT, gender TEXT, blood_group TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_name);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_donor);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_userneedblood);

    }

    public boolean insertdata(String name, String username, String email, String password, String gender, String blood_group)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, name);
        contentValues.put(col_username, username);
        contentValues.put(col_email, email);
        contentValues.put(col_pass, password);
        contentValues.put(col_gender, gender);
        contentValues.put(col_bgroup, blood_group);
        long result = db.insert(tbl_name, null, contentValues);

        if(result==-1)
        {
            return false;

        }
        else
        {
            return true;
        }

    }

    public boolean insertdonor(String name, String fname, String age, String address, String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_namee, name);
        contentValues.put(col_fname, fname);
        contentValues.put(col_age, age);
        contentValues.put(col_address, address);
        contentValues.put(col_number, number);

        long result = db.insert(tbl_donor, null, contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            {
                return true;
            }
    }

    public boolean insertbloodneed(String pname, String pcnic, String hospital, String attendant, String attendant_number, String patient_no, String gen, String b_group)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(patient_name, pname);
        contentValues.put(patient_cnic, pcnic);
        contentValues.put(col_hospital, hospital);
        contentValues.put(attendee_name, attendant);
        contentValues.put(attendee_number, attendant_number);
        contentValues.put(patient_number, patient_no);
        contentValues.put(gender, gen);
        contentValues.put(bgroup, b_group);
        long result = db.insert(tbl_userneedblood, null, contentValues);

        if(result==-1)
        {
            return false;

        }
        else
        {
            return true;
        }

    }

    public Cursor getDonor()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor r = db.rawQuery("select * from " + tbl_donor,null);
        return r;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tbl_name,null);
        return res;
    }

    public Cursor validate(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM LoginUser where username ='"+username+"' and password = '"+password+"'",null);
        return rs;
    }

    public Cursor getbloodneed()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rr = db.rawQuery("select * from " + tbl_userneedblood,null);
        return rr;
    }
}
