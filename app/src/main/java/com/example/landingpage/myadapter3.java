package com.example.landingpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.Random;
import java.util.UUID;


public class  myadapter3 extends FirebaseRecyclerAdapter<model3,myadapter3.myviewholder> {
    Context context;
    private FirebaseAuth auth;
    String namet,email,image;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options2
     */
    public myadapter3(@NonNull FirebaseRecyclerOptions<model3> options2,Context context) {
        super(options2);
        this.context = context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign3,null);
        return new myviewholder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model3 model3) {
        holder.NAME.setText(model3.getName());
        holder.TYPE.setText(model3.getType());
        holder.PRICE.setText(model3.getPrice());
        holder.DESCRIPTION.setText(model3.getDESCRIPTION());
        holder.CROP_ID.setText(model3.getCROP_ID());
        auth = FirebaseAuth.getInstance();

        get_profiles();
        holder.bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, String> omap = new HashMap<String, String>();
                omap.put("NAME",holder.NAME.getText().toString());
                omap.put("PRICE",holder.PRICE.getText().toString());
                omap.put("DESCRIPTION",holder.DESCRIPTION.getText().toString());
                omap.put("TYPE",holder.TYPE.getText().toString());
                omap.put("CROP_ ID",holder.CROP_ID.getText().toString());
                omap.put("Bid",holder.spinner3.getSelectedItem().toString());
                omap.put("name",namet);
                omap.put("email",email);
                omap.put("image",image);


                FirebaseDatabase.getInstance().getReference("landing_page")
                        .child("bids")
                        .child(getRef(position).getKey().toString())
                        .child(auth.getCurrentUser().getUid().toString())
                        .setValue(omap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context,"BID IS SET",Toast.LENGTH_SHORT).show();
                        ////child buyer ///all sam

                        String id = UUID.randomUUID().toString();
                        FirebaseDatabase.getInstance().getReference("landing_page")
                                .child("Buys")
                                .child(auth.getCurrentUser().getUid().toString())
                                .child(id)
                                .setValue(omap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(context,"Added to buys",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });



                    }
                });
            }


    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView NAME,TYPE,DESCRIPTION,PRICE,CROP_ID;
        Button bid;
        Spinner spinner3;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            CROP_ID = itemView.findViewById(R.id.cropid);
            NAME =itemView.findViewById(R.id.name);
            DESCRIPTION = itemView.findViewById(R.id.description123);
            PRICE = itemView.findViewById(R.id.prize);
            TYPE= itemView.findViewById(R.id.typew);
            bid = itemView.findViewById(R.id.bid);
            spinner3 = itemView.findViewById(R.id.spinner3);


        }
    }

    public void get_profiles(){
        FirebaseDatabase.getInstance().getReference("landing_page")
                .child("users")
                .child(auth.getCurrentUser().getUid().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            namet = snapshot.child("name").getValue().toString();
                            email = snapshot.child("email").getValue().toString();
                            image = snapshot.child("image").getValue().toString();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
