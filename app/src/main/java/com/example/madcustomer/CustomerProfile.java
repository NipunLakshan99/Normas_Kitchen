package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerProfile extends AppCompatActivity {


    Button menue,myorders,feedback,help,manageprofile,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        menue = findViewById(R.id.btnmenueMA);
        myorders = findViewById(R.id.btnmyordersMA);

        feedback = findViewById(R.id.btnfeedbkMA);
        help = findViewById(R.id.btnhelpMA);
        manageprofile = findViewById(R.id.btnmanageproMA);
        logout = findViewById(R.id.btnlogoutMA);



        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FeedBack.class);
                startActivity(i);
            }
        });

        manageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Help.class);
                startActivity(i);
            }
        });





    }
}