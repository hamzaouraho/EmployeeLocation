package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    public static final String EXTRA_HAMZA = "com.example.suiviemployer.EXTRA_HAMZA";

    EditText mfullname,memailregister,mpasswordregister,mphoneregister;
    Button mbuttonregister;
    TextView mloginregister;
    FirebaseAuth mfAuth;
    DatabaseReference emplref;
    ProgressBar mprogressregister;
    String EmailData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);


        emplref = FirebaseDatabase.getInstance().getReference().child("Employer");
        mfullname = findViewById(R.id.fullname);
        memailregister = findViewById(R.id.emailregister);
        mpasswordregister = findViewById(R.id.passwordregister);
        mphoneregister = findViewById(R.id.phoneregister);
        mbuttonregister = findViewById(R.id.buttonregister);
        mloginregister = findViewById(R.id.loginregister);

        mfAuth = FirebaseAuth.getInstance();
        mprogressregister = findViewById(R.id.progressregister);


        mbuttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mpasswordregister.getText().toString().trim();
                String name = mfullname.getText().toString().trim();
                String phone = mphoneregister.getText().toString().trim();
                String email = memailregister.getText().toString().trim();

                mprogressregister.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(email)){
                    memailregister.setError("Email is Required.");
                    mprogressregister.setVisibility(View.INVISIBLE);
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mpasswordregister.setError("Password is required.");
                    mprogressregister.setVisibility(View.INVISIBLE);
                    return;
                }

                if (password.length() < 7){
                    mpasswordregister.setError("Password Must be more then 7 characterse.");
                    mprogressregister.setVisibility(View.INVISIBLE);
                    return;
                }


                mfAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            EmailData = email;
                            Toast.makeText(Register.this,"User Created.", Toast.LENGTH_SHORT).show();
                            Employer employer = new Employer(name,phone,email,password);
                            emplref.push().setValue(employer);

                            Intent intent = new Intent(getApplicationContext(),RetrieveData.class);
                            intent.putExtra(EXTRA_HAMZA, email);
                            startActivity(intent);

                        }else{

                            Toast.makeText(Register.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mprogressregister.setVisibility(View.INVISIBLE);

                        }
                    }
                });


            }
        });


        mloginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}