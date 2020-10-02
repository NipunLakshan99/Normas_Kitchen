package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madcustomer.Database.DBHandler;

public class Help extends AppCompatActivity {

    EditText username,help;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        username = findViewById(R.id.etusernmeHL);
        help = findViewById(R.id.ethelpHL);
        submit = findViewById(R.id.submitHL);

      submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                if(dbHandler.loginUser(username.getText().toString(),help.getText().toString())) {


                    Toast.makeText(Help.this, "Feedback Insert Successfull ! ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),CustomerProfile.class);
                    startActivity(i);

                }

                else{
                    Toast.makeText(Help.this, "Invalid User Details! Please check Username again.", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                    help.setText(null);
                }
            }

        });


    }
}