package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    public static final String EXTRA_HAMZA = "com.example.suiviemployer.EXTRA_HAMZA";
    TextView mregisterlogin, mforgetlogin;
    EditText memaillogin, mpasswordlogin;
    Button mbuttonlogin;
    FirebaseAuth fAuth;
    ProgressBar mprogresslogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mregisterlogin = findViewById(R.id.registerlogin);
        mforgetlogin = findViewById(R.id.forgetlogin);
        memaillogin = findViewById(R.id.emaillogin);
        mpasswordlogin = findViewById(R.id.passwordlogin);
        mbuttonlogin = findViewById(R.id.buttonlogin);
        fAuth = FirebaseAuth.getInstance();
        mprogresslogin = findViewById(R.id.progresslogin);


        mbuttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = memaillogin.getText().toString().trim();
                String password = mpasswordlogin.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    memaillogin.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mpasswordlogin.setError("Password is required.");
                    return;
                }

                if (password.length() < 7){
                    mpasswordlogin.setError("Password Short.");
                    return;
                }
                mprogresslogin.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Welcome to your Account.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),EmployerHome.class);
                            intent.putExtra(EXTRA_HAMZA, email);
                            startActivity(intent);
                            //startActivity(new Intent(getApplicationContext(),RetrieveData.class));
                        }else{
                            Toast.makeText(Login.this, "Invalid Email or Password ! ", Toast.LENGTH_SHORT).show();
                            mprogresslogin.setVisibility(View.INVISIBLE);
                        }
                    }
                });


            }
        });

        mforgetlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetemail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetemail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetemail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Login.this,"Reset Link Send Your Email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error ! Reset Link is Not Send",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();

            }
        });




        mregisterlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}