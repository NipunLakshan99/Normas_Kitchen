package com.example.android_app_food_g6.ActivityShop;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_app_g6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    Activity selected=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_shop);

        bottomNavigationView=findViewById(R.id.bottomnavigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Cart()).commit();
//        getSupportParentActivityIntent()..replace(R.id.frame, selected).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (menuItem.getItemId()) {
//                case R.id.cart:
//                    selected = new Cart();
//
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            return true;
        }
    };
}
