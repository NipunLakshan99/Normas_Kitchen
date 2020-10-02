package com.example.android_app_food_g6.ActivityShop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.AdaptersShop.AdapterMenu;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    ArrayList<MenuTypes> menutypes = new ArrayList<>();
    TextView name;
    ImageView Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_shop);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);


        name = findViewById(R.id.nameNavi);
        Cart = findViewById(R.id.cartnavi);

        name.setText(Login.name);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Cart.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase rootRef1 = FirebaseDatabase.getInstance();
        final DatabaseReference menutypeRef = rootRef1.getReference("MenuTypes");
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference folder = storage.getReference("ShopMenuType");//table name

//        DatabaseReference menutype = menutypeRef.child("Chinese");
//        MenuTypes type = new MenuTypes("Chinese", "https://firebasestorage.googleapis.com/v0/b/app-food-g6.appspot.com/o/ShopMenuType%2Fchinnese.jpg?alt=media&token=01b50a14-fce7-44c4-9e88-339e5fb5a178");//chinees,indian
//        menutypeRef.setValue(type);


        menutypeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MenuTypes mt = postSnapshot.getValue(MenuTypes.class);
                    menutypes.add(mt);
                    if (postSnapshot.getChildrenCount() == menutypes.size()) {
                        // The child doesn't exist
                        final RecyclerView view = findViewById(R.id.menu_typesRe);
                        System.out.println(Login.type);
                        AdapterMenu menure = new AdapterMenu(getApplicationContext(), menutypes);
                        view.setAdapter(menure);
                        view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////            Fragment selectedFragment = null;
//
//            switch (item.getItemId()) {
//                case R.id.cart:
//                    Intent intent = new Intent(Menu.this, Cart.class);
//                    startActivity(intent);
//                    System.out.println("carttttt");
////                    selectedFragment = new FragmenteChannel();
//                    break;
////                case R.id.nav_notify:
////                    selectedFragment = new FragmentNotification();
////                    break;
////                case R.id.nav_profilr:
////                    selectedFragment = new FragmentUser();
////                    break;
//            }
////            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
//            return true;
//        }
//    };


}
