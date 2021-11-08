package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class bid_detail extends AppCompatActivity {
    RecyclerView recview4;
    myadapter4 myadapter4;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_detail);
                recview4 =findViewById(R.id.recview4);
                recview4.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();

                FirebaseRecyclerOptions<model4> options4 =
                        new FirebaseRecyclerOptions.Builder<model4>()
                                .setQuery(FirebaseDatabase.getInstance().getReference("landing_page").child("bids").child(firebaseAuth.getCurrentUser().getUid().toString()), model4.class)
                                .build();

                myadapter4 = new myadapter4(options4,this);
                recview4.setAdapter(myadapter4);

            }


            @Override
            public void onStart() {
                super.onStart();
                myadapter4.startListening();
            }

            @Override
            public void onStop() {
                super.onStop();
                myadapter4.stopListening();
            }
        }