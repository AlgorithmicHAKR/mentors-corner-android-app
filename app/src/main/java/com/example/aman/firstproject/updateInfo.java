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

public class updateInfo extends AppCompatActivity {
    private EditText name,category,yoe,cwi;
    private Button save; private ImageButton profileimage;
    private DatabaseReference db;
    private DatabaseReference db1;    private int GALLERY_CODE=1;
    private StorageReference sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        sb= FirebaseStorage.getInstance().getReference().child("profile");
        name=(EditText) findViewById(R.id.name);
        profileimage=(ImageButton) findViewById(R.id.profileimage);
        category=(EditText) findViewById(R.id.category);
        yoe=(EditText) findViewById(R.id.yoe);
        cwi=(EditText) findViewById(R.id.cwi);
        save=(Button) findViewById(R.id.finallyUpdate);
        final String password=getIntent().getStringExtra("password");
        db= FirebaseDatabase.getInstance().getReference().child("mentors").child(password);
        db1= FirebaseDatabase.getInstance().getReference().child("All_Mentors").child(password);
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
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
                                        HashMap<String,Object> h=new HashMap<>();
                                        h.put("name",name.getText().toString());
                                        h.put("category",category.getText().toString());
                                        h.put("yoe",yoe.getText().toString());
                                        h.put("cwi",cwi.getText().toString());
                                        h.put("image",downloadUrl.toString());
                                        db1.updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(updateInfo.this,"Information Saved",Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(updateInfo.this,MentorsWorld.class);
                                                    intent.putExtra("password",password);
                                                    intent.putExtra("key",getIntent().getStringExtra("key"));
                                                    startActivity(intent);

                                                }
                                                else{

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
            profileimage.setImageURI(mImageUri);
        }
    }
}
