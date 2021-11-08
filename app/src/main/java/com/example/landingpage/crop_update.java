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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class crop_update extends AppCompatActivity {
    Button addbtn, upbtn, delebtn, seebtn, update;
    EditText namet, price, description, type, cropid;
    TextView crpdec, crpname, cprtype, crpprice, crpid;
    private FirebaseAuth firebaseAuth;
    ImageView profile3;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PICK_IMAGE = 1;
    Uri imageUri3;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri3 = data.getData();
            profile3.setImageURI(imageUri3);
            uploadPicture();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri3);
                profile3.setImageBitmap(bitmap);
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

        riversRef.putFile(imageUri3)
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
        setContentView(R.layout.activity_crop_update);


        namet = (EditText) findViewById(R.id.namet);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        type = (EditText) findViewById(R.id.type);
        cropid = (EditText) findViewById(R.id.cropid);
        update = (Button) findViewById(R.id.update);
        profile3 = (ImageView) findViewById(R.id.profile3);
        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String, Object> bmap = new HashMap<String, Object>();
                bmap.put("NAME", namet.getText().toString());
                bmap.put("PRICE", price.getText().toString());
                bmap.put("DESCRIPTION", description.getText().toString());
                bmap.put("TYPE", type.getText().toString());
                bmap.put("ID", cropid.getText().toString());

                FirebaseDatabase.getInstance().getReference("landing_page").child("crops").child(firebaseAuth.getCurrentUser().getUid().toString()).updateChildren(bmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(crop_update.this, "TASK IS SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
