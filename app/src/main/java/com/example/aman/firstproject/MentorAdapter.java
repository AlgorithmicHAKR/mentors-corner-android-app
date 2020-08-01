package com.example.aman.firstproject;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class  MentorAdapter extends RecyclerView.Adapter<MentorAdapter.NayaViewHolder> {
    public static Context context;public String pno;public int val=0;
    private DatabaseReference   db;
    public List<MentorDetails> mdlist;public int admin=1;public String key;
    private   MentorDetails mentor;
    public MentorAdapter(Context context, List<MentorDetails> mdlist,int admin,String pno) {
        this.context = context;
        this.admin=admin;
        this.mdlist = mdlist;this.pno=pno;
    }
    public MentorAdapter(Context context, List<MentorDetails> mdlist,String pno,String key) {
        this.context = context;this.key=key;
        this.mdlist = mdlist;
        this.pno=pno;
    }
    public MentorAdapter(Context context, List<MentorDetails> mdlist,int val ) {
        this.context = context;
        this.mdlist = mdlist;
        this.val=val;
    }

    public MentorAdapter(Context context, List<MentorDetails> allmentorslist, int i, String pno, String key) {
          this.context=context;
          this.mdlist=allmentorslist;
          this.val=i;
          this.pno=pno;
          this.key=key;
    }

    @NonNull
    @Override
    public MentorAdapter.NayaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.asinglementor,viewGroup,false);
        return new NayaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MentorAdapter.NayaViewHolder myViewHolder, int i) {
        mentor=mdlist.get(i);
        myViewHolder.name.setText(mentor.getName());
        myViewHolder.category.setText(mentor.getCategory());
        myViewHolder.password.setText(mentor.getPassword());
        myViewHolder.keyfield.setText(mentor.getKey());
        Toast.makeText(context,"HI "+mentor.getKey(),Toast.LENGTH_LONG).show();
        Toast.makeText(context,"hi "+mentor.getKey(),Toast.LENGTH_LONG).show();
        myViewHolder.rating.setText(mentor.getRating());
        Picasso.get().load(mentor.getImage()).into(myViewHolder.image);

        if(val==4) {
            myViewHolder.subscribe.setVisibility(View.VISIBLE);

        }if(val!=4){
        myViewHolder.mview.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
               Intent intent=new Intent(context,MentorsWorld.class);
               //    intent.putExtra("password",pno);
               intent.putExtra("password",pno);
               intent.putExtra("admin","no");
               intent.putExtra("id",myViewHolder.password.getText().toString());
               intent.putExtra("key",myViewHolder.keyfield.getText().toString());
               Toast.makeText(context,"ha yaha "+myViewHolder.keyfield.getText().toString(),Toast.LENGTH_LONG).show();

               context.startActivity(intent);
                   }
                                              }
        );}
        myViewHolder.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db= FirebaseDatabase.getInstance().getReference().child("users").child(pno).child("mentors").child(key);
              h=new HashMap<>();
              h.put("name",myViewHolder.name.getText().toString());
              h.put("category",myViewHolder.category.getText().toString());
              h.put("password",myViewHolder.password.getText().toString());
              h.put("key",key);
              DatabaseReference dd= FirebaseDatabase.getInstance().getReference().child("All_Mentors").child(myViewHolder.password.getText().toString());
              dd.child("key").setValue(key);
              dd.child("image").addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                      String   x =dataSnapshot.getValue(String.class);
                      h.put("image",x);
                      db.updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                      Toast.makeText(context,"You have Now Subscribed to "+h.get("name"),Toast.LENGTH_LONG).show();
                            }
                        });
                      }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

           //     Toast.makeText(context,"AMAN "+x,Toast.LENGTH_LONG).show();
            //    h.put("currentlyworkingin",myViewHolder.currentlyworkingin.getText().toString());
             //   h.put("id",myViewHolder..getText().toString());
              //  h.put("rating",mentor.getRating());
           //     h.put("yoe",myViewHolder.y.getText().toString());

            }
        });
    }
    private  String x="";;private    HashMap<String ,Object> h;
    @Override
    public int getItemCount() {
        return mdlist.size();
    }
    public static class NayaViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name,currentlyworkingin,category,rating,yoe;
        private TextView password;private TextView keyfield;
        private Button subscribe;
        public View mview;
        public NayaViewHolder(@NonNull View itemView) {
            super(itemView);
            keyfield=(TextView)itemView.findViewById(R.id.keyfield);
            password=(TextView) itemView.findViewById(R.id.mdpassword);
            image=itemView.findViewById(R.id.mdimage);
            name=itemView.findViewById(R.id.mdname);
            category=itemView.findViewById(R.id.mdcategory);
            rating=itemView.findViewById(R.id.mdrating);
            subscribe=itemView.findViewById(R.id.subscribe);
            mview=itemView;
         //   currentlyworkingin=itemView.findViewById(R.id.cwi);

        }

    }


}
