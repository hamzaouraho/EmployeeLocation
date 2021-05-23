package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

public class EmployerHome extends AppCompatActivity  {

    private static final String TAG = "EmployerHome";
    public static final String EXTRA_gmail = "com.example.suiviemployer.EXTRA_gmail";
    public static final String EXTRA_SAID = "com.example.suiviemployer.EXTRA_keyy";
    RecyclerView recyclerVieww,recyclerView;
    DatabaseReference database;
    HolderAdapterEmployer myadapter;
    ArrayList<Employer> list;
    TextView missionname;
    String email,keyy;
    List<Employer> employerList;
    List<Mission> missionList;
    DatabaseReference databasee;
    HolderMyadapter myadapterr;
    ArrayList<Mission> listt;
    FusedLocationProviderClient fusedLocationProviderClient;
    FusedLocationProviderClient fusedLocationProviderClientt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_employer_home);

        missionname = findViewById(R.id.missionname);

        fusedLocationProviderClientt = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            System.out.println("latitude11 : : :");
            if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                System.out.println("latitude2222 : : :");
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Double lat = location.getLatitude();
                            System.out.println("latitude333 : : :"+lat);
                            Double lon = location.getLongitude();
                            missionname.setText(lat+" , "+lon);
                            Toast.makeText(EmployerHome.this,"Success",Toast.LENGTH_SHORT);

                        }
                    }
                });
            }else {
                System.out.println("latitude444444 : : :");

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }


        Intent intent = getIntent();
        String email = intent.getStringExtra(Login.EXTRA_HAMZA);

        System.out.println("email : : : : "+email);

        recyclerView = findViewById(R.id.employerList);
        database =  FirebaseDatabase.getInstance().getReference("Employer");;
        recyclerVieww = findViewById(R.id.userList);
        recyclerVieww.setHasFixedSize(true);
        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        list = new ArrayList<Employer>();
        myadapter = new HolderAdapterEmployer(this,list);
        recyclerView.setAdapter(myadapter);
        employerList = new ArrayList<>();
        missionList = new ArrayList<>();

        recyclerVieww = findViewById(R.id.userList);
        System.out.println("key : : : : "+keyy);
        //recyclerVieww.setHasFixedSize(true);
        //recyclerVieww.setLayoutManager(new LinearLayoutManager(this));

        listt = new ArrayList<Mission>();
        myadapterr = new HolderMyadapter(this,listt);
        recyclerVieww.setAdapter(myadapterr);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employerList.clear();
                for (DataSnapshot employerDataSnap : snapshot.getChildren()){
                    Employer employer = employerDataSnap.getValue(Employer.class);

                    if(employer.getEmail().equals(email)){
                        list.add(employer);
                        keyy = employerDataSnap.getKey();
                    }
                }
                database.child(keyy).child("mission").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshott) {
                        missionList.clear();

                        for (DataSnapshot missionDataSnap : snapshott.getChildren()){
                            Mission mission = missionDataSnap.getValue(Mission.class);
                            listt.add(mission);
                        }
                        myadapterr.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("dataSnapshot : : : "+snapshot.getValue().toString());
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Employer employer = dataSnapshot.getValue(Employer.class);
                    list.add(employer);
                    System.out.println("kay : : : "+snapshot.getKey());

                    keyy = "-M_bWPLHdId3yhE0zYUI";
                }
                keyy = "-M_bWPLHdId3yhE0zYUI";
                databasee.child(keyy).child("mission").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Mission mission = dataSnapshot.getValue(Mission.class);
                            listt.add(mission);

                        }
                        myadapterr.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); */







    }

    public void logout(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            System.out.println("latitude11 : : :");
            if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                System.out.println("latitude2222 : : :");
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Double lat = location.getLatitude();
                            System.out.println("latitude333 : : :"+lat);
                            Double lon = location.getLongitude();
                            missionname.setText(lat+" , "+lon);
                            Toast.makeText(EmployerHome.this,"Success",Toast.LENGTH_SHORT);

                        }else {
                            System.out.println("latitude55555: : :");
                        }
                    }

                });
            }else {
                System.out.println("latitude444444 : : :");

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
        }
    }






}