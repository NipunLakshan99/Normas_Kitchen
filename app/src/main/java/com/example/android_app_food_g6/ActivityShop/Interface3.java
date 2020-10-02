package com.example.android_app_food_g6.ActivityShop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.AdaptersShop.AdapterEdit;
import com.example.android_app_food_g6.AdaptersShop.AdapterViewFoods;
import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Interface3 extends AppCompatActivity {


    String type;
    TextView title;
    ArrayList<Foods> foods = new ArrayList<>();
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface3_shop);

        Intent intent = getIntent();
        type= intent.getStringExtra("type");

        title=findViewById(R.id.menuText);
        title.setText(type);

        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FirebaseDatabase rootRef1 = FirebaseDatabase.getInstance();
        final DatabaseReference menutypeRef = rootRef1.getReference("Foods").child(type);

        menutypeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Foods mt = postSnapshot.getValue(Foods.class);
                    foods.add(mt);
                    if (dataSnapshot.getChildrenCount() == foods.size()) {

                        // The child doesn't exist
                        final RecyclerView view = findViewById(R.id.reLoadFoods);
                        AdapterViewFoods edit = new AdapterViewFoods(getApplicationContext(), foods);
                        view.setAdapter(edit);
                        view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
