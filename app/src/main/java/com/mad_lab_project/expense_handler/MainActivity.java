package com.mad_lab_project.expense_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.mad_lab_project.expense_handler.activities.AddExpenses;
import com.mad_lab_project.expense_handler.activities.Login_Activity;
import com.mad_lab_project.expense_handler.activities.displayExpenditure;
import com.mad_lab_project.expense_handler.databases.ExpenditureDatabase;
import com.mad_lab_project.expense_handler.databases.UserPassDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ExpenditureDatabase edb = new ExpenditureDatabase(this);
//        SQLiteDatabase db = edb.getWritableDatabase();
//
//         String Table_Expenses = "Expenses";
//         String COLUMN_EXPENDITURE_ID = "ExpenditureId";
//         String COLUMN_USER_ID = "userId";
//         String COLUMN_AMOUNT = "amount";
//         String COLUMN_CATEGORY = "category";
//         String COLUMN_TRANSACTION_DATE = "transactionDate";
//
//        String createTableStatement = "create table " + Table_Expenses + " ( " + COLUMN_EXPENDITURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " TEXT , " +
//                COLUMN_TRANSACTION_DATE + " Date, " + COLUMN_CATEGORY + " TEXT , " + COLUMN_AMOUNT + " int " +
//                "  , foreign key ( " + COLUMN_USER_ID + " ) References " + UserPassDatabase.TABLE_USER_PASS + "( " + UserPassDatabase.COLUMN_USERID +" )  )";
//        db.execSQL(createTableStatement);
//
//        db.close();


        Intent loginPageIntent = new Intent(this, Login_Activity.class);
        //Intent loginPageIntent = new Intent(this, displayExpenditure.class);
        startActivity(loginPageIntent);
    }
}