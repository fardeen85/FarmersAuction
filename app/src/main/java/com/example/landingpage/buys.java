package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class buys extends AppCompatActivity {
    RecyclerView recview5;
    myadapter5 myadapter5;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buys);
        recview5 =findViewById(R.id.recview5);
        firebaseAuth = FirebaseAuth.getInstance();
        recview5.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model5> options5 =
                new FirebaseRecyclerOptions.Builder<model5>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("landing_page").child("sells").child(firebaseAuth.getCurrentUser().getUid().toString()), model5.class)
                        .build();

        myadapter5 = new myadapter5(options5);
        recview5.setAdapter(myadapter5);


    }
    @Override
    public void onStart() {
        super.onStart();
        myadapter5.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadapter5.stopListening();
    }
}