package com.example.android_app_food_g6.AdaptersShop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.ModelShop.Bill;
import com.example.android_app_food_g6.ModelShop.Cart;
import com.example.android_app_g6.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBill extends RecyclerView.Adapter<AdapterBill.LoadBill>{
    Context context;
    ArrayList<Cart> list;

    public AdapterBill(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LoadBill onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewObj = inflater.inflate(R.layout.bill_adapt_shop, viewGroup, false);
        return new LoadBill(viewObj);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadBill holder, int position) {
        holder.qty.setText(list.get(position).getQty()+"");
        holder.name.setText(list.get(position).getFoodname());
        holder.price.setText(list.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoadBill extends RecyclerView.ViewHolder{

        TextView qty,name,price;

        public LoadBill(@NonNull View itemView) {
            super(itemView);
            qty= itemView.findViewById(R.id.qty);
            name= itemView.findViewById(R.id.itemName);
            price= itemView.findViewById(R.id.itemPrice);
        }
    }
}
