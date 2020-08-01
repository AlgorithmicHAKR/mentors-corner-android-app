package com.example.aman.firstproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class mentorworldadapter extends RecyclerView.Adapter<mentorworldadapter.MyViewHolder> {
    private Context context;
    public List<BlogDetails> blogList;
    public mentorworldadapter(Context context, List<BlogDetails> blogList) {
        this.context = context;
        this.blogList=blogList;
    }

    @NonNull
    @Override   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.a_singleitem,viewGroup,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        BlogDetails blog=blogList.get(i);
        //myViewHolder.topic.setText("HIIIIIIII");
        {

        myViewHolder.topic.setText(blog.getTopic());
        Picasso.get().load(blog.getImage()).into(myViewHolder.image);
        Toast.makeText(context,"idhar "+blog.getTopic(),Toast.LENGTH_SHORT).show();


        myViewHolder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }
    }
    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name,topic;public View mview;
        private ImageView image;
        public MyViewHolder(@NonNull View itemView) {
          super(itemView);
          topic=(TextView)itemView.findViewById(R.id.mentortopic);
          image=(ImageView)itemView.findViewById(R.id.asingleitemimage);
          mview=itemView;
        }
    }


}
