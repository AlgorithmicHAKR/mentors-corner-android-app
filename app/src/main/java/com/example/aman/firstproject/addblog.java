package com.example.aman.firstproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class addblog extends AppCompatActivity {
    private EditText topic , description;
    private ImageButton image;
    private Button post;
    private DatabaseReference db;
    private String key="nmy";
    private int GALLERY_CODE=1;
    private StorageReference sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addblog);
        String pno=getIntent().getStringExtra("password");
        db= FirebaseDatabase.getInstance().getReference().child("mentors").child(pno).child("blogs");
        topic=(EditText)findViewById(R.id.blog_topic);
        description=(EditText)findViewById(R.id.blog_description);
        key=getIntent().getStringExtra("key");
        image=(ImageButton) findViewById(R.id.blogimage);
        post=findViewById(R.id.blogpost);
        sb= FirebaseStorage.getInstance().getReference().child("blogs");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);

            }
        });

       /* if(mImageUri!=null)
             Toast.makeText(addblog.this,"idhar to pahuch gaye",Toast.LENGTH_LONG).show();*/

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImageUri!=null){
                  //      Toast.makeText(addblog.this,"idhar to pahuch gaye",Toast.LENGTH_LONG).show();
                    sb.child(mImageUri.toString()).putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  //          Toast.makeText(addblog.this,"idhar to pahuch gaye",Toast.LENGTH_LONG).show();
                            sb.child(mImageUri.toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=uri;
                                    if(downloadUrl!=null){
                                        HashMap<String, Object> h=new HashMap<>();
                                        h.put("topic",topic.getText().toString());
                                        h.put("description",description.getText().toString());
                                        h.put("image",downloadUrl.toString());
                                        db.child(key).updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(addblog.this,"Post Uploaded Successfully",Toast.LENGTH_LONG).show();

                         /* Intent intent=new Intent(addblog.this,MentorsWorld.class);
                              intent.putExtra("key",key);
                              startActivity(intent);*/

                                                }
                                                else{
                                                    Toast.makeText(addblog.this,"Something went wrong",Toast.LENGTH_LONG).show();

                                                }
                                            }
                                        });

                                    }
                             //           Toast.makeText(addblog.this,"MUTU "+downloadUrl.toString(),Toast.LENGTH_LONG).show();

                             //       Toast.makeText(addblog.this,"Successful ",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }
            }
        });


    }
    private Uri mImageUri=null;private Uri downloadUrl;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CODE&&resultCode==RESULT_OK){
            mImageUri=data.getData();
            image.setImageURI(mImageUri);
        }
    }
}
