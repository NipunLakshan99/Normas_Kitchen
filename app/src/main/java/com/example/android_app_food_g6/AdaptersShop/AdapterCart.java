package com.example.android_app_food_g6.AdaptersShop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_app_food_g6.ModelShop.Cart;
import com.example.android_app_g6.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.LoadCartItem> {
    Context context;
    ArrayList<Cart> list;

    public AdapterCart(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LoadCartItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewObj = inflater.inflate(R.layout.cart_adapt_shop, viewGroup, false);
        return new LoadCartItem(viewObj);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadCartItem holder, int position) {
        holder.name.setText(list.get(position).getFoodname());
        holder.price.setText(list.get(position).getPrice());
        holder.qty.setText(list.get(position).getQty()+"");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoadCartItem extends RecyclerView.ViewHolder{

        TextView name,price,qty;

        public LoadCartItem(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.itemName);
            price= itemView.findViewById(R.id.itemPrice);
            qty= itemView.findViewById(R.id.qty);
        }
    }
}
