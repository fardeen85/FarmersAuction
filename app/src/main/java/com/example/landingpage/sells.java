package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class sells extends AppCompatActivity {
    RecyclerView recview3;
    myadapter3 myadapter3;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sells);
        recview3 =findViewById(R.id.recview3);
        recview3.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseRecyclerOptions<model3> options2 =
                new FirebaseRecyclerOptions.Builder<model3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("landing_page").child("crops"), model3.class)
                        .build();

        myadapter3 = new myadapter3(options2,this);
        recview3.setAdapter(myadapter3);


    }
    @Override
    public void onStart() {
        super.onStart();
        myadapter3.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadapter3.stopListening();
    }
}