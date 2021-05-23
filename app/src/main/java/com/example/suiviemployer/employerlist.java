package com.example.suiviemployer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class employerlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    HolderAdapterEmployer myadapter;
    ArrayList<Employer> list;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);


        recyclerView = findViewById(R.id.employerList);
        database = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Employer").orderByChild("email").equalTo(email);;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Employer>();
        myadapter = new HolderAdapterEmployer(this,list);
        recyclerView.setAdapter(myadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Employer employer = dataSnapshot.getValue(Employer.class);
                    list.add(employer);
                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}