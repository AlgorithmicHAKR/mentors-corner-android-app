package com.example.aman.firstproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoubtsAdapter extends RecyclerView.Adapter<DoubtsAdapter.Naya1ViewHolder> {
    private Context context;private String password,key;
   private List<QuestionFromMentor> qflist;private int ja;
    private List<Question> qlist;private String mentor;private String mentorpassword;
    public DoubtsAdapter(Context context,List<Question> qlist,String mentor,String password,String key,String mentorpassword) {
        this.context = context;
        this.qlist=qlist;
        this.mentor=mentor;
        this.key=key;this.password=password;this.mentorpassword=mentorpassword;
    }
    public DoubtsAdapter(Context context,List<QuestionFromMentor> qflist,String mentor,String password,String key,String mentorpassword,int jaga) {
        this.context = context;
        this.qflist=qflist;this.ja =jaga;
        this.mentor=mentor;
        this.mentorpassword=mentorpassword;
        this.key=key;this.password=password;
    }

    @NonNull
    @Override
    public DoubtsAdapter.Naya1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.onedoubt,parent,false);
        return new Naya1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Naya1ViewHolder holder, int position) {
      //  Toast.makeText(context,"hello "+password,Toast.LENGTH_LONG).show();
        if(mentor.equals("no")){
       final Question question=qlist.get(position);
        holder.q.setText(question.getQuestion());
        holder.q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,asimpleactivity.class);
                intent.putExtra("mentor",mentor);
                intent.putExtra("key",key);
                intent.putExtra("password",password);
                intent.putExtra("doubtkey",question.getDoubtkey());
                intent.putExtra("mentorpassword",mentorpassword);
                context.startActivity(intent);
            }
        });}
        else{
            final QuestionFromMentor question=qflist.get(position);
            //Toast.makeText(context,"HUIIH "+question.getQuestion(),Toast.LENGTH_LONG).show();;
            holder.q.setText(question.getQuestion());
            holder.q.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,asimpleactivity.class);
                    intent.putExtra("mentor",mentor);
                    intent.putExtra("mentorpassword",password);
                    intent.putExtra("userpassword",question.getUserpassword());
                    Toast.makeText(context,question.getUserpassword(),Toast.LENGTH_LONG).show();
                    intent.putExtra("doubtkey",question.getKey());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
       if(mentor.equals("no")) return qlist.size();
       return qflist.size();
    }
    public static class Naya1ViewHolder extends RecyclerView.ViewHolder{
       public TextView q;
       public Naya1ViewHolder(@NonNull View itemView) {
            super(itemView);
            q=(TextView)itemView.findViewById(R.id.onequestion);
        }
    }
}
