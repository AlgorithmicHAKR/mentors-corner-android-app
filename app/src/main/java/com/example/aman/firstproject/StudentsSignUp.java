package com.example.aman.firstproject;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class StudentsSignUp extends AppCompatActivity {
    private EditText name ;private TextInputLayout password, email;

    private Button createacc;private String e;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_sign_up);
        db= FirebaseDatabase.getInstance().getReference("users");
        name=(EditText)findViewById(R.id.ssignupname);
        email=(TextInputLayout)findViewById(R.id.ssignupemail);
        password=(TextInputLayout)findViewById(R.id.ssignuppassword);
        createacc=(Button)findViewById(R.id.ssignupcreate);

        email.setErrorTextColor(ColorStateList.valueOf(Color.RED));


   { createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=email.getEditText().getText().toString().trim();
                HashMap<String,Object> h=new HashMap<>();
                h.put("name",name.getText().toString());
                h.put("email",email.getEditText().getText().toString());
                h.put("password",password.getEditText().getText().toString());
              /*  Toast.makeText(StudentsSignUp.this,"your password-"+password.getText().toString(),Toast.LENGTH_LONG).show();
                db.child(password.getText().toString()).child("name").setValue(name.getText().toString());
                db.child(password.getText().toString()).child("email").setValue(email.getText().toString());
                db.child(password.getText().toString()).child("password").setValue(password.getText().toString()).addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(StudentsSignUp.this,"Successful",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(StudentsSignUp.this,"Not Successful",Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                );*/
                if(e.isEmpty()){email.setError("Email can't be left empty");}
                else if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    email.setError("Please enter a valid email address");
                }
                else{
                Toast.makeText(StudentsSignUp.this,"Account created",Toast.LENGTH_LONG).show();
                /*startActivity(new Intent(StudentsSignUp.this, StudentsLoginActivity.class));
                 finish();*/
                db.child(password.getEditText().getText().toString()).updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(StudentsSignUp.this,"Account created",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(StudentsSignUp.this, StudentsLoginActivity.class));

                    }
                });}

            }
        });}
    }
}
