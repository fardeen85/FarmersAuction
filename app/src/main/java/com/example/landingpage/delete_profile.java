package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class delete_profile extends AppCompatActivity {
    ImageView profile;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    EditText name, email, password, city, company, phone1, descript;
    Button ups;
    TextView img, name1, email1, com, city1, gen, phone;
    TextInputLayout nameError, emailError, phoneError, passError;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);

        profile = (ImageView) findViewById(R.id.profile);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone1 = (EditText) findViewById(R.id.phone1);
        password = (EditText) findViewById(R.id.password);
        city = (EditText) findViewById(R.id.city);
        company = (EditText) findViewById(R.id.company);
        img = (TextView) findViewById(R.id.img);
        descript = (EditText) findViewById(R.id.descript);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profile.setOnClickListener(new View.OnClickListener() {
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

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"BUYER", "SELLER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        Spinner dropdown1 = findViewById(R.id.spinner2);
        String[] items1 = new String[]{"MALE", "FEMALE"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

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



        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email.getText().toString().trim();
                String password2 = password.getText().toString().trim();

                FirebaseDatabase.getInstance().getReference().child("landingpage").setValue("check");



                if (TextUtils.isEmpty(email2)) {
                    Toast.makeText(delete_profile.this, "PLEASE ENTER THE EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(delete_profile.this, "PLEASE ENTER PASWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password2.length() > 6) {
                    Toast.makeText(delete_profile.this, "PASSWORD IS TO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email2, password2)
                        .addOnCompleteListener(delete_profile.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {


                                    Toast.makeText(delete_profile.this,"task is successfull",Toast.LENGTH_SHORT).show();




                                    if (dropdown.getSelectedItem().toString() == "BUYER") {
                                        HashMap<String, Object> imap = new HashMap<String, Object>();
                                        imap.put("name", name.getText().toString());
                                        imap.put("email", email.getText().toString());
                                        imap.put("password", password.getText().toString());
                                        imap.put("Company", company.getText().toString());
                                        imap.put("phone", phone.getText().toString());
                                        imap.put("city", city.getText().toString());
                                        imap.put("gender",gen.getText().toString());
                                        imap.put("descript", descript.getText().toString());





                                        FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("BUYER").child(firebaseAuth.getCurrentUser().getUid().toString()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(delete_profile.this, "DATA ADDED TO BUYER", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {

                                        if (dropdown.getSelectedItem().toString() == "SELLER") {
                                            HashMap<String, Object> pmap = new HashMap<String, Object>();
                                            pmap.put("name", name.getText().toString());
                                            pmap.put("email", email.getText().toString());
                                            pmap.put("password", password.getText().toString());
                                            pmap.put("Company", company.getText().toString());
                                            pmap.put("phone", phone.getText().toString());
                                            pmap.put("city", city.getText().toString());
                                            pmap.put("gender",dropdown1.getSelectedItem().toString());
                                            pmap.put("descript", descript.getText().toString());


                                            FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("SELLER").child(firebaseAuth.getCurrentUser().getUid().toString()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(delete_profile.this, "DATA ADDED TO SELLER", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            // Sign in success, update UI with the signed-in user's information

                                        }

                                    }


                                }
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(delete_profile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
