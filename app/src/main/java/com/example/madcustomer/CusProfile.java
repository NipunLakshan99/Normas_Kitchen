package com.example.madcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madcustomer.Database.DBHandler;

import java.util.ArrayList;

public class CusProfile extends AppCompatActivity {

    ListView cusProfile;
    ArrayList dataList;
    ArrayAdapter adapter;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_profile);

        cusProfile = findViewById(R.id.users_list);

        db = new DBHandler(getApplicationContext());
        dataList = (ArrayList) db.readAllInfo();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);

        cusProfile.setAdapter(adapter);

        cusProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = cusProfile.getItemAtPosition(i).toString();
                Toast.makeText(CusProfile.this, "User :"+text, Toast.LENGTH_SHORT).show();
            }
        });



    }
}
