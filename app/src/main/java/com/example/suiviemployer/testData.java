package com.example.suiviemployer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class testData extends AppCompatActivity {

    RecyclerView recyclerView;
    String groupname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groupname = getIntent().getExtras().get("groupName").toString();
        Toast.makeText(testData.this,groupname,Toast.LENGTH_SHORT);

    }
}