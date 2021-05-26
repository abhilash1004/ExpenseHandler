package com.mad_lab_project.expense_handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mad_lab_project.expense_handler.activities.AddExpenses;
import com.mad_lab_project.expense_handler.activities.Login_Activity;
import com.mad_lab_project.expense_handler.activities.SetGoalsActivity;
import com.mad_lab_project.expense_handler.activities.displayExpenditure;
import com.mad_lab_project.expense_handler.databases.GoalsDatabase;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    CardView cardnew;
    CardView cardedit;
    CardView carddisplay;
    CardView cardlogout;
    TextView tvGoalsAmount;
    TextView tvExpenditureAmount;
    TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cardnew = findViewById(R.id.cardviewNew);
        cardedit = findViewById(R.id.cardviewEdit);
        carddisplay = findViewById(R.id.cardviewDisplay);
        cardlogout = findViewById(R.id.cardviewLogout);
        tvGoalsAmount = findViewById(R.id.TextViewGoalsAmount);
        tvExpenditureAmount = findViewById(R.id.TextViewExpenditureAmount);
        tvBalance = findViewById(R.id.TextViewBalanceAmount);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        Log.d("Present Date",month+" "+year);

        GoalsDatabase gdb = new GoalsDatabase(this);
        SQLiteDatabase db = gdb.getReadableDatabase();
        String query = "select * from "+GoalsDatabase.TABLE_GOALS + " where "+GoalsDatabase.COLUMN_USER_ID + " = '"+Login_Activity.loggedInUserId
                + "' and " + GoalsDatabase.COLUMN_YEAR + "=" + year + " and " + GoalsDatabase.COLUMN_MONTH + "=" + month;

        Log.d("Query",query);
        Cursor cursor=db.rawQuery(query,null);
        int x = 1,bal=0,spent=0,budget=0;
        if(cursor.moveToFirst()){
            x = 0;
            do{
                budget += cursor.getInt(1);
                spent += cursor.getInt(2);
            }while (cursor.moveToNext());
        }
        if(x==1){
            tvGoalsAmount.setText("Please Set a Goal");
            tvBalance.setText("Please Set a Goal");
            tvExpenditureAmount.setText("Please Set a Goal");
        }
        else{
            tvGoalsAmount.setText(budget+"");
            tvExpenditureAmount.setText(spent+"");
            tvBalance.setText((budget-spent)+"");
        }
        cardnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, SetGoalsActivity.class);
                startActivity(intent);
            }
        });

        cardedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, AddExpenses.class);
                startActivity(intent);
            }
        });

        carddisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, displayExpenditure.class);
                startActivity(intent);
            }
        });

        cardlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_Activity.loggedInUserId = "";
                finish();
                System.exit(0);
            }
        });

    }
}