package com.example.aman.firstproject;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentsLoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email,password;
    private TextInputLayout emaili,passwordi;
    private DatabaseReference database;
    private Button signup,show;
    private String e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_login);
        emaili=(TextInputLayout) findViewById(R.id.student_email);
        passwordi=(TextInputLayout) findViewById(R.id.student_password);

        login=(Button) findViewById(R.id.student_login);
        signup=(Button) findViewById(R.id.student_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentsLoginActivity.this,StudentsSignUp.class);
                startActivity(intent);
            }
        });
        emaili.setErrorTextColor(ColorStateList.valueOf(Color.RED));
       {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance().getReference().child("users");

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        e=emaili.getEditText().getText().toString().trim();
                        if(e.isEmpty()){
                            emaili.setError("Email is Required, can't be left empty..");
                        }
                        else if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                            emaili.setError("Please enter a valid email address");
                        }
                        else{
                        if(dataSnapshot.hasChild(passwordi.getEditText().getText().toString())){
                            if( dataSnapshot.child(passwordi.getEditText().getText().toString()).child("email").getValue().toString().equals(emaili.getEditText().getText().toString()))
                            {
                                Toast.makeText(StudentsLoginActivity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();;
                                Intent intent=new Intent(StudentsLoginActivity.this, Mymentors.class);
                                intent.putExtra("password",passwordi.getEditText().getText().toString());
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(StudentsLoginActivity.this,"Login failed",Toast.LENGTH_LONG).show();

                            }
                        }}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });}
    }
}
