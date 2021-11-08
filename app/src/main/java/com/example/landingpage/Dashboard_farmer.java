package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard_farmer extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,logout;
    private FirebaseAuth firebaseAuth;
    TextView email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_farmer);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        logout = findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_farmer.this, crop_2.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_farmer.this, retrive_crop.class);
                startActivity(i);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_farmer.this, Signup_update.class);
                startActivity(i);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_farmer.this, bid_detail.class);
                startActivity(i);
                finish();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_farmer.this, store.class);
                startActivity(i);
                finish();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_farmer.this, bid_detail.class);
                startActivity(i);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser =FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(Dashboard_farmer.this,"YOU! ARE LOGOUT THANKYOU FOR CHOOSING FARMERS AUCTION",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Dashboard_farmer.this,login_Form.class);
                    startActivity(i);
                    finish();
                    firebaseAuth.signOut();

                }else{
                    Toast.makeText(Dashboard_farmer.this,"PLEASE LOGIN FIRST",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
