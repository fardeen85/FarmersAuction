package com.example.landingpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Signup_Form extends AppCompatActivity {

     ImageView profile;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
     EditText name, email, password, city, company, phone1, descript;
    Button reg;
    Spinner dropdown,dropdown1;
    TextView img, name1, email1, com, city1, gen, phone,dec,purpose;
    TextInputLayout nameError, emailError, phoneError, passError;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadPicture() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Images.....");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("User_images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Images Uploaded", Snackbar.LENGTH_LONG).show();
                    }

                }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            HashMap<String, Object> rmap = new HashMap<String, Object>();
                            rmap.put("image",task.getResult().toString());
                            Log.d("img","image"+task.getResult().toString());
                            FirebaseDatabase.getInstance().getReference("landing_page").child("users")
                                    .child(firebaseAuth.getCurrentUser().getUid().toString()).updateChildren(rmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Signup_Form.this,"IMAGE ADDED TO FIREBASE SUCCESSFULLY!",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "FAILED TO UPLOAD", Toast.LENGTH_LONG).show();
            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage!" + (int) progressPercent + "%");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        profile = (ImageView) findViewById(R.id.profile);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone1 = (EditText) findViewById(R.id.phone1);
        password = (EditText) findViewById(R.id.password);
        city = (EditText) findViewById(R.id.city);
        company = (EditText) findViewById(R.id.company);
        img = (TextView) findViewById(R.id.img);
        descript = (EditText) findViewById(R.id.descript);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });



        FirebaseDatabase.getInstance().getReference("landing_page");
        firebaseAuth = FirebaseAuth.getInstance();

        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"BUYER", "SELLER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        dropdown1 = findViewById(R.id.spinner2);
        String[] items1 = new String[]{"MALE", "FEMALE"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email.getText().toString().trim();
                String password2 = password.getText().toString().trim();

                FirebaseDatabase.getInstance().getReference().child("landingpage").setValue("check");



                if (TextUtils.isEmpty(email2)) {
                    Toast.makeText(Signup_Form.this, "PLEASE ENTER THE EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(Signup_Form.this, "PLEASE ENTER PASWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password2.length() > 6) {
                    Toast.makeText(Signup_Form.this, "PASSWORD IS TO SHORT", Toast.LENGTH_SHORT).show();
                }

                    firebaseAuth.createUserWithEmailAndPassword(email2, password2)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {


                                        Toast.makeText(Signup_Form.this,"task is successfull",Toast.LENGTH_SHORT).show();




                                        if (dropdown.getSelectedItem().toString() == "BUYER") {
                                            HashMap<String, String> imap = new HashMap<String, String>();
                                            imap.put("name", name.getText().toString());
                                            imap.put("email", email.getText().toString());
                                            imap.put("password", password.getText().toString());
                                            imap.put("Company", company.getText().toString());
                                            imap.put("phone", phone.getText().toString());
                                            imap.put("city", city.getText().toString());
                                            imap.put("gender",dropdown1.getSelectedItem().toString());
                                            imap.put("description", descript.getText().toString());
                                            imap.put("purpose",dropdown.getSelectedItem().toString());





                                            FirebaseDatabase.getInstance().getReference("landing_page").child("users").child(firebaseAuth.getCurrentUser().getUid().toString()).setValue(imap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Signup_Form.this, "DATA ADDED TO USER AS BUYER", Toast.LENGTH_SHORT).show();
                                                    uploadPicture();
                                                    Intent i = new Intent(Signup_Form.this, login_Form.class);
                                                    startActivity(i);
                                                }
                                            });
                                        } else {

                                            if (dropdown.getSelectedItem().toString() == "SELLER") {
                                                HashMap<String, String> pmap = new HashMap<String, String>();
                                                pmap.put("name", name.getText().toString());
                                                pmap.put("email", email.getText().toString());
                                                pmap.put("password", password.getText().toString());
                                                pmap.put("Company", company.getText().toString());
                                                pmap.put("phone", phone.getText().toString());
                                                pmap.put("city", city.getText().toString());
                                                pmap.put("gender",dropdown1.getSelectedItem().toString());
                                                pmap.put("descript", descript.getText().toString());
                                                pmap.put("purpose",dropdown.getSelectedItem().toString());


                                                FirebaseDatabase.getInstance().getReference("landing_page").child("users").child(firebaseAuth.getCurrentUser()
                                                        .getUid().toString()).setValue(pmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Signup_Form.this, "DATA ADDED TO USER BY SELLER", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(Signup_Form.this, login_Form.class);
                                                        startActivity(i);
                                                    }
                                                });
                                                // Sign in success, update UI with the signed-in user's information

                                            }

                                        }


                                    }
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signup_Form.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        });
    }
}


        