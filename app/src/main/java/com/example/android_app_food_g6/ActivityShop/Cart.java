package com.example.android_app_food_g6.ActivityShop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.AdaptersShop.AdapterCart;
import com.example.android_app_food_g6.AdaptersShop.AdapterEdit;
import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    Button pay;
    ArrayList<com.example.android_app_food_g6.ModelShop.Cart> cart = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shop);

        pay=findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Bill.class);
                startActivity(i);
                finish();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference keyTable = database.getReference("Cart").child(Login.userid);
        keyTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    com.example.android_app_food_g6.ModelShop.Cart c = postSnapshot.getValue(com.example.android_app_food_g6.ModelShop.Cart.class);
                    cart.add(c);

                    if (snapshot.getChildrenCount() == cart.size()) {
                        final RecyclerView view = findViewById(R.id.recyclerViewcart);
                        AdapterCart adap = new AdapterCart(getApplicationContext(), cart);
                        view.setAdapter(adap);
                        view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
