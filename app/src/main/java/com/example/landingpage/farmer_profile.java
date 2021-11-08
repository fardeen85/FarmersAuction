package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class farmer_profile extends AppCompatActivity {
    RecyclerView recview2;
    myadapter2 myadapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_profile);
        recview2 =findViewById(R.id.recview2);
        recview2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model2> options1 =
                new FirebaseRecyclerOptions.Builder<model2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("landing_page").child("users").child("BUYER"), model2.class)
                        .build();

        myadapter2 = new myadapter2(options1);
        recview2.setAdapter(myadapter2);

    }

    @Override
    public void onStart() {
        super.onStart();
        myadapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadapter2.stopListening();
    }

}

