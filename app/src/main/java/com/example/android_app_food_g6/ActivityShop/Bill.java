package com.example.android_app_food_g6.ActivityShop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android_app_food_g6.AdaptersShop.AdapterBill;
import com.example.android_app_food_g6.AdaptersShop.AdapterCart;
import com.example.android_app_food_g6.ModelShop.Cart;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bill extends AppCompatActivity {

    Button done;
    TextView tot, id, name, time;
    EditText address;
    RadioButton cash, card;
    ArrayList<Cart> cart = new ArrayList<>();
    Double total;
    String paymenttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_shop);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference keyTable1 = database.getReference("Cart").child(Login.userid);

        total = 0.0;
        done = findViewById(R.id.done);
        id = findViewById(R.id.oid);
        name = findViewById(R.id.cusname);
        time = findViewById(R.id.time);
        address = findViewById(R.id.address);
        cash = findViewById(R.id.cash);
        card = findViewById(R.id.card);
        tot = findViewById(R.id.tot);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add firebace
                if (cash.isSelected()) {
                    paymenttype = "Cash";
                } else {
                    paymenttype = "Card";
                }
                final DatabaseReference orderref = database.getReference("Order").child(id.getText().toString());
                com.example.android_app_food_g6.ModelShop.Bill bill = new com.example.android_app_food_g6.ModelShop.Bill(Login.userid, time.getText().toString(), address.getText().toString(), tot.getText().toString(), paymenttype);
                orderref.setValue(bill);

                keyTable1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cart.clear();
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Cart c = postSnapshot.getValue(Cart.class);
                            cart.add(c);
                            final DatabaseReference ordercartref = orderref.child(c.getFoodId());
                            ordercartref.setValue(c);
                            System.out.println(cart.size());
                            System.out.println(snapshot.getChildrenCount());
                            if (snapshot.getChildrenCount() == cart.size()) {
                                //delete cart firebace
                                keyTable1.removeValue();
//                                notifyAll();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        name.setText(Login.name);
        time.setText(currentTime);


        final DatabaseReference keyTable = database.getReference("Order");
        Query lastQuery = keyTable.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue() == null) {
                    id.setText("1");
                } else {
//                    String orderid = snapshot.getValue().toString();
//                    System.out.println(orderid);
//                    id.setText(orderid);
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        String latestKey = childSnapshot.getKey();
                        System.out.println(latestKey);
                        id.setText(Integer.valueOf(latestKey)+1+"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        keyTable1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cart c = postSnapshot.getValue(Cart.class);
                    cart.add(c);
                    total += Double.valueOf(c.getPrice()) * Integer.valueOf(c.getQty());
                    //calculate total
                    if (snapshot.getChildrenCount() == cart.size()) {
                        final RecyclerView view = findViewById(R.id.billItems);
                        AdapterBill adap = new AdapterBill(getApplicationContext(), cart);
                        view.setAdapter(adap);
                        view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    }
                }
                tot.setText(total + "");
                System.out.println(tot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}