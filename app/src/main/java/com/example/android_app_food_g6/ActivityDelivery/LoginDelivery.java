package com.example.android_app_food_g6.ActivityDelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginDelivery extends AppCompatActivity {
    AppCompatEditText email, password;
    AppCompatButton login;
    EditText signup;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_delivery);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.register_btn);
        signup = findViewById(R.id.txt_signup);

        FirebaseApp.initializeApp(getApplicationContext());
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginDelivery.this, RegisterDelivery.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
                    Toast.makeText(LoginDelivery.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    final ProgressDialog pd = new ProgressDialog(LoginDelivery.this);
                    pd.setCanceledOnTouchOutside(false);

                    auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(LoginDelivery.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                                if (firebaseUser != null && firebaseUser.isEmailVerified()) {
                                    Intent intent = new Intent(LoginDelivery.this, MainActivityDelivery.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginDelivery.this, "Please verify your Email.", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                pd.dismiss();
                                Toast.makeText(LoginDelivery.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}