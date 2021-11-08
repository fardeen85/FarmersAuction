package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class store extends AppCompatActivity {
    ImageView profile121;
    private static final int PICK_IMAGE = 1;
    Uri imageUri121;
    EditText ite_name, ite_price, ite_description, ite_type, ite_id;
    Button ite_add;
    Button addbtn, upbtn, delebtn, seebtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
        imageUri121 = data.getData();
        profile121.setImageURI(imageUri121);
        uploadPicture();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri121);
            profile121.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    private void uploadPicture() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Images.....");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri121)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Images Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "FAILED TO UPLOAD", Toast.LENGTH_LONG).show();
            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage!" + (int) progressPercent + "%");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ite_name = (EditText) findViewById(R.id.ite_name);
        ite_price = (EditText) findViewById(R.id.ite_price);
        ite_description = (EditText) findViewById(R.id.ite_description);
        ite_type = (EditText) findViewById(R.id.ite_type);
        ite_id = (EditText) findViewById(R.id.ite_id);

        addbtn = (Button) findViewById(R.id.addbtn);
        upbtn = (Button) findViewById(R.id.upbtn);
        delebtn = (Button) findViewById(R.id.delebtn);
        ite_add = (Button) findViewById(R.id.ite_add);
        profile121 = (ImageView) findViewById(R.id.profile121);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profile121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });
        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();
        ite_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> bmap = new HashMap<String, String>();
                bmap.put("ITE_NAME", ite_name.getText().toString());
                bmap.put("ITE_PRICE", ite_price.getText().toString());
                bmap.put("ITE_DESCRIPTION", ite_description.getText().toString());
                bmap.put("ITE_TYPE", ite_type.getText().toString());
                bmap.put("ITE_ID", ite_id.getText().toString());
                FirebaseDatabase.getInstance().getReference("landing_page").child("store").child(firebaseAuth.getCurrentUser().getUid().toString()).setValue(bmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(store.this, "NEW STORE ITEM IS ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(store.this, Dashboard_farmer.class);
                        startActivity(i);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(store.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}