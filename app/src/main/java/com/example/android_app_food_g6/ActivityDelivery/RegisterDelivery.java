package com.example.android_app_food_g6.ActivityDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.android_app_g6.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterDelivery extends AppCompatActivity {
    AppCompatMultiAutoCompleteTextView fname, lname, uname, email, password, confirmpass;
    AppCompatButton registerbtn;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery);
    }
}