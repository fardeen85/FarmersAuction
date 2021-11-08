package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class login_Form extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ImageView profile;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    private EditText name, email, password, city, company, phone1;
    Button reg, log,logr;
    RadioButton male, fem;
    TextView img, name1, email1, com, city1, gen, phone;
    String purpose;
    boolean isNameValid, isEmailValid, isPasswordValid, isCityValid, isCompanyValid, isPhoneVaild;
    TextInputLayout nameError, emailError, phoneError, passError;
    private FirebaseAuth firebaseAuth;
    ProgressDialog mloadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);

        profile = (ImageView) findViewById(R.id.profile);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone1);
        password = (EditText) findViewById(R.id.password);
        city = (EditText) findViewById(R.id.city);
        company = (EditText) findViewById(R.id.company);

        img = (TextView) findViewById(R.id.img);
        com = (TextView) findViewById(R.id.company);
        phone = (TextView) findViewById(R.id.phone);
        log = (Button) findViewById(R.id.log);
        reg = (Button) findViewById(R.id.reg);
        logr = (Button)findViewById(R.id.logr);
        mloadingBar = new ProgressDialog(login_Form.this);
        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();
logr.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(login_Form.this,Signup_Form.class);
        startActivity(i);
        finish();
    }
});
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email.getText().toString();
                String password2 = password.getText().toString();

                if (email2.isEmpty() || !email2.contains("@")) {
                    showError(email, "Email is not valid");
                } else if (password2.isEmpty() || password.length() < 6) {
                    showError(password, "Password mut be 6 characters");
                } else {
                    mloadingBar.setTitle("Login");
                    mloadingBar.setMessage("Please wait while check your credentials");
                    mloadingBar.setCanceledOnTouchOutside(false);
                    mloadingBar.show();
                    firebaseAuth.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase.getInstance().getReference("landing_page").child("users").child(firebaseAuth.getCurrentUser()
                                        .getUid().toString()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()){

                                            purpose = snapshot.child("purpose").getValue().toString();
                                            Toast.makeText(login_Form.this, "login successful"+ purpose, Toast.LENGTH_SHORT).show();
                                            check();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }
                    });
                }

            }

        });
    }


    public void check(){

      /*  if (purpose == "BUYER")
        {
            Toast.makeText(login_Form.this,"welcome",Toast.LENGTH_SHORT).show();
            Intent j = new Intent(login_Form.this, Dashboard_seller.class);
            Log.d("tag","checked"+purpose);
            startActivity(j);
        }
        else if(purpose == null){

            Toast.makeText(login_Form.this,purpose,Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(login_Form.this, Dashboard_farmer.class);
            startActivity(i);
            Log.d("tag","checked");
        }*/

        switch (purpose){

            case "BUYER":
                Toast.makeText(login_Form.this,"welcome",Toast.LENGTH_SHORT).show();
                Intent j = new Intent(login_Form.this, Dashboard_seller.class);
                Log.d("tag","checked"+purpose);
                startActivity(j);
                break;
            case "" :

                Toast.makeText(login_Form.this,purpose,Toast.LENGTH_SHORT).show();
                break;

            default:
                Intent i = new Intent(login_Form.this, Dashboard_farmer.class);
                startActivity(i);
                Log.d("tag","checked");
                break;

        }
    }
private void showError(EditText input,String s){
    input.setError(s);
    input.requestFocus();
}
}