package com.example.android_app_food_g6.ActivityDelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.android_app_food_g6.FragmentDelivery.ProfileDelivery;
import com.example.android_app_food_g6.FragmentDelivery.ordersDelivery;
import com.example.android_app_g6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityDelivery extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_delivery);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        selectedFragment = new ordersDelivery();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.nav_offers:
                    selectedFragment = new ordersDelivery();
                    break;

                case R.id.nav_profile:
                    selectedFragment = new ProfileDelivery();
                    break;
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        }


    };
}