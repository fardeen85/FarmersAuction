package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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

import java.util.HashMap;

public class crop_2 extends AppCompatActivity {

    Button addbtn,upbtn,delebtn,seebtn;
    EditText namet, price, description, type,cropid;
    ImageView profile1;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_2);
        addbtn = (Button) findViewById(R.id.addbtn);
        upbtn = (Button) findViewById(R.id.upbtn);
        delebtn = (Button) findViewById(R.id.delebtn);
        namet = (EditText) findViewById(R.id.namet);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        type = (EditText) findViewById(R.id.type);
        cropid = (EditText) findViewById(R.id.cropid);

        FirebaseDatabase.getInstance().getReference("landing_page");

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(crop_2.this, crop.class);
                startActivity(i);
                finish();
            }
        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(crop_2.this, crop_update.class);
                startActivity(i);
                finish();
            }
        });
        delebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(crop_2.this, crop_delete.class);
                startActivity(i);
                finish();
            }
        });
    }
}