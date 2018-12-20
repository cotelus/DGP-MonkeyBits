package com.monkeybit.routability;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> implements View.OnClickListener{

    private List<Comments> comments;

    public CommentsAdapter (List<Comments> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments,parent,false);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments,null,false);

        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsViewHolder commentsViewHolder, final int posicion) {

        Comments comment = this.comments.get(posicion);
        commentsViewHolder.author.setText(comment.getAuthor());
        commentsViewHolder.comment.setText(comment.getDescription());
        commentsViewHolder.date.setText(comment.getDate());
        commentsViewHolder.time.setText(comment.getTime());
        commentsViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setOnClickListeners(){
    }

    @Override
    public void onClick(View view) {
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder{
        private TextView author;
        private TextView comment;
        private TextView date;
        private TextView time;
        private ImageButton imageButton;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            imageButton = itemView.findViewById(R.id.banButton);
        }
    }
}
