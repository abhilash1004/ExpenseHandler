package com.mad_lab_project.expense_handler.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ExpenditureDatabase extends SQLiteOpenHelper {

    public static final String Table_Expenses = "Expenses";
    public static final String COLUMN_EXPENDITURE_ID = "ExpenditureId";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TRANSACTION_DATE = "transactionDate";

    public ExpenditureDatabase(@Nullable Context context) {
        super(context, "expenseHandler.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "create table " + Table_Expenses + " ( " + COLUMN_EXPENDITURE_ID + " int PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " TEXT , " +
                COLUMN_TRANSACTION_DATE + " Date, " + COLUMN_CATEGORY + " TEXT , " + COLUMN_AMOUNT + " int " +
                "  , foreign key ( " + COLUMN_USER_ID + " ) References " + UserPassDatabase.TABLE_USER_PASS + "( " + UserPassDatabase.COLUMN_USERID +" )  )";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addExpenditure(String userid, String category,String date,int amt){
        if(!(userid.length()>0 && category.length()>0 && date.length()>0)){
            return false;
        }
        Log.d("ValuesCameForAddition ", "UserId: " + userid + " catgory: " + category + "date: " + date + " amount: " + amt);
        //String tableInsertStatement = "";
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_ID,userid);
        cv.put(COLUMN_AMOUNT,amt);
        cv.put(COLUMN_CATEGORY,category);
        cv.put(COLUMN_TRANSACTION_DATE,date);
        if(db.insert(Table_Expenses,null,cv)==-1) {
            db.close();
            return false;
        }
        db.close();
        return true;
    }

}
