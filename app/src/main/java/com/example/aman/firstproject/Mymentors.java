package com.example.aman.firstproject;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mymentors extends AppCompatActivity {
    private RecyclerView rv;
    private MentorAdapter ma;
    private List<MentorDetails> mentorlist;private Button mentorsadd;
    private DatabaseReference db;
    private String pno;private String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymentors);
        mentorsadd=findViewById(R.id.mymentorsadd);
        pno=getIntent().getStringExtra("password");
        rv=(RecyclerView) findViewById(R.id.mymentorsrv);
        db=FirebaseDatabase.getInstance().getReference().child("users").child(pno).child("mentors");
        mentorlist=new ArrayList<>();                          //yaha se lekar 42nd line tak ko mentorsadd.setOnclickList. k
                                                                               //neeche rakhna pad sakta hai
        final  LinearLayoutManager manager=new LinearLayoutManager(this);
        key=db.push().getKey();
        mentorsadd.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
            Intent intent=new Intent(Mymentors.this,allmentors.class);
            intent.putExtra("password",pno);
            intent.putExtra("key",key);
            startActivity(intent);
            }
        });
        ma=new MentorAdapter(Mymentors.this,mentorlist,pno,key);
        rv.setLayoutManager(manager);
        rv.setAdapter(ma);
      //  Toast.makeText(Mymentors.this,key,Toast.LENGTH_LONG).show();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for(DataSnapshot snapshot:dataSnapshot.getChildren())
             {
               try{
                 if(snapshot.exists())  {
                    MentorDetails m=snapshot.getValue(MentorDetails.class);
                    mentorlist.add(m);
                    ma.notifyDataSetChanged();}
               }catch (DatabaseException e){
                    }
             }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
      super.onStart();
      db.child(key).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try{
          if(dataSnapshot.exists()){
            MentorDetails m=dataSnapshot.getValue(MentorDetails.class);
            mentorlist.add(m);
            ma.notifyDataSetChanged();;}
        }catch (DatabaseException e){
                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
             }
         });
    }
}
