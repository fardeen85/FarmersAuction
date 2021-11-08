package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.HashMap;

public class Signup_update extends AppCompatActivity {
    ImageView profile;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    EditText name, email, password, city, company, phone1, descript;
    Button ups;
    TextView img, name1, email1, com, city1, gen, phone,pass,dec;
    TextInputLayout nameError, emailError, phoneError, passError;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;


///image work start///
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ///half image work end////

    ///update data auto filled///

    public void loaddata() {
        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("BUYER").child(firebaseAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String name1, email1, phone, pass, city1, com, dec;

                    name1 = snapshot.child("name").getValue().toString();
                    email1 = snapshot.child("email").getValue().toString();
                    phone = snapshot.child("phone").getValue().toString();
                    pass = snapshot.child("password").getValue().toString();
                    city1 = snapshot.child("city").getValue().toString();
                    com = snapshot.child("Company").getValue().toString();
                    dec = snapshot.child("descript").getValue().toString();
                    name.setText(name1);
                    email.setText(email1);
                    phone1.setText(phone);
                    password.setText(pass);
                    city.setText(city1);
                    company.setText(com);
                    descript.setText(dec);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
///update data auto filled//


            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaddata();
        setContentView(R.layout.activity_signup_update);
        profile = (ImageView) findViewById(R.id.profile);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone1 = (EditText) findViewById(R.id.phone1);
        password = (EditText) findViewById(R.id.password);
        city = (EditText) findViewById(R.id.city);
        company = (EditText) findViewById(R.id.company);
        descript = (EditText) findViewById(R.id.descript);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ///image click listener///

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });






        ups = (Button) findViewById(R.id.ups);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });

////update profile button///

        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    HashMap<String, Object> imap = new HashMap<String, Object>();
                    imap.put("name", name.getText().toString());
                    imap.put("email", email.getText().toString());
                    imap.put("password", password.getText().toString());
                    imap.put("Company", company.getText().toString());
                    imap.put("phone", phone1.getText().toString());
                    imap.put("city", city.getText().toString());
                    imap.put("descript", descript.getText().toString());


                    FirebaseDatabase.getInstance().getReference("landing_page").child("users").child(firebaseAuth.getCurrentUser().getUid().toString()).updateChildren(imap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Signup_update.this, "DATA UPDATED FROM BUYER", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
            }
}