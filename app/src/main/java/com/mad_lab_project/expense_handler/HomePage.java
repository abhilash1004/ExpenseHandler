package com.mad_lab_project.expense_handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    CardView cardnew;
    CardView cardedit;
    CardView carddisplay;
    CardView cardstats;
    CardView cardsettings;
    CardView cardlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cardnew = findViewById(R.id.cardviewNew);
        cardedit = findViewById(R.id.cardviewEdit);
        carddisplay = findViewById(R.id.cardviewDisplay);
        cardstats = findViewById(R.id.cardviewStats);
        cardsettings = findViewById(R.id.cardviewSettings);
        cardlogout = findViewById(R.id.cardviewLogout);


        cardnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        carddisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}