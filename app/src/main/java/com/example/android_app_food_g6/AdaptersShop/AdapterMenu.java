package com.example.android_app_food_g6.AdaptersShop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_app_food_g6.ActivityShop.EditFoods;
import com.example.android_app_food_g6.ActivityShop.Interface3;
import com.example.android_app_food_g6.ActivityShop.Login;
import com.example.android_app_food_g6.ActivityShop.Menu;
import com.example.android_app_food_g6.ModelShop.MenuTypes;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.LoadTypes> {
    Context context;
    List<MenuTypes> list;

    public AdapterMenu(Context context, List<MenuTypes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public LoadTypes onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewObj = inflater.inflate(R.layout.inter1_shop, viewGroup, false);
        return new LoadTypes(viewObj);
    }

    @Override
    public void onBindViewHolder(final LoadTypes loadTypes, final int i) {

//        afggdfgdfg
//        //if chek kerala admin nam "btn.setvisibility(true)

        if (Login.type.equals("root")){
            loadTypes.btnedit.setVisibility(View.VISIBLE);
        }else {
            loadTypes.btnedit.setVisibility(View.INVISIBLE);
        }

        loadTypes.name.setText(list.get(i).getName().toString());
        Glide.with(context).load(list.get(i).getImage()).into(loadTypes.imageView);

        final MenuTypes type = list.get(i);
        System.out.println(type.getName() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

        loadTypes.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditFoods.class);
                i.putExtra("type",type.getName());
                context.startActivity(i);
//                ((Menu)context).finish();
            }
        });

        //view
        loadTypes.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,Interface3.class);
                i.putExtra("type",type.getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LoadTypes extends RecyclerView.ViewHolder {
        //    ConstraintLayout constraintLayout;
        TextView name;

        ImageView imageView;

        Button btn;
        Button btnedit;


        public LoadTypes(View itemView) {
            super(itemView);
//        constraintLayout=itemView.findViewById(R.id.doctor);

            name = itemView.findViewById(R.id.nametype);

            imageView = itemView.findViewById(R.id.img);
            btn = itemView.findViewById(R.id.btnview);
            btnedit = itemView.findViewById(R.id.btnedit);
        }
    }

}
