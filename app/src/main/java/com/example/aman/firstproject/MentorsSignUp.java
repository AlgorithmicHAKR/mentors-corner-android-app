package com.example.aman.firstproject;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MentorsSignUp extends AppCompatActivity {
    private EditText name ;private TextInputLayout password, email;
    private Button createacc;
    private DatabaseReference db;
    private DatabaseReference db1;private String e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_mentors_sign_up);
        db= FirebaseDatabase.getInstance().getReference().child("mentors");
        db1= FirebaseDatabase.getInstance().getReference().child("All_Mentors");
        name=findViewById(R.id.ssignupname);
        email=(TextInputLayout)findViewById(R.id.ssignupemail);
        password=(TextInputLayout)findViewById(R.id.ssignuppassword);
        createacc=findViewById(R.id.ssignupcreate);
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=email.getEditText().getText().toString().trim();
                HashMap<String,Object> h=new HashMap<>();
                h.put("name",name.getText().toString());
                h.put("email",email.getEditText().getText().toString());
                h.put("password",password.getEditText().getText().toString());
                if(e.isEmpty()){email.setError("Email can't be left empty");}
                else if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    email.setError("Please enter a valid email address");
                }
                else{
                db.child(password.getEditText().getText().toString()).updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MentorsSignUp.this,"Account created",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MentorsSignUp.this, MentorLoginActivity.class));
                        finish();

                    }
                });

                db1.child(password.getEditText().getText().toString()).updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MentorsSignUp.this,"Account created",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MentorsSignUp.this, MentorLoginActivity.class));
                        finish();

                    }
                });}

            }
        });

    }
}
