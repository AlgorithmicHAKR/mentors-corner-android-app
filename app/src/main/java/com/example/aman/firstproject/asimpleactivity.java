package com.example.aman.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class asimpleactivity extends AppCompatActivity {
     private TextView answer;
     private EditText ans;
     private String mentor;
     private String mentorpassword;
     private Button post;
     private DatabaseReference userdatabase;
     private DatabaseReference mentordatabase;
     private String userpassword,key;
     private String mentorkey1;private String answer1;
     private String anotheruserpass,doubtskey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asimpleactivity);
        userpassword=getIntent().getStringExtra("password");
        mentorpassword=getIntent().getStringExtra("mentorpassword");
        key=getIntent().getStringExtra("key");
        doubtskey=getIntent().getStringExtra("doubtkey");
        mentor=getIntent().getStringExtra("mentor");
        if(mentor.equals("yes"))
            userpassword=getIntent().getStringExtra("userpassword");
        //  userdatabase= FirebaseDatabase.getInstance().getReference().child("users").child(userpassword).child("mentors").child(key).child("doubts").child("answer");
       // mentordatabase=FirebaseDatabase.getInstance().getReference().child("mentors").child(mentorpassword).child("doubts").child(doubtskey).child("answer");

        answer=(TextView)findViewById(R.id.answer);
        ans=(EditText) findViewById(R.id.ans);

        post=(Button) findViewById(R.id.anspost);
        FirebaseDatabase.getInstance().getReference().child("All_Mentors").child(mentorpassword).child("key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mentorkey1=dataSnapshot.getValue(String.class);
                Toast.makeText(asimpleactivity.this,"yo "+mentorkey1+" "+key+" "+doubtskey+" "+mentorpassword+" "+userpassword,Toast.LENGTH_LONG).show();

                FirebaseDatabase.getInstance().getReference().child("users").
                        child(userpassword).child("mentors").
                        child(mentorkey1).child("doubts")
                        .child("answer").child(doubtskey).
                        child("answer").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        answer1=dataSnapshot.getValue(String.class);
                        answer.setText(answer1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(mentor.equals("yes")){
            ans.setVisibility(View.VISIBLE);
            post.setVisibility(View.VISIBLE);
        }
     //    mentorkey1=FirebaseDatabase.getInstance().getReference().child("All_Mentors").child(mentorpassword).child("key").getKey();
         //answer1="";
       // Toast.makeText(asimpleactivity.this,"yo "+mentorkey1+" "+key+" "+doubtskey+" "+mentorpassword+" "+userpassword,Toast.LENGTH_LONG).show();
         if(mentorkey1!=null){
           }
          if(answer1!=null){

          }
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    String mentorkey=FirebaseDatabase.getInstance().getReference().child("All_Mentors").child(mentorpassword).child("key").getKey();
                userdatabase=FirebaseDatabase.getInstance().getReference().child("users").child(userpassword).child("mentors").child(mentorkey1).child("doubts")
                        .child("answer").child(doubtskey).child("answer");
                String key=userdatabase.push().getKey();
                userdatabase.setValue(ans.getText().toString());
              //  mentordatabase.setValue(ans.getText().toString());
                userdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        answer.setText(dataSnapshot.getValue(String.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
