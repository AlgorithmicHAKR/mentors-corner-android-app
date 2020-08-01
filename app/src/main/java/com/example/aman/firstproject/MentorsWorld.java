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
import java.util.List;

public class MentorsWorld extends AppCompatActivity {

   private RecyclerView rv;
    DatabaseReference mref;
    DatabaseReference db;
    List<BlogDetails> blogList;
    List<MentorDetails> lista;
   private mentorworldadapter adapter;
    MentorAdapter ma;
    Button Add_Posts;
    Button doubts;
    Button update;
    private Button solvedoubts;
    private String key1="";
    private String admin="";
    private String key="";private String password="";
    private String id="";
    @Override
    protected void onStart() {
        super.onStart();
        mref.child(key).addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
          {
            try{
                BlogDetails blog = dataSnapshot.getValue(BlogDetails.class);
                blogList.add(blog);
                adapter.notifyDataSetChanged();
            }
            catch (DatabaseException e){}
           }
        }
        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
    private DatabaseReference nb;private List<BlogDetails> blist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentors_world);
        password=getIntent().getStringExtra("password");
        admin=getIntent().getStringExtra("admin");
        key1=getIntent().getStringExtra("key");
        rv=findViewById(R.id.rrv);
        solvedoubts=(Button) findViewById(R.id.solvedoubts);
        blist=new ArrayList<>();
        id=getIntent().getStringExtra("id");
        if(admin!=null&&admin.equals("no")){
            final LinearLayoutManager manager=new LinearLayoutManager(this);
          //  Toast.makeText(MentorsWorld.this,"idhar "+id,Toast.LENGTH_LONG).show();
            nb=FirebaseDatabase.getInstance().getReference().child("mentors").child(id).child("blogs");
            nb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    adapter=new mentorworldadapter(MentorsWorld.this,blist);
                    rv.setLayoutManager(manager);
                    rv.setAdapter(adapter);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        BlogDetails b=snapshot.getValue(BlogDetails.class);
                        blist.add(b);
                       // Toast.makeText(MentorsWorld.this,"idhar "+id,Toast.LENGTH_LONG).show();
                        Toast.makeText(MentorsWorld.this,"idhar "+b.getTopic(),Toast.LENGTH_SHORT).show();

                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        Add_Posts=(Button)findViewById(R.id.Add_Posts);
        doubts=(Button)findViewById(R.id.doubts);
        update=(Button) findViewById(R.id.updateInfo);
        mref= FirebaseDatabase.getInstance().getReference().child("mentors").child(password).child("blogs");
        Toast.makeText(MentorsWorld.this,password+" "+id,Toast.LENGTH_LONG).show();
        if(!admin.isEmpty()&&admin.equals("no")){
            Add_Posts.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
            doubts.setVisibility(View.VISIBLE);
            solvedoubts.setVisibility(View.GONE);
        }
        else{
            solvedoubts.setVisibility(View.VISIBLE);
            Add_Posts.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            doubts.setVisibility(View.GONE);
        }
        solvedoubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              key=mref.push().getKey();
              Intent intent=new Intent(MentorsWorld.this,doubtsforum.class);
              intent.putExtra("id",password);
              intent.putExtra("password",password);
              intent.putExtra("key",password);
              intent.putExtra("mentor","yes");
              startActivity(intent);
            }
        });
        Add_Posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          key=mref.push().getKey();
          Intent intent=new Intent(MentorsWorld.this, addblog.class);
          intent.putExtra("password",password);
          intent.putExtra("key",key);
          startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent=new Intent(MentorsWorld.this,updateInfo.class);
           intent.putExtra("password",password);
           intent.putExtra("key",key);
           startActivity(intent);
            }
        });
        doubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentorsWorld.this,doubtsforum.class);
                intent.putExtra("password",password);
                intent.putExtra("key",key1);
                intent.putExtra("id",id);
                intent.putExtra("mentor","no");
                startActivity(intent);
            }
        });
        blogList=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new mentorworldadapter(MentorsWorld.this,blogList);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
}

}
