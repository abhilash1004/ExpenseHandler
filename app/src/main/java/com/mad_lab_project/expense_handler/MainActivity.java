package com.mad_lab_project.expense_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mad_lab_project.expense_handler.activities.Login_Activity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent loginPageIntent = new Intent(this, Login_Activity.class);
        //Intent loginPageIntent = new Intent(this, SetGoalsActivity.class);
        startActivity(loginPageIntent);
    }
}