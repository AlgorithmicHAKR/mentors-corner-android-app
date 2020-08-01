package com.example.aman.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class doubtsforum extends AppCompatActivity {
    private RecyclerView rv;
    private EditText question;
    private Button ask;
    private DatabaseReference userdatabase;
    private DatabaseReference mentordatabase;
    private String mentor="";
    private String userpassword;
    private String mentorpassword;
    private String key;
    private List<Question> qlist;
    private DoubtsAdapter da;
    private List<QuestionFromMentor> qflist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubtsforum);
        mentor=getIntent().getStringExtra("mentor");
        key=getIntent().getStringExtra("key");
        qlist=new ArrayList<>();
        qflist=new ArrayList<>();
        mentorpassword =getIntent().getStringExtra("id");
        userpassword=getIntent().getStringExtra("password");
       LinearLayoutManager manager=new LinearLayoutManager(this);
        rv=(RecyclerView) findViewById(R.id.doubtsrv);
        rv.setLayoutManager(manager);

       if(mentor.equals("no")){

           userdatabase= FirebaseDatabase.getInstance().getReference().child("users").
                child(userpassword).
                child("mentors").
                child(key).
                child("doubts").
                child("question");
       }
        question=(EditText) findViewById(R.id.doubtsedittext);
        ask=(Button) findViewById(R.id.ask);
        if(mentor.equals("no")){
            ask.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
        }
        mentordatabase=FirebaseDatabase.getInstance().getReference().child("mentors").child(mentorpassword).child("doubts");
        if(mentor.equals("no")){
            da=new DoubtsAdapter(doubtsforum.this,qlist,mentor,userpassword,key, mentorpassword);
            rv.setAdapter(da);
            userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Question q=snapshot.getValue(Question.class);
                        qlist.add(q);
                    }
                    da.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(mentor.equals("yes")){
            qflist=new ArrayList<>();
            da=new DoubtsAdapter(doubtsforum.this,qflist,mentor,userpassword,key, mentorpassword,-1);
            rv.setAdapter(da);

            //   Toast.makeText(doubtsforum.this,"HUIIH "+mentorpassword,Toast.LENGTH_LONG).show();
            mentordatabase.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   try {
                      if(snapshot.exists()){
                        QuestionFromMentor q = snapshot.getValue(QuestionFromMentor.class);
                        qflist.add(q);
                    //   Toast.makeText(doubtsforum.this,"HUIIH "+q.getUserpassword()+" "+q.getQuestion(),Toast.LENGTH_LONG).show();
                        da.notifyDataSetChanged();
                       }
                   }
                  catch(DatabaseException e){}
                  }

              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
        }
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=userdatabase.push().getKey();
                userdatabase.child(key).child("question").setValue(question.getText().toString());
                userdatabase.child(key).child("doubtkey").setValue(key);
                mentordatabase.child(key).child("userpassword").setValue(userpassword);
                mentordatabase.child(key).child("question").setValue(question.getText().toString());
                mentordatabase.child(key).child("key").setValue(key);
                userdatabase.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Question q=dataSnapshot.getValue(Question.class);
                            qlist.add(q);
                            da.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //It can be required to attach a valueeventListener here. So be prepared.
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
