package com.example.android_app_food_g6.AdaptersShop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_app_food_g6.ActivityShop.Login;
import com.example.android_app_food_g6.ModelShop.Cart;
import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.List;

public class AdapterViewFoods extends RecyclerView.Adapter<AdapterViewFoods.LoadFoods> {
    Context context;
    List<Foods> list;
    List<Cart> list1;

    //cart
    InputStream imageStream;

    public AdapterViewFoods(Context context, List<Foods> list) {
        this.context = context;
        this.list = list;
        System.out.println(list.size() + "fffffffffffffffffff");
    }

    @Override
    public LoadFoods onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewObj = inflater.inflate(R.layout.inter3_shop, viewGroup, false);
        return new LoadFoods(viewObj);
    }

    @Override
    public void onBindViewHolder(final LoadFoods loadFoods, final int i) {

        loadFoods.name.setText(list.get(i).getName());
        loadFoods.price.setText(list.get(i).getPrice());
        Glide.with(context).load(list.get(i).getImage()).into(loadFoods.image);

        final Foods foods = list.get(i);
        System.out.println(foods.getName() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

        loadFoods.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make cart And add deta
                FirebaseDatabase database = FirebaseDatabase.getInstance();//ape firebase eke database eka access karanne FirebaseDatabase.getInstance() kiyala
                final DatabaseReference keyTable = database.getReference("Cart").child(Login.userid).child(foods.getId());//userslawa wenama save karaganna parent key ekak hadanawa user kiyala
//                final DatabaseReference dr = keyTable.push();
//                final DatabaseReference keyTable1 = keyTable.child(dr.getKey());

                keyTable.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cart c = snapshot.getValue(Cart.class);
                        if(c != null){
                            if(c.getFoodId().equals(foods.getId())){
                                c.setQty(c.getQty()+1);
                                keyTable.setValue(c);
                            }else{
                                Cart cart = new Cart(foods.getId(),Login.userid,foods.getName(),foods.getPrice(),1);
                                keyTable.setValue(cart);
                            }
                        }else{
                            Cart cart = new Cart(foods.getId(),Login.userid,foods.getName(),foods.getPrice(),1);
                            keyTable.setValue(cart);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoadFoods extends RecyclerView.ViewHolder{

        TextView name,price;
        ImageView image;
        Button btn;

        public LoadFoods(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.foodname);
            price= itemView.findViewById(R.id.foodprice);
            image= itemView.findViewById(R.id.img);
            btn= itemView.findViewById(R.id.add);
        }
    }
}
