package com.mad_lab_project.expense_handler.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad_lab_project.expense_handler.MainActivity;
import com.mad_lab_project.expense_handler.R;
import com.mad_lab_project.expense_handler.databases.UserPassDatabase;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button Register = (Button) findViewById(R.id.buttonRegister);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailIdeditText = (EditText) findViewById(R.id.editTextemailidregistration);
                EditText userideditText = (EditText) findViewById(R.id.editTextuserIdregistration);
                EditText pass1editText = (EditText) findViewById(R.id.editTextpasswordRegistration);
                EditText pass2editText = (EditText) findViewById(R.id.editTextRePasswordregistration);
                UserPassDatabase db = new UserPassDatabase(SignUpActivity.this);

                String pass1 = pass1editText.getText().toString();
                String pass2 = pass2editText.getText().toString();
                String emailId = emailIdeditText.getText().toString();
                String userId = userideditText.getText().toString();
                Log.d("Check",pass1 + pass2 + emailId + userId);

                if (pass1.length()==0 || pass2.length()==0 || emailId.length()==0 || userId.length()==0)
                    return;

                int x = 1;
                if(db.checkuserexists(emailId)){
                    Toast.makeText(SignUpActivity.this,"Email Already Exists", Toast.LENGTH_LONG).show();
                    x=0;
                    return;
                }
                else Log.d("","emailid doesnt exist");
                if(db.checkuserexists(userId)){
                    Toast.makeText(SignUpActivity.this,"User Already Exists", Toast.LENGTH_LONG).show();
                    x= 0;
                    return;
                }
                else Log.d("","usetrid doesnt exist");
                if(!pass1.equals(pass2)) {
                    Toast.makeText(SignUpActivity.this,"Passwords Doesnt Match", Toast.LENGTH_LONG).show();
                    x= 0;
                    return;
                }

                if(db.addUser(emailId,userId,pass1)) {
                    Toast.makeText(SignUpActivity.this,"You Have successfully Registered", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Error in Registration", Toast.LENGTH_LONG).show();
                }

                Intent sampleActivityIntent = new Intent(SignUpActivity.this, SampleDisplayActivity.class);
                startActivity(sampleActivityIntent);


            }
        });


    }
}