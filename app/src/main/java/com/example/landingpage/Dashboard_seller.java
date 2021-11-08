package com.example.landingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard_seller extends AppCompatActivity {
Button btn11,btn2,btn3,btn4,btn1,logout;
    private FirebaseAuth firebaseAuth;
    TextView email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_seller);
        btn11 =(Button) findViewById(R.id.btn11);
        btn2 =(Button) findViewById(R.id.btn2);
        btn3 =(Button) findViewById(R.id.btn3);
        btn4 =(Button) findViewById(R.id.btn4);
        btn1 = (Button)findViewById(R.id.btn1);
        logout = findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_seller.this, sells.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_seller.this, Signup_update.class);
                startActivity(i);
            }

        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_seller.this, buys.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(Dashboard_seller.this,"YOU! ARE LOGOUT THANKYOU FOR CHOOSING FARMERS AUCTION",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Dashboard_seller.this,login_Form.class);
                    startActivity(i);
                    finish();
                    firebaseAuth.signOut();

                }else{
                    Toast.makeText(Dashboard_seller.this,"PLEASE LOGIN FIRST",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}