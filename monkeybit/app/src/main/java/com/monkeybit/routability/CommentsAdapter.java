package com.monkeybit.routability;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

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
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int posicion) {

        Comments comment = this.comments.get(posicion);
        commentsViewHolder.author.setText(comment.getAuthor());
        commentsViewHolder.comment.setText(comment.getDescription());
        Picasso.get().load(comment.getImage()).into(commentsViewHolder.imagePlace);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagePlace;
        private TextView author;
        private TextView comment;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePlace = itemView.findViewById(R.id.imagePlace);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
