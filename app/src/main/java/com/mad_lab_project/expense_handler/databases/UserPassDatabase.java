package com.mad_lab_project.expense_handler.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserPassDatabase extends SQLiteOpenHelper {
    public static final String TABLE_USER_PASS = "USER_PASS_TABLE";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_USERID = "USERID";
    public static final String COLUMN_PASSWORD = "PASSWORD";

    public UserPassDatabase(@Nullable Context context) {//, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "expenseHandler.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "create table " + TABLE_USER_PASS + " ( " + COLUMN_EMAIL + " VARCHAR(254) NOT NULL , " + COLUMN_USERID
                + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT NOT NULL )";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(String emailId,String userid,String password ) {
        if(emailId.length()==0 || userid.length()==0 || password.length()==0)
            return false;
        Log.d("Check 2",emailId + password + userid);
        String tableInsertionStatement = "";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL,emailId);
        cv.put(COLUMN_USERID,userid);
        cv.put(COLUMN_PASSWORD,password);
        long insert =db.insert(TABLE_USER_PASS,null,cv);
        Log.d("check",insert+"");
        db.close();
        if(insert==-1)
            return false;
        return true;
    }
    public boolean checkCredentialsEntered(String emailOruser,String password) {
        if(emailOruser.length()==0 || password.length()==0)
            return false;
        //ArrayList<String> data = new ArrayList<>();
        String query1 = "Select * from " + TABLE_USER_PASS + " where "+ TABLE_USER_PASS+"." + COLUMN_EMAIL + " = '" + emailOruser + "'";
        String query2 = "Select * from " + TABLE_USER_PASS + " where "+ TABLE_USER_PASS+"." + COLUMN_USERID + " = '" + emailOruser + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query1,null);
        if(cursor.moveToFirst()){
            String databasePassword = cursor.getString(2);
            db.close();
            if(password.equals(databasePassword))
                return true;
        }
        cursor = db.rawQuery(query2,null);
        if(cursor.moveToFirst()){
            String databasePassword = cursor.getString(2);
            db.close();
            if(password.equals(databasePassword))
                return true;
            }
        db.close();
        return false;
    }


    public boolean checkuserexists(String emailOrUser) {
        if(emailOrUser.length()==0)
            return false;
        String query1 = "Select * from " + TABLE_USER_PASS + " where "+ TABLE_USER_PASS+"." + COLUMN_EMAIL + " = '" + emailOrUser +"'";
        String query2 = "Select * from " + TABLE_USER_PASS + " where "+ TABLE_USER_PASS+"." + COLUMN_USERID + " = '" + emailOrUser + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query1,null);
        if(cursor.moveToFirst()){
            db.close();
                return true;
        }
        cursor = db.rawQuery(query2,null);
        if(cursor.moveToFirst()){
            db.close();
                return true;
        }
        db.close();
        return false;
    }
}
