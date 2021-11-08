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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class  myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {
    Context context;
    private FirebaseAuth auth;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options,Context context) {
        super(options);
        this.context = context;
    }


    @NonNull
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.NAME.setText(model.getName());
        holder.TYPE.setText(model.getType());
        holder.PRICE.setText(model.getPrice());
        holder.DESCRIPTION.setText(model.getDESCRIPTION());
        auth = FirebaseAuth.getInstance();
        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("landing_page").child("crops").child(auth.getCurrentUser().getUid().toString())
                        .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(context,"Crop deleted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,null);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView NAME,TYPE,DESCRIPTION,PRICE;
        Button deletebutton;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img);
            NAME =itemView.findViewById(R.id.namet);
            DESCRIPTION = itemView.findViewById(R.id.description);
            PRICE = itemView.findViewById(R.id.price);
            TYPE= itemView.findViewById(R.id.type);
            deletebutton = itemView.findViewById(R.id.deletbutton);


        }
    }
}
