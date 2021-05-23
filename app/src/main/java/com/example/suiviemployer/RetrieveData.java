package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveData extends AppCompatActivity {

    public static final String EXTRA_gmail = "com.example.suiviemployer.EXTRA_gmail";

    FusedLocationProviderClient fusedLocationProviderClient;
    String keyy ;
    int a;
    ListView myListView;
    ListView myListViewMission;
    String EmailData;
    List<Employer> employerList;
    List<Mission> missionList;
    FloatingActionButton deleteMission;
    AlertDialog dialogdelete;



    DatabaseReference emplref,Missionref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_retrieve_data);

        Intent intent = getIntent();
        String email = intent.getStringExtra(Login.EXTRA_HAMZA);
        EmailData=email;
        System.out.println("hamza : "+email);
        //deleteMission = findViewById(R.id.deleteMission);

        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);




        myListView = findViewById(R.id.myListView);
        myListViewMission = findViewById(R.id.myListViewMission);
        employerList = new ArrayList<>();
        missionList = new ArrayList<>();
        //Missionref = FirebaseDatabase.getInstance().getReference("Employer").child("-M_bSgpfXzCQe4mH0_PT").child("mission");
        emplref = FirebaseDatabase.getInstance().getReference("Employer");

        a=1;

        emplref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employerList.clear();
                for (DataSnapshot employerDataSnap : snapshot.getChildren()){
                    Employer employer = employerDataSnap.getValue(Employer.class);

                    System.out.println("-------------------------  employerDataSnap.getValue() : "+employerDataSnap.getValue());
                    if(employer.getEmail().equals(email)){
                        employerList.add(employer);
                        keyy = employerDataSnap.getKey();
                    }
                }
                System.out.println("key 11 : "+keyy);
                emplref.child(keyy).child("mission").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshott) {
                        missionList.clear();

                        for (DataSnapshot missionDataSnap : snapshott.getChildren()){
                            System.out.println("111111  employerDataSnap.getValue() : "+missionDataSnap.getValue());
                            System.out.println("hamza 4");
                            Mission mission = missionDataSnap.getValue(Mission.class);
                            missionList.add(mission);
                        }
                        System.out.println("hamza 2");
                        ListAdapterMission adapterMission = new ListAdapterMission(RetrieveData.this,missionList);
                        myListViewMission.setAdapter(adapterMission);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ListAdapter adapter = new ListAdapter(RetrieveData.this,employerList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void addmission(View view) {
        startActivity(new Intent(getApplicationContext(),createMission.class));

        Intent intent = new Intent(getApplicationContext(),createMission.class);
        intent.putExtra(EXTRA_gmail, EmailData);
        startActivity(intent);
    }
    public void deletemission(View view) {
        startActivity(new Intent(getApplicationContext(),employerlist.class));
        LayoutInflater inflater = getLayoutInflater();
        View  viewww = inflater.inflate(R.layout.activity_retrieve_data,null,true);


        //nameADelete.setText("hamza" +misssionName.getText());

        myListViewMission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(),testData.class);
                intent.putExtra("groupName",intent);
                startActivity(intent);
            }
        });



        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        View viewDelete = getLayoutInflater().inflate(R.layout.activity_delete_mission,null);
        builderDelete.setView(viewDelete);
        dialogdelete = builderDelete.create();


        TextView misssionName = findViewById(R.id.misssionName);
        System.out.println("-----122222211 misssionName : "+misssionName.getText());
        System.out.println("-----122222211 misssionName : "+misssionName.getText());

        dialogdelete.show();

    }
}