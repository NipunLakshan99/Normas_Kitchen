package com.example.android_app_food_g6.ActivityShop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_app_food_g6.AdaptersShop.AdapterEdit;
import com.example.android_app_food_g6.AdaptersShop.AdapterMenu;
import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditFoods extends AppCompatActivity {

    Button add;
    String type;
    TextView title;
    ArrayList<Foods> foods = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foods_shop);

        add=findViewById(R.id.addnew);
        title=findViewById(R.id.typeTitle);

        Intent intent = getIntent();

        type= intent.getStringExtra("type");
        title.setText(type);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddFoods.class);
                i.putExtra("type",type);
                startActivity(i);
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
                        final RecyclerView view = findViewById(R.id.recyclerViewfoods);
                        AdapterEdit edit = new AdapterEdit(getApplicationContext(), foods);
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