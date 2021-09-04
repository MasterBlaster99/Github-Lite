package com.example.githublite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<Comment> list = new ArrayList<>();

    public CommentAdapter(ArrayList<Comment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  CommentAdapter.ViewHolder holder, int position) {

        holder.uname.setText(list.get(position).getUser());
        holder.comm.setText(list.get(position).getCommentmsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView uname, comm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uname = itemView.findViewById(R.id.userName);
            comm = itemView.findViewById(R.id.comment);
        }
    }
}
