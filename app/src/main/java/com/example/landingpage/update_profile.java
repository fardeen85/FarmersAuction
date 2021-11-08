package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


public class update_profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText name, email, password, city, company, phone1, descript;
    TextView img, name1, email1, com, city1, gen, phone, pass, dec;
    Spinner dropdown1;

    public void loaddata() {
        FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("BUYER").child(firebaseAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String name, email, phone1, password, city, Company, descript;

                    name = snapshot.child("users").getValue().toString();
                    email = snapshot.child("users").getValue().toString();
                    phone1 = snapshot.child("users").getValue().toString();
                    password = snapshot.child("users").getValue().toString();
                    city = snapshot.child("users").getValue().toString();
                    Company = snapshot.child("users").getValue().toString();
                    descript = snapshot.child("users").getValue().toString();


                    name1.setText(name);
                    email1.setText(email);
                    phone.setText(phone1);
                    pass.setText(password);
                    city1.setText(city);
                    com.setText(Company);
                    dec.setText(descript);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("SELLER").child(firebaseAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {

                String name, email, phone1, password, city, Company, descript;

                name = snapshot.child("users").getValue().toString();
                email = snapshot.child("users").getValue().toString();
                phone1 = snapshot.child("users").getValue().toString();
                password = snapshot.child("users").getValue().toString();
                city = snapshot.child("users").getValue().toString();
                Company = snapshot.child("users").getValue().toString();
                descript = snapshot.child("users").getValue().toString();

                name1.setText(name);
                email1.setText(email);
                phone.setText(phone1);
                pass.setText(password);
                city1.setText(city);
                com.setText(Company);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone1 = (EditText) findViewById(R.id.phone1);
        password = (EditText) findViewById(R.id.password);
        city = (EditText) findViewById(R.id.city);
        company = (EditText) findViewById(R.id.company);
        phone = (TextView) findViewById(R.id.phone);
        descript = (EditText) findViewById(R.id.descript);
        pass = (TextView) findViewById(R.id.password);
        dropdown1 = findViewById(R.id.spinner2);

    }
}