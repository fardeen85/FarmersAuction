package com.example.landingpage;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class  myadapter5 extends FirebaseRecyclerAdapter<model5,myadapter5.myViewholder> {
    Context context;
    private FirebaseAuth auth;
    String namet, email, image;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options5
     */
    public myadapter5(@NonNull FirebaseRecyclerOptions<model5> options5) {
        super(options5);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull model5 model5) {
        holder.NAME.setText(model5.getName());
        holder.TYPE.setText(model5.getType());
        holder.PRICE.setText(model5.getPrice());
        holder.DESCRIPTION.setText(model5.getDESCRIPTION());
        holder.CROP_ID.setText(model5.getCROP_ID());
        holder.namet.setText(model5.getnamet());
        holder.email.setText(model5.getemail());
        auth = FirebaseAuth.getInstance();
get_profile1();
    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign5, null);
        return new myadapter5.myViewholder(view);
    }


    public class myViewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView CROP_ID, NAME, TYPE, DESCRIPTION, PRICE, namet, email;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            NAME = itemView.findViewById(R.id.name);
            DESCRIPTION = itemView.findViewById(R.id.description123456);
            PRICE = itemView.findViewById(R.id.prizeif);
            TYPE = itemView.findViewById(R.id.typewas);
            CROP_ID = itemView.findViewById(R.id.cropif);
            namet = itemView.findViewById(R.id.namet);
            email = itemView.findViewById(R.id.email);
        }
    }

        public void get_profile1() {
            FirebaseDatabase.getInstance().getReference("landing_page")
                    .child("users")
                    .child(auth.getCurrentUser().getUid().toString())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                namet = snapshot.child("name").getValue().toString();
                                email = snapshot.child("email").getValue().toString();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }
