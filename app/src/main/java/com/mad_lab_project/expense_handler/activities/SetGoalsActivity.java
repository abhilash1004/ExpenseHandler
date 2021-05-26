package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;

import com.mad_lab_project.expense_handler.R;

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

        adapter = ArrayAdapter.createFromResource(this,R.array.Months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Resources res = getResources();
        months = res.getStringArray(R.array.Months);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedMonth = position + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}