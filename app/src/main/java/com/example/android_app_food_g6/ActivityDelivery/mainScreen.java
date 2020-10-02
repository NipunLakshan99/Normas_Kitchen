package com.example.android_app_food_g6.ActivityDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_app_food_g6.ActivityShop.Login;
import com.example.android_app_g6.R;

public class mainScreen extends AppCompatActivity {

   AppCompatTextView cusbtn , delbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        cusbtn=findViewById(R.id.asCus);
        delbtn=findViewById(R.id.asDel);

        cusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainScreen.this, Login.class));
                finish();
            }
        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainScreen.this, LoginDelivery.class));
                finish();
            }
        });
    }
}