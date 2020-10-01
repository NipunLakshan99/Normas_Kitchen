package com.example.android_app_food_g6.FragmentDelivery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_app_food_g6.AdapterDelivery.OrdersAdapter;
import com.example.android_app_food_g6.Model.Orders;
import com.example.android_app_g6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ordersDelivery extends Fragment {

    ArrayList<Orders> orderlist;

    DatabaseReference databaseReference;
    OrdersAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_delivery, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        orderlist = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.orderdata);
        FragmentManager fragmentManager = getFragmentManager();
        adapter = new OrdersAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderlist.clear();
                for(DataSnapshot snap1:snapshot.getChildren()){
                    Orders od=new Orders();
                    od.setAddress(snap1.child("addres").getValue().toString());
                    od.setOrdertime(snap1.child("time").getValue().toString());
                    od.setPaymenttype(snap1.child("payment").getValue().toString());
                    od.setPrice(snap1.child("price").getValue().toString());
                    od.setOrderid(snap1.getKey());
                    if (snap1.child("status").getValue()!=null){
                        od.setStatus(snap1.child("status").getValue().toString());
                    }
                    orderlist.add(od);
                }
                adapter.loadOrders(orderlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}