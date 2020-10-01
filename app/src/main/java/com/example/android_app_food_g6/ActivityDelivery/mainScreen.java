package com.example.android_app_food_g6.ActivityDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_app_g6.R;

public class mainScreen extends AppCompatActivity {

    AppCompatButton cus , del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        cus.findViewById(R.id.asCus);
        del.findViewById(R.id.asDel);

        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainScreen.this, LoginDelivery.class));
                finish();
            }
        });

    }
}