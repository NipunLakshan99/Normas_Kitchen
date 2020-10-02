package com.example.android_app_food_g6.ActivityShop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_app_food_g6.ModelShop.Foods;
import com.example.android_app_g6.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddFoods extends AppCompatActivity {

    Button btnadd;
    EditText name;
    EditText price;
    ImageView imageButtonl;
    InputStream imageStream;
    String type;
    String imageNameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_foods_shop);
        btnadd = findViewById(R.id.AddFood);
        name = findViewById(R.id.addname);
        price = findViewById(R.id.addprice);
        imageButtonl = findViewById(R.id.setimage);

        imageButtonl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(name.getText());
                profilePicSelection();
            }
        });

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();//ape firebase eke database eka access karanne FirebaseDatabase.getInstance() kiyala
                final DatabaseReference keyTable = database.getReference("Foods").child(type);//userslawa wenama save karaganna parent key ekak hadanawa user kiyala
                final DatabaseReference dr = keyTable.push();

                final DatabaseReference keyTable1 = keyTable.child(dr.getKey());

                FirebaseStorage storage = FirebaseStorage.getInstance();//ape firebase eke storage eka access karanne FirebaseStorage.getInstance() kiyala
                final StorageReference folder = storage.getReference("Foods").child(type).child(imageNameName);//methanin reference ekak hadaganne image save karagana format ekak hadaganna user kiyala folder ekak hadagena eke ethule userslage images tika save karaganna hadanawa
                final UploadTask task = folder.putStream(imageStream);
//                final Task<Uri> task1 = folder.getDownloadUrl();

                Task<Uri> urlTask = task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return folder.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            System.out.println(name.getText());
                            System.out.println(price.getText());
                            System.out.println(folder.getDownloadUrl());
                            Foods foods = new Foods(type, name.getText().toString(), downloadUri.toString(), price.getText().toString(), dr.getKey());
                            keyTable1.setValue(foods);
                            finish();
                        } else {
                            // Handle failures
                            // ...
                        }
                    }
                });
            }
        });
    }

    //image
    private void profilePicSelection() {


        //DISPLAY DIALOG TO CHOOSE CAMERA OR GALLERY

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFoods.this);
        builder.setTitle("Add Photo!");

        //SET ITEMS AND THERE LISTENERS
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private void cameraIntent() {

        //CHOOSE CAMERA
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 400);


        } else {
            //camera permission not Granted
            String permissionArray[] = new String[3];
            permissionArray[0] = Manifest.permission.CAMERA;
            permissionArray[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
            permissionArray[2] = Manifest.permission.WRITE_EXTERNAL_STORAGE;

            ActivityCompat.requestPermissions(this, permissionArray, 300);
        }
    }

    private void galleryIntent() {

        //CHOOSE IMAGE FROM GALLERY
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 300) {
            if (grantResults[0] == 0) {
                Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivityForResult(intent, 400);
            } else if (grantResults[1] == 0) {
                Toast.makeText(getApplicationContext(), "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else if (grantResults[2] == 0) {
                Toast.makeText(getApplicationContext(), "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Camera Permission deepan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 400) {
            if (resultCode == Activity.RESULT_OK) {
                //SAVE URI FROM CAMERA

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageButtonl.setBackground(null);
                imageButtonl.setImageBitmap(bitmap);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap, "Title", null);
                Uri uri = Uri.parse(path);
                imageNameName = uri.getLastPathSegment();


                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(uri);//me widiyata input steam ekakata dala tiyagannawa,ethakota save karaddi me stream eka database ekata yawanna puluwan
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                OnSuccessListener listener = new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        System.out.println("Success");
//                        System.out.println(name.getText());
//                        System.out.println(price.getText());
//                        System.out.println(folder.getDownloadUrl());
//                        Foods foods = new Foods(type, name.getText().toString(), folder.getDownloadUrl().toString(), price.getText().toString(), dr.getKey());
//                        keyTable.setValue(foods);
//                    }
//                };

//                task.addOnSuccessListener(listener);

            }
        } else if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                //SAVE URI FROM CAMERA
                Uri imageUri = data.getData();
                //imageView.setBackground(null);
                imageButtonl.setImageURI(null);
                imageButtonl.setImageURI(imageUri);
                imageNameName = imageUri.getLastPathSegment();
                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(imageUri);//me widiyata input steam ekakata dala tiyagannawa,ethakota save karaddi me stream eka database ekata yawanna puluwan
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, int resultCode, Intent data) {
//
//    }


}