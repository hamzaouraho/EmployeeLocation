package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class missionlist extends AppCompatActivity {

    private static final String EXTRA_SAID = "com.example.suiviemployer.EXTRA_keyy";

    RecyclerView recyclerVieww;
    DatabaseReference databasee;
    HolderMyadapter myadapterr;
    ArrayList<Mission> listt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);

        Intent intent = getIntent();
        String keyyy = intent.getStringExtra(EmployerHome.EXTRA_SAID);

        recyclerVieww = findViewById(R.id.userList);
        databasee = FirebaseDatabase.getInstance().getReference("Employer").child(keyyy).child("mission");
        recyclerVieww.setHasFixedSize(true);
        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));

        listt = new ArrayList<Mission>();
        myadapterr = new HolderMyadapter(this,listt);
        recyclerVieww.setAdapter(myadapterr);

        databasee.addValueEventListener(new ValueEventListener() {
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
    }
}