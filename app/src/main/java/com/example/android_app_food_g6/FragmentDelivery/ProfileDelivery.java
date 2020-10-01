package com.example.android_app_food_g6.FragmentDelivery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.android_app_food_g6.ModelDelivery.Delivery;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;


public class ProfileDelivery extends Fragment {
    AppCompatImageView userimg;
    AppCompatEditText fullname,uname,nic,mobile,vehicleno,email;
    AppCompatButton update;

    FirebaseUser firebaseUser;
    StorageReference storageReference;

    private Uri mImageUri;
    private StorageTask uplaodTask;
    private static final int USERIMAGE = 102;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_delivery, container, false);
        userimg=view.findViewById(R.id.user_img);
        fullname=view.findViewById(R.id.full_name);
        uname=view.findViewById(R.id.user_name);
        nic=view.findViewById(R.id.nic);
        mobile=view.findViewById(R.id.mobile_no);
        vehicleno=view.findViewById(R.id.vehicle_no);
        email=view.findViewById(R.id.email);
        update=view.findViewById(R.id.update_btn);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("DeliveryProfiles");
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Delivery Man").child(firebaseUser.getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Delivery delivery=snapshot.getValue(Delivery.class);
                if (!delivery.getFullname().isEmpty()){
                    fullname.setText(delivery.getFullname());
                }
                if (!delivery.getUsername().isEmpty()){
                    uname.setText(delivery.getUsername());
                }
                if (!delivery.getNic().isEmpty()){
                    nic.setText(delivery.getNic());
                }
                if (!delivery.getMobil().isEmpty()){
                    mobile.setText(delivery.getMobil());
                }
                if (!delivery.getVehicleno().isEmpty()){
                    vehicleno.setText(delivery.getVehicleno());
                }
                email.setText(firebaseUser.getEmail());
                if (!delivery.getImageurl().isEmpty()){
                    Picasso.get().load(delivery.getImageurl()).into(userimg);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, USERIMAGE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullname.getText().toString().isEmpty()&&uname.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Nothing to update", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("fullname", fullname.getText().toString());
                    hashMap.put("username", uname.getText().toString());

                    reference.updateChildren(hashMap);
                    Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }

    private void uploadImage() {

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Updating your Profile");
        pd.show();
        pd.setCanceledOnTouchOutside(false);


        if (mImageUri != null) {
            final StorageReference filerefrence = storageReference.child(firebaseUser.getUid()+ ".jpg");
            uplaodTask = filerefrence.putFile(mImageUri);

            uplaodTask = filerefrence.putFile(mImageUri);

            uplaodTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filerefrence.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String myUrl = null;
                        if (downloadUri != null) {
                            myUrl = downloadUri.toString();
                        }

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Delivery Man").child(firebaseUser.getUid());
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("imageurl", "" + myUrl);

                        reference.updateChildren(hashMap);
                        pd.dismiss();


                    } else {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {

            Toast.makeText(getActivity(), "No image selected ", Toast.LENGTH_SHORT).show();
            pd.dismiss();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case USERIMAGE:
                    mImageUri = data.getData();
                    userimg.setImageURI(mImageUri);
                    uploadImage();
                    break;

            }
    }
}