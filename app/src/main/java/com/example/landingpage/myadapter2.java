package com.example.landingpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class  myadapter2 extends FirebaseRecyclerAdapter<model2,myadapter2.myViewholder> {
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options1
     */
    public myadapter2(@NonNull FirebaseRecyclerOptions<model2> options1) {
        super(options1);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull model2 model2) {
        holder.name.setText(model2.getname());
        holder.email.setText(model2.getemail());
        holder.password.setText(model2.getpassword());
        holder.Company.setText(model2.getCompany());
        holder.phone.setText(model2.getphone());
        holder.city.setText(model2.getcity());
        holder.descript.setText(model2.getdescript());


    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign2, null);
        return new myadapter2.myViewholder(view);
    }


    public class myViewholder extends RecyclerView.ViewHolder {
        TextView name, email, password, Company, phone, city, descript;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            Company = itemView.findViewById(R.id.company);
            phone = itemView.findViewById(R.id.phone);
            city = itemView.findViewById(R.id.city);
            descript = itemView.findViewById(R.id.descript);

        }
    }
}
