package com.example.aman.firstproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class allmentors extends AppCompatActivity {
    private RecyclerView rv;private MentorAdapter ma;
    private DatabaseReference db;private List<MentorDetails> allmentorslist;
    private String pno="";
    private String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allmentors);
    }

    @Override
    protected void onStart() {
        super.onStart();
        allmentorslist=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference().child("All_Mentors");
        key=getIntent().getStringExtra("key");
        pno=getIntent().getStringExtra("password");
        rv=findViewById(R.id.allmentorsrv);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        ma=new MentorAdapter(allmentors.this,allmentorslist,4,pno,key);
        rv.setLayoutManager(manager);
        rv.setAdapter(ma);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           //     Toast.makeText(allmentors.this,String.valueOf(dataSnapshot.getChildrenCount()),Toast.LENGTH_LONG).show();
                   int x=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
              //      Toast.makeText(allmentors.this,String.valueOf(x),Toast.LENGTH_LONG).show();
                  if(dataSnapshot.exists()) {
                      x++;
                      MentorDetails mentor = snapshot.getValue(MentorDetails.class);
                      allmentorslist.add(mentor);

                      ma.notifyDataSetChanged();
                  }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
