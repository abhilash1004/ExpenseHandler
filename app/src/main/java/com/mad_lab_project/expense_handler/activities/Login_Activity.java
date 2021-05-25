package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad_lab_project.expense_handler.HomePage;
import com.mad_lab_project.expense_handler.R;
import com.mad_lab_project.expense_handler.databases.UserPassDatabase;

public class Login_Activity extends AppCompatActivity {

    public static String loggedInUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logInbtn = (Button) findViewById(R.id.buttonSignIn);
        logInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = (EditText) findViewById(R.id.editTextEmailId);
                EditText passEditText = (EditText) findViewById(R.id.editTextPassword);

                UserPassDatabase db = new UserPassDatabase(Login_Activity.this);
                if(db.checkCredentialsEntered(emailEditText.getText().toString(),passEditText.getText().toString())) {



                    Intent homePageIntent = new Intent(Login_Activity.this, HomePage.class);
                    startActivity(homePageIntent);

                }
                else {
                    Toast.makeText(Login_Activity.this,"Wrong Credentials / user Doesnt Exist" , Toast.LENGTH_LONG).show();
                }
            }
        });

        Button signUpbtn  = (Button) findViewById(R.id.buttonSignUp);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(Login_Activity.this,SignUpActivity.class);
                startActivity(signupIntent);
            }
        });
    }
}