package com.example.githublite;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    ArrayList<Issue> arrayList = new ArrayList<>();

    public IssueAdapter(ArrayList<Issue> arrayList) {
        this.arrayList = arrayList;
    }
    private OnItemClickListener mListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  IssueAdapter.ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.inum.setText("#"+arrayList.get(position).getIssueNumber()+"  ");
        holder.comms.setText(arrayList.get(position).getComments());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , inum , comms;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.issueName);
            inum = itemView.findViewById(R.id.issueNumber);
            comms = itemView.findViewById(R.id.comments);
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
