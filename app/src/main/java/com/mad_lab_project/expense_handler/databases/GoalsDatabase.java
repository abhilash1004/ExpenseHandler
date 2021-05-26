package com.mad_lab_project.expense_handler.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mad_lab_project.expense_handler.activities.Login_Activity;

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void createTable(SQLiteDatabase db){
        String createTableStatement = "create table " + TABLE_GOALS + " (" + COLUMN_USER_ID + " TEXT, " + COLUMN_AMOUNT_CAP + " int, " +
                COLUMN_AMOUNT_SPENT + " int," + COLUMN_MONTH + " int," + COLUMN_YEAR + " int, primary key ( " +
                COLUMN_USER_ID + "," + COLUMN_MONTH + ", " + COLUMN_YEAR + " ), " + "foreign key ( " + COLUMN_USER_ID +
                " ) references " + UserPassDatabase.TABLE_USER_PASS + "("+UserPassDatabase.COLUMN_USERID + ") )";
        db.execSQL(createTableStatement);



    }
    public boolean addGoals(String userid,int amountCap,int year,int month){
        if(userid.length()==0 && month==0)
            return false;

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_ID,userid);
        cv.put(COLUMN_AMOUNT_CAP,amountCap);
        cv.put(COLUMN_AMOUNT_SPENT,amountSpent(year,month));
        cv.put(COLUMN_MONTH,month);
        cv.put(COLUMN_YEAR,year);
        SQLiteDatabase db = getWritableDatabase();
        String rawQuery = "delete from " + GoalsDatabase.TABLE_GOALS;
        db.execSQL(rawQuery);

        if(db.insert(TABLE_GOALS,null,cv)==-1) {
            db.close();
            return false;
        }
        db.close();

        return true;
    }
    int amountSpent(int year,int month){
        int moneySpent = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+ ExpenditureDatabase.Table_Expenses;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int amt = cursor.getInt(4);
                String date = cursor.getString(2);
                int extractedMonth = Integer.parseInt(date.substring(5,7));
                int extractedYear = Integer.parseInt(date.substring(0,4));
                String userId = cursor.getString(1);
                if(extractedMonth==month && extractedYear==year && userId.compareTo(Login_Activity.loggedInUserId)==0)
                    moneySpent+=amt;
            }while (cursor.moveToNext());
        }
        return  moneySpent;
    }
}
