package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mad_lab_project.expense_handler.R;
import com.mad_lab_project.expense_handler.databases.ExpenditureDatabase;

public class AddExpenses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String categories[];
    DatePicker datePicker;
    private String category;
    int amt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        Resources res = getResources();
        categories = res.getStringArray(R.array.categoriesArray);
        Spinner categoriesSpinner = (Spinner) findViewById(R.id.spinnerCategories);
        categoriesSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categoriesArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);

        Button addExpenditureBtn = (Button) findViewById(R.id.addExpenditureButton);


        addExpenditureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpenditureDatabase db = new ExpenditureDatabase(AddExpenses.this);
                EditText editTextAmt = (EditText) findViewById(R.id.editTextAmtExpenditure);
                try{
                    float x = Float.parseFloat(editTextAmt.getText().toString());
                    amt = (int) x;
                }
                catch (Exception e){
                    return;
                }
                if(Login_Activity.loggedInUserId.length() == 0)
                    return;
                if(!(db.addExpenditure(Login_Activity.loggedInUserId,category,getSelectedDate(),amt)))
                {
                    Log.d("Expenditure add: ","Failed");
                    return;
                }
                else {
                    //Change Intent to Home;
                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = categories[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getSelectedDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(datePicker.getYear()+"-");
        builder.append((datePicker.getMonth() + 1)+"-");//month is 0 based
        builder.append(datePicker.getDayOfMonth());
        return builder.toString();
    }

}