package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.madcustomer.Database.DBHandler;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Home extends AppCompatActivity {

    EditText username,password;
    Button login,register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        username = findViewById(R.id.etUserNameH);
        password = findViewById(R.id.etPasseordH);
        login = findViewById(R.id.btnLoginH);
        register = findViewById(R.id.btnRegisterH);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(i);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              DBHandler dbHandler = new DBHandler(getApplicationContext());

                if(dbHandler.loginUser(username.getText().toString(),password.getText().toString())) {

                    Toast.makeText(Home.this, "Login in successfully ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),CustomerProfile.class);
                    startActivity(i);

              }

               else{
                    Toast.makeText(Home.this, "No User invalid User Details", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                    password.setText(null);
                }
            }
        });
    }
}