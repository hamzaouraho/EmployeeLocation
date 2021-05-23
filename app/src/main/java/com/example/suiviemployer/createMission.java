package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class createMission extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private TextView mDisplayDate,datefin,tvDate;
    private EditText missionname,editTextTextMultiLine;
    private Button savemission;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListenerr;
    private DatabaseReference MissionRef;
    private String key;
    List<Employer> employerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_mission);

        Intent intent = getIntent();
        String EmailData = intent.getStringExtra(RetrieveData.EXTRA_gmail);

        System.out.println("hamza ouraho : "+EmailData);

        missionname = findViewById(R.id.missionname);
        tvDate = findViewById(R.id.tvDate);
        savemission = findViewById(R.id.savemission);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        datefin = (TextView) findViewById(R.id.datefin);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        MissionRef = FirebaseDatabase.getInstance().getReference().child("Employer");
        employerList = new ArrayList<>();
        // get key :
        System.out.println("bababa");
        MissionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("mamama");
                employerList.clear();
                for (DataSnapshot employerDataSnap : snapshot.getChildren()){
                    Employer employer = employerDataSnap.getValue(Employer.class);

                    if(employer.getEmail().equals(EmailData)){
                        employerList.add(employer);
                        key = employerDataSnap.getKey();
                        System.out.println("Key 00 : : "+key);
                        //System.out.println("getChildrenCount () : "+employerDataSnap.child("mission").getChildrenCount());

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //  Save Mission
        savemission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String missionnam = missionname.getText().toString();
                String tvDat = tvDate.getText().toString();
                String datefi = datefin.getText().toString();
                String editTextTextMultiLin = editTextTextMultiLine.getText().toString();

                Mission mission = new Mission(missionnam,editTextTextMultiLin,datefi,tvDat);
                System.out.println("Key 00 : : "+key);
                MissionRef.child(key).child("mission").push().setValue(mission);
                Toast.makeText(createMission.this,"Inserted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        createMission.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        datefin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int yearr = cal.get(Calendar.YEAR);
                int monthh = cal.get(Calendar.MONTH);
                int dayy = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        createMission.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerr,
                        yearr,monthh,dayy);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        mDateSetListenerr = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yearr, int monthh, int dayy) {
                monthh = monthh + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + monthh + "/" + dayy + "/" + yearr);

                String datee = monthh + "/" + dayy + "/" + yearr;
                datefin.setText(datee);
            }
        };
    }


}

