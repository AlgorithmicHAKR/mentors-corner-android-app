package com.example.aman.firstproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private Button sb,mb;
    //FirebaseApp fb=new FirebaseApp();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb=(Button)findViewById(R.id.Students_section);
        mb=(Button)findViewById(R.id.MENTORS_SECTION);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StudentsLoginActivity.class);
                startActivity(intent);
            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MentorLoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
