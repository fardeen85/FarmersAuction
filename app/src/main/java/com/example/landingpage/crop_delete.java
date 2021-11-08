package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class crop_delete extends AppCompatActivity {
    Button delete;
    EditText namet, price, description, type, cropid;
    TextView crpdec, crpname, cprtype, crpprice, crpid;
    private FirebaseAuth firebaseAuth;
    ImageView profile3;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PICK_IMAGE = 1;
    Uri imageUri3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_delete);
        namet = (EditText) findViewById(R.id.namet);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        type = (EditText) findViewById(R.id.type);
        cropid = (EditText) findViewById(R.id.cropid);
        delete = (Button) findViewById(R.id.delete);
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

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String, Object> bmap = new HashMap<String, Object>();
                bmap.put("NAME", namet.getText().toString());
                bmap.put("PRICE", price.getText().toString());
                bmap.put("DESCRIPTION", description.getText().toString());
                bmap.put("TYPE", type.getText().toString());
                bmap.put("ID", cropid.getText().toString());

                FirebaseDatabase.getInstance().getReference("landing_page").child("crops").child(firebaseAuth.getCurrentUser().getUid().toString()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(crop_delete.this, "TASK IS SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });    }
}