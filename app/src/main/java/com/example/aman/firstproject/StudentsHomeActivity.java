package com.example.aman.firstproject;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentsHomeActivity extends AppCompatActivity {
    private EditText search;
    private Button mymentors;
    private DatabaseReference db;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_home);
        search=(EditText)findViewById(R.id.search);
        mymentors=(Button) findViewById(R.id.mentors);
        final String pno=getIntent().getStringExtra("password");
        db= FirebaseDatabase.getInstance().getReference().child("users").child(pno);
        mymentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentsHomeActivity.this, Mymentors.class);
                intent.putExtra("password",pno);
                startActivity(intent);
            }
        });
    }
}
