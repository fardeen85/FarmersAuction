package com.example.landingpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class  myadapter4 extends FirebaseRecyclerAdapter<model4,myadapter4.myviewholder> {
    Context context;
    private FirebaseAuth auth;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options4
     */
    public myadapter4(@NonNull FirebaseRecyclerOptions<model4> options4, Context context) {
        super(options4);
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign4, null);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model4 model4) {

        holder.NAME.setText(model4.getName());
        holder.TYPE.setText(model4.getType());
        holder.PRICE.setText(model4.getPrice());
        holder.DESCRIPTION.setText(model4.getDESCRIPTION());
        holder.CROP_ID.setText(model4.getCROP_ID());
        holder.namet.setText(model4.getnamet());
        holder.email.setText(model4.getemail());
        auth = FirebaseAuth.getInstance();
        Glide.with(holder.img2).load(model4.getimage()).into(holder.img2);

        holder.accbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, String> fmap = new HashMap<String, String>();
                fmap.put("NAME",holder.NAME.getText().toString());
                fmap.put("PRICE",holder.PRICE.getText().toString());
                fmap.put("DESCRIPTION",holder.DESCRIPTION.getText().toString());
                fmap.put("TYPE",holder.TYPE.getText().toString());
                fmap.put("CROP_ ID",holder.CROP_ID.getText().toString());
                fmap.put("name",holder.namet.getText().toString());
                fmap.put("email",holder.email.getText().toString());
                Glide.with(holder.img2).load(model4.getimage()).into(holder.img2);
                FirebaseDatabase.getInstance().getReference("landing_page")
                        .child("bids")
                        .child(getRef(position).getKey().toString())
                        .child(auth.getCurrentUser().getUid().toString())
                        .setValue(fmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "BID is Accepted", Toast.LENGTH_SHORT).show();
                        ////child buyer ///all sam
                        FirebaseDatabase.getInstance().getReference("landing_page")
                                .child("sells")
                                .child(getRef(position).getKey().toString())
                                .child(auth.getCurrentUser().getUid().toString())
                                .setValue(fmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(context, "Added to sells", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }
        });
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1, img2;
        Button accbid;
        TextView CROP_ID, NAME, TYPE, DESCRIPTION, PRICE, namet, email;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            NAME = itemView.findViewById(R.id.name);
            DESCRIPTION = itemView.findViewById(R.id.description1234);
            PRICE = itemView.findViewById(R.id.prize);
            TYPE = itemView.findViewById(R.id.typew);
            CROP_ID = itemView.findViewById(R.id.cropid);
            namet = itemView.findViewById(R.id.namet);
            email = itemView.findViewById(R.id.email);
            accbid = itemView.findViewById(R.id.accbid);

        }
    }

}

