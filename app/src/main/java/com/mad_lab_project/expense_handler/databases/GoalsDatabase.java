package com.mad_lab_project.expense_handler.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GoalsDatabase extends SQLiteOpenHelper {
    public static final String TABLE_GOALS = "Goals";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_AMOUNT_CAP = "amountCap";
    public static final String COLUMN_AMOUNT_SPENT = "amountSpent";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = " month";

    public GoalsDatabase(@Nullable Context context) { //@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "expenseHandler.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "create table " + TABLE_GOALS + " (" + COLUMN_USER_ID + " TEXT, " + COLUMN_AMOUNT_CAP + " int, " +
                COLUMN_AMOUNT_SPENT + " int," + COLUMN_MONTH + " int," + COLUMN_YEAR + " int, primary key ( " +
                COLUMN_USER_ID + "," + COLUMN_MONTH + ", " + COLUMN_YEAR + " ), " + "foreign key ( " + COLUMN_USER_ID +
                " ) references " + UserPassDatabase.TABLE_USER_PASS + "("+UserPassDatabase.COLUMN_USERID + ") )";
        //db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addGoals(String userid,int amountCap,int year,int month){
        if(userid.length()==0)
            return false;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_ID,userid);
        cv.put(COLUMN_AMOUNT_CAP,amountCap);
        cv.put(COLUMN_AMOUNT_SPENT,0);
        cv.put(COLUMN_MONTH,month);
        cv.put(COLUMN_YEAR,year);
        SQLiteDatabase db = getWritableDatabase();
        if(db.insert(TABLE_GOALS,null,cv)==-1) {
            db.close();
            return false;
        }
        db.close();

        return true;
    }
}
