package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mad_lab_project.expense_handler.R;
import com.mad_lab_project.expense_handler.adapters.DatePicker;
import com.mad_lab_project.expense_handler.adapters.ExpenditureListAdapter;
import com.mad_lab_project.expense_handler.databases.ExpenditureDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class displayExpenditure extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView tvDate;
    ImageButton btnDateDecrement;
    ImageButton btnDateIncrement;
    //RecyclerView recyclerViewExpenditures;
    ListView ListViewExpenditures;

    String selectedDate = "";
    private Calendar mCalender;
    private SimpleDateFormat dateFormat;
    Date date;
    ArrayList<String> categoriesList;
    ArrayList<String> amtList;
    Date todaysDate;

//    public displayExpenditure() {
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expenditure);

        tvDate = (TextView) findViewById(R.id.textViewDateDisplay);
        btnDateDecrement = (ImageButton) findViewById(R.id.buttonDateDecrement);
        btnDateIncrement = (ImageButton) findViewById(R.id.buttonDateIncrement);
        //recyclerViewExpenditures = (RecyclerView) findViewById(R.id.recylclerViewExpenditures);
        ListViewExpenditures = (ListView) findViewById(R.id.listViewExpenditure);
        categoriesList = new ArrayList<>();
        amtList = new ArrayList<>();

        mCalender = Calendar.getInstance();
        date = mCalender.getTime();
        todaysDate = mCalender.getTime();
        dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
        selectedDate = dateFormat.format(date);
        tvDate.setText(selectedDate);

        displayExpenditureList();



        btnDateDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDecrement();
                displayExpenditureList();
            }
        });

        btnDateIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x1 = dateFormat.format(date);
                String x2 = dateFormat.format(todaysDate);
                if(x1.compareTo(x2) == 0){
                    return;
                }
                dateIncrement();
                displayExpenditureList();
            }
        });


    }
    final void displayExpenditureList() {
        ExpenditureDatabase dbED = new ExpenditureDatabase(this);
        SQLiteDatabase db = dbED.getReadableDatabase();
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = dt.format(date);
        categoriesList.clear();
        amtList.clear();


        String query="select "+ExpenditureDatabase.COLUMN_AMOUNT+", "+ExpenditureDatabase.COLUMN_CATEGORY+" from "+
                        ExpenditureDatabase.Table_Expenses + " where " + ExpenditureDatabase.COLUMN_USER_ID + " = '"+Login_Activity.loggedInUserId +
                        "' and " + ExpenditureDatabase.COLUMN_TRANSACTION_DATE + " = '" + tempDate + "'";

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                String cat =cursor.getString(1);
                int amt = cursor.getInt(0);
                Log.d("Cat and Amt","category = "+cat+" ,Amt ="+amt);
                categoriesList.add(cat);
                amtList.add(amt+"");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        display();

    }
    void display() {
        ExpenditureListAdapter adapter = new ExpenditureListAdapter(this,categoriesList,amtList);
        ListViewExpenditures.setAdapter(adapter);
    }


    final void dateDecrement(){
        date.setTime(date.getTime()-24*60*60*1000);
        selectedDate = dateFormat.format(date);
        tvDate.setText(selectedDate);
    }
    final void dateIncrement(){
        date.setTime(date.getTime()+24*60*60*1000);
        selectedDate = dateFormat.format(date);
        tvDate.setText(selectedDate);
    }

    public void dateOnClick(View v){
        DatePicker mDatePickerDialogFragment = new DatePicker();
        mDatePickerDialogFragment.show(getSupportFragmentManager(),"DATE PICK");
//        Toast.makeText(this,"Hello I am Clicked",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        selectedDate = dateFormat.format(mCalender.getTime());
        date = mCalender.getTime();
        tvDate.setText(selectedDate);
        displayExpenditureList();
    }
}