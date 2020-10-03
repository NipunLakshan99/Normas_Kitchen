package com.example.android_app_food_g6.AdaptersShop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_app_food_g6.ActivityShop.EditFoods;
import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterEdit extends RecyclerView.Adapter<AdapterEdit.LoadEditFoods> {
    Context context;
    List<Foods> list;

    public AdapterEdit(Context context, List<Foods> list) {
        this.context = context;
        this.list = list;
        System.out.println(list.size() + "fffffffffffffffffff");
    }

    @Override
    public LoadEditFoods onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewObj = inflater.inflate(R.layout.inter2_shop, viewGroup, false);
        return new LoadEditFoods(viewObj);
    }

    @Override
    public void onBindViewHolder(final LoadEditFoods loadEditFoods, final int i) {

        loadEditFoods.name.setText(list.get(i).getName());
        loadEditFoods.price.setText("Rs "+list.get(i).getPrice());
        Glide.with(context).load(list.get(i).getImage()).into(loadEditFoods.imageView);

        final Foods type = list.get(i);
        System.out.println(type.getName() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

        loadEditFoods.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebace delete
                //__storage
                // Create a storage reference from our app
                FirebaseStorage storage = FirebaseStorage.getInstance();//ape firebase eke storage eka access karanne FirebaseStorage.getInstance() kiyala
                StorageReference storageRef = storage.getReference();
                StorageReference desertRef = storageRef.getStorage().getReferenceFromUrl(list.get(i).getImage());
                desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // File deleted successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Uh-oh, an error occurred!
                    }
                });

                //fire
                FirebaseDatabase database = FirebaseDatabase.getInstance();//ape firebase eke database eka access karanne FirebaseDatabase.getInstance() kiyala
                final DatabaseReference keyTable = database.getReference("Foods").child(list.get(i).getType()).child(list.get(i).getId());
                keyTable.removeValue();
                notify();
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoadEditFoods extends RecyclerView.ViewHolder {
        //    ConstraintLayout constraintLayout;
        TextView name;
        TextView price;

        ImageView imageView;

        Button btn;


        public LoadEditFoods(View itemView) {
            super(itemView);
//        constraintLayout=itemView.findViewById(R.id.doctor);

            name = itemView.findViewById(R.id.foodnameAdd);
            price = itemView.findViewById(R.id.foodpriceAdd);

            imageView = itemView.findViewById(R.id.imdAdd);
            btn = itemView.findViewById(R.id.delete);
        }
    }

}