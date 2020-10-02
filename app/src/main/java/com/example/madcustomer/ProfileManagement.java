package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madcustomer.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username, phone, address, contactno, email, password;
    Button createprofile, updateprofile;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUserNameP);
        phone = findViewById(R.id.etPhoneP);
        address = findViewById(R.id.etAddressP);
        contactno = findViewById(R.id.etContactNoP);
        email = findViewById(R.id.etEmailP);
        password = findViewById(R.id.etPasswordP);
        createprofile = findViewById(R.id.btnCreateP);
        updateprofile = findViewById(R.id.btnUpdateP);
        male = findViewById(R.id.radioButton3);
        female = findViewById(R.id.radioButton4);

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        createprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()){
                    gender = "Male";

                }
                else{
                    gender = "Female";
                }
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    Long newID = dbHandler.addInfo(username.getText().toString(),phone.getText().toString(),address.getText().toString(),contactno.getText().toString(),email.getText().toString(),password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User Added. User Id:" + newID, Toast.LENGTH_SHORT).show();

                Intent i =new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);

            }
        });

    }
}