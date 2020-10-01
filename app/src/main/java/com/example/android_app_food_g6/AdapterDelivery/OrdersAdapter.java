package com.example.android_app_food_g6.AdapterDelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.Model.Orders;
import com.example.android_app_g6.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{

    private ArrayList orderlist;
    private Context context;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    public OrdersAdapter(Context context) {
        orderlist=new ArrayList();
        this.context = context;
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void loadOrders(ArrayList output) {
        this.orderlist = output;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oders_adapter, parent, false);
        OrdersAdapter.ViewHolder vh = new OrdersAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Orders o = (Orders) orderlist.get(position);
        holder.odtime.setText(o.getOrdertime());
        holder.price.setText(o.getPrice());
        holder.p_type.setText(o.getPaymenttype());
        holder.address.setText(o.getAddress());
        if (o.getStatus()!=null && o.getStatus().equals("Confirmed")){
            holder.confirm.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
        }else {
            holder.confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("status", "Confirmed");
                    databaseReference.child(o.getOrderid()).updateChildren(hashMap);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child(o.getOrderid()).setValue(null);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView odtime,price,p_type;
        EditText address;
        AppCompatButton confirm,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            odtime = itemView.findViewById(R.id.order_time);
            price = itemView.findViewById(R.id.price);
            p_type = itemView.findViewById(R.id.p_type);
            address = itemView.findViewById(R.id.address);
            confirm = itemView.findViewById(R.id.confirm);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
