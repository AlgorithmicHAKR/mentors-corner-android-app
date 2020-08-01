package com.example.aman.firstproject;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MentorLoginActivity extends AppCompatActivity {
    private Button login;
    private TextInputLayout email,password;
    private DatabaseReference database;
    private Button signup;private String e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_login);
        email=(TextInputLayout) findViewById(R.id.mentors_email);
        password=(TextInputLayout) findViewById(R.id.mentors_password);
        login=(Button) findViewById(R.id.mentors_login);
        signup=(Button) findViewById(R.id.mentors_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentorLoginActivity.this,MentorsSignUp.class);
                startActivity(intent);
                finish();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance().getReference().child("mentors");
                e=email.getEditText().getText().toString().trim();
                if(e.isEmpty()){
                    email.setError("Email can't be left empty");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    email.setError("Please enter a valid email address");
                }
                else{
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(password.getEditText().getText().toString())){
                            if( dataSnapshot.child(password.getEditText().getText().toString()).child("email").getValue().toString().equals(email.getEditText().getText().toString()))
                            {
                                Intent intent=new Intent(MentorLoginActivity.this,MentorsWorld.class);
                                intent.putExtra("password",password.getEditText().getText().toString());
                                intent.putExtra("admin","yes");
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                }
            }
        });
    }
}
