package com.example.android_app_food_g6.ActivityShop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_app_food_g6.ModelShop.Customer;
import com.example.android_app_g6.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterCus extends AppCompatActivity {

    EditText name, password, address;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cus_shop);

        name = findViewById(R.id.unreg);
        password = findViewById(R.id.pwreg);
        address = findViewById(R.id.addressreg);
        reg = findViewById(R.id.btnreg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              save customer
                DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference customerRef = rootRef1.child("Customer");
                DatabaseReference customerid = customerRef.push();
                Customer customer = new Customer(customerid.getKey(), name.getText().toString(), password.getText().toString(), "Customer", address.getText().toString());
                customerid.setValue(customer);
            }
        });


    }
}