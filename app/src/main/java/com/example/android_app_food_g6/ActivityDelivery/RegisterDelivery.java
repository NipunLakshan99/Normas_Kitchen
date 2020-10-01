package com.example.android_app_food_g6.ActivityDelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;

import com.example.android_app_food_g6.ModelDelivery.Delivery;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterDelivery extends AppCompatActivity {
    androidx.appcompat.widget.AppCompatEditText fullname, uname, nic, mobile, vehicleno, email, password, confirmpass;
    AppCompatButton registerbtn;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery);

        fullname = findViewById(R.id.full_name);
        uname = findViewById(R.id.user_name);
        nic = findViewById(R.id.nic);
        mobile = findViewById(R.id.mobile_no);
        vehicleno = findViewById(R.id.vehicle_no);
        password = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirm_password);
        email = findViewById(R.id.email);
        registerbtn = findViewById(R.id.register_btn);
        auth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_fullname = fullname.getText().toString();
                String str_uname = uname.getText().toString();
                String str_nic = nic.getText().toString();
                String str_mobile = mobile.getText().toString();
                String str_vehicleno = vehicleno.getText().toString();
                String str_pw = password.getText().toString();
                String str_confirmpw = confirmpass.getText().toString();
                String str_email = email.getText().toString();

                if (TextUtils.isEmpty(str_fullname) || TextUtils.isEmpty(str_uname) || TextUtils.isEmpty(str_nic) || TextUtils.isEmpty(str_mobile) || TextUtils.isEmpty(str_vehicleno) || TextUtils.isEmpty(str_pw)  || TextUtils.isEmpty(str_confirmpw)|| TextUtils.isEmpty(str_email)) {
                    Toast.makeText(RegisterDelivery.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (str_pw.length() < 6) {
                    Toast.makeText(RegisterDelivery.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (!(str_pw.equals(str_confirmpw))) {
                    Toast.makeText(RegisterDelivery.this, "Confirm password didn't match", Toast.LENGTH_SHORT).show();
                } else {
                    pd = new ProgressDialog(RegisterDelivery.this);
                    pd.setMessage("Please Wait....");
                    pd.show();
                    pd.setCanceledOnTouchOutside(false);

                    register(str_fullname,str_uname,str_nic,str_mobile,str_vehicleno, str_pw, str_email);
                }


            }
        });

    }



    private void register(final String fullname, final String username, final String nic, final String mobile, final String vehicleno,String password,String email) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterDelivery.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String userid = null;
                    if (firebaseUser != null) {
                        userid = firebaseUser.getUid();
                    }

                    if (userid != null) {
                        reference = FirebaseDatabase.getInstance().getReference().child("Delivery Man").child(userid);
                    }

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("fullname", fullname);
                    hashMap.put("username", username.toLowerCase());
                    hashMap.put("nic", nic);
                    hashMap.put("mobil", mobile);
                    hashMap.put("vehicleno", vehicleno);
                    hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/app-food-g6.appspot.com/o/DeliveryProfiles%2Fprofile.png?alt=media&token=3b9d114e-3b7c-4c72-b9cd-8b77573a1b80");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            pd.dismiss();
                                            Toast.makeText(RegisterDelivery.this, "Email verification  was send", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterDelivery.this, LoginDelivery.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });


                } else {
                    pd.dismiss();
                    Toast.makeText(RegisterDelivery.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}