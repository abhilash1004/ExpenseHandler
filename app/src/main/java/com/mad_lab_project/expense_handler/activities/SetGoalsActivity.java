package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mad_lab_project.expense_handler.HomePage;
import com.mad_lab_project.expense_handler.R;
import com.mad_lab_project.expense_handler.databases.GoalsDatabase;

public class SetGoalsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner monthSpinner;
    //Spinner yearSpinner;
    EditText editTextYear;
    EditText etBudget;
    Button btnSetGoal;
    String months[];
    String years[];
    ArrayAdapter<CharSequence> adapter;
    int selectedMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goals);

        monthSpinner = (Spinner) findViewById(R.id.spinnerMonth);
        //yearSpinner = (Spinner) findViewById(R.id.spinnerYear);
        editTextYear = (EditText) findViewById(R.id.editTextYear);
        etBudget = (EditText) findViewById(R.id.editTextBudget);
        btnSetGoal = (Button) findViewById(R.id.buttonAddGoal);

        monthSpinner.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,R.array.Months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        editTextYear.setText("2021");

        Resources res = getResources();
        months = res.getStringArray(R.array.Months);

        btnSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoalsDatabase db = new GoalsDatabase(SetGoalsActivity.this);
                int amt,year;
                try
                {
                    amt = Integer.parseInt(etBudget.getText().toString());
                    year = Integer.parseInt(editTextYear.getText().toString());
                    if(!(year>2020 && year<2031)) {
                        Toast.makeText(SetGoalsActivity.this,"Error! Year should be in between 2021 to 2030",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (Exception e){
                    Toast.makeText(SetGoalsActivity.this,"Error! Enter proper year or amount",Toast.LENGTH_SHORT).show();
                    return;
                }
                 if((db.addGoals(Login_Activity.loggedInUserId,amt,year,selectedMonth))){
                     Toast.makeText(SetGoalsActivity.this,"Successfully Goal Added",Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(SetGoalsActivity.this, HomePage.class);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(SetGoalsActivity.this,"Failed to add Goal",Toast.LENGTH_SHORT).show();
                 }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedMonth = position + 1;
        //Toast.makeText(this,""+(position+1),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}