package com.example.android_app_food_g6.ActivityShop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_app_food_g6.ModelShop.Customer;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    public static String userid;
    public static String name;
    public static String type;
    TextView reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shop);

        username = findViewById(R.id.un);
        password = findViewById(R.id.pw);
        login = findViewById(R.id.btnlogin);
        reg = findViewById(R.id.signup);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegisterCus.class);
                startActivity(intent);
            }
        });

//        FirebaseDatabase rootRef1 = FirebaseDatabase.getInstance();
        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference customerRef = rootRef1.child("Customer");


        //save customer
//        DatabaseReference customerid = customerRef.push();
//        Customer customer = new Customer(customerid.getKey(),"abc","22222222","Customer","Colombo"); //key eka auto nisa ohomma thiyanna
//        customerid.setValue(customer);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                            Customer customer = postSnapshot.getValue(Customer.class);

                            if (username.getText().toString().equals(customer.getName()) && password.getText().toString().equals(customer.getPassword())) {
                                userid = customer.getId();
                                name = customer.getName();
                                Login.type = customer.getType();
                                Intent i = new Intent(Login.this, Menu.class);
                                startActivity(i);
                                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            } else {
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}
