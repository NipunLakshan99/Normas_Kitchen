package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madcustomer.Database.DBHandler;

public class FeedBack extends AppCompatActivity {

    EditText username,description;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        username = findViewById(R.id.etUserNameFB);
        description = findViewById(R.id.etDescriptonFB);
        add = findViewById(R.id.btnaddFB);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                if(dbHandler.loginUser(username.getText().toString(),description.getText().toString())) {


                    Toast.makeText(FeedBack.this, "Feedback Insert Successfull ! ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),CustomerProfile.class);
                    startActivity(i);

                }

                else{

                    Toast.makeText(FeedBack.this, "Invalid User Details! Please check Username again.", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                    description.setText(null);
                }
            }

        });







       // add.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {

             //   DBHandler dbHandler = new DBHandler(getApplicationContext());
              //Long newID = dbHandler.addInfo2(username.getText().toString(),description.getText().toString());
                //Toast.makeText(FeedBack.this, "User Added. User Id:" + newID, Toast.LENGTH_SHORT).show();

               // Intent i =new Intent(getApplicationContext(),CustomerProfile.class);
                //startActivity(i);

            //}
        //});



    }
}