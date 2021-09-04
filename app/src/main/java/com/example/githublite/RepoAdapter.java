package com.example.githublite;

import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    ArrayList<Repo> list = new ArrayList<>();
    private OnItemClickListener mListener;
    public RepoAdapter(ArrayList<Repo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.ViewHolder holder, int position) {

        holder.nameTV.setText(list.get(position).getName());
        if(list.get(position).getDescription()!="null")
            holder.desTV.setText(list.get(position).getDescription());
        else
            holder.desTV.setText("No description");
        holder.numStarsTV.setText(list.get(position).getNumStars());
        if(list.get(position).getLanguage()!="null")
            holder.languageTV.setText(list.get(position).getLanguage());
        else
            holder.languageTV.setText("None");
        Log.d("theTEXT", "size"+list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView desTV;
        TextView numStarsTV;
        TextView languageTV;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nameTV =itemView.findViewById(R.id.repoName);
            desTV = itemView.findViewById(R.id.descriptionTV);
            numStarsTV=itemView.findViewById(R.id.numStars);
            languageTV=itemView.findViewById(R.id.language);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
}
