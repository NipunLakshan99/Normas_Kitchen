package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.madcustomer.Database.DBHandler;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ProfileManagement extends AppCompatActivity {

    EditText username, phone, address, contactno, email, password;
    Button createprofile, updateprofile;
    RadioButton male,female;
    String gender;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
     //   awesomeValidation = new AwesomeValidation(BASIC);

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

       // String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
       // awesomeValidation.addValidation(ProfileManagement.this, R.id.etUserNameP, "[a-zA-Z\\s]+", R.string.err_name);
        //awesomeValidation.addValidation(ProfileManagement.this, R.id.etPhoneP, RegexTemplate.TELEPHONE, R.string.err_tel);
        //awesomeValidation.addValidation(ProfileManagement.this, R.id.etEmailP, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        //awesomeValidation.addValidation(ProfileManagement.this, R.id.etPasswordP, regexPassword, R.string.password);

        //createprofile.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View view) {
               // if(male.isChecked()){
                 //              gender = "Male";

                   //      }
                     // else {
                    //gender = "Female";
               // }
          //      if(awesomeValidation.validate()){

            //        String aa= username.getText().toString();
              //      String bb= phone.getText().toString();
                //    String cc= email.getText().toString();
          //          String dd= password.getText().toString();


                  //  DBHandler dbHandler = new DBHandler(getApplicationContext());
                    //Long newID = dbHandler.addInfo(username.getText().toString(),phone.getText().toString(),address.getText().toString(),contactno.getText().toString(),email.getText().toString(),password.getText().toString(),gender);
                    //Toast.makeText(ProfileManagement.this, "Success", Toast.LENGTH_SHORT).show();
                    //Intent i =new Intent(getApplicationContext(),EditProfile.class);
                    //startActivity(i);
                //}
                //else {
                  //  Toast.makeText(ProfileManagement.this, "invalid", Toast.LENGTH_SHORT).show();
                //}
            //}
        //});





        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

      createprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male.isChecked()) {
                    gender = "Male";

                } else {
                    gender = "Female";
                }
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Long newID = dbHandler.addInfo(username.getText().toString(), phone.getText().toString(), address.getText().toString(), contactno.getText().toString(), email.getText().toString(), password.getText().toString(), gender);
                Toast.makeText(ProfileManagement.this, "User Added. User Id:" + newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), CustomerProfile.class);
                startActivity(i);

            }
        });

    }
}
