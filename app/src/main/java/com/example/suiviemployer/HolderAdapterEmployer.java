package com.example.suiviemployer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HolderAdapterEmployer extends RecyclerView.Adapter<HolderAdapterEmployer.MyViewHolder> {

    Context context;
    ArrayList<Employer> list;

    public HolderAdapterEmployer(Context context, ArrayList<Employer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Employer employer = list.get(position);
        holder.fullname.setText(employer.getFullname());
        holder.email.setText(employer.getEmail());
        holder.phone.setText(employer.getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fullname,phone,email,password;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        fullname = itemView.findViewById(R.id.tvFullName);
        phone = itemView.findViewById(R.id.tvphone);
        email = itemView.findViewById(R.id.tvEmail);

    }
}
}
