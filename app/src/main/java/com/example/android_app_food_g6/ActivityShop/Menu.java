package com.example.android_app_food_g6.ActivityShop;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_app_g6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_shop);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.cart:
                    System.out.println("carttttt");
//                    selectedFragment = new FragmenteChannel();
                    break;
//                case R.id.nav_notify:
//                    selectedFragment = new FragmentNotification();
//                    break;
//                case R.id.nav_profilr:
//                    selectedFragment = new FragmentUser();
//                    break;
            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            return true;
        }
    };

}
