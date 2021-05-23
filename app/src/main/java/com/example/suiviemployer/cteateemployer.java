package com.example.suiviemployer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cteateemployer extends AppCompatActivity {

    EditText emplfullname, emplphone, emplemail, emplpassword;
    TextView empllogin;
    Button emplbutton;
    DatabaseReference emplref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cteateemployer);

        emplfullname = findViewById(R.id.emplfullname);
        emplphone = findViewById(R.id.emplphone);
        emplemail = findViewById(R.id.emplemail);
        emplpassword = findViewById(R.id.emplpassword);
        emplbutton = findViewById(R.id.emplbutton);
        empllogin = findViewById(R.id.empllogin);
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));

        emplref = FirebaseDatabase.getInstance().getReference().child("Employer");

        emplbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setId(Integer.parseInt(v.getId()+"1"));
                insertEmployer();
            }
        });

        empllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                View vieww = v.findViewById(R.id.emplfullname);
            }
        });

    }

    private void insertEmployer(){
        String fullname = emplfullname.getText().toString();
        String phone = emplphone.getText().toString();
        String email = emplemail.getText().toString();
        String password = emplpassword.getText().toString();

        Employer employer = new Employer(fullname,phone,email,password);

        emplref.push().setValue(employer);
        Toast.makeText(cteateemployer.this,"Inserted",Toast.LENGTH_SHORT).show();

    }

}