package com.example.suiviemployer;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class HolderMyadapter extends RecyclerView.Adapter<HolderMyadapter.MyViewHolder> {

    Context context;
    ArrayList<Mission> list;

    public HolderMyadapter(Context context, ArrayList<Mission> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mission_design,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mission mission = list.get(position);
        holder.misssionName.setText(mission.getMissonName());
        holder.missionDescription.setText(mission.getDiscription());
        holder.dateDebut.setText(mission.getDate_depart());
        holder.dateFin.setText(mission.getDate_fin());




        holder.add_fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*final DialogPlus dialogplus = DialogPlus.newDialog(context).
                            setGravity(Gravity.CENTER)
                            .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogcontent))
                            .setExpanded(false)
                            .create();
                            dialogplus.show();*/
                ExampleDialog exampleDialog = new ExampleDialog();


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView misssionName,missionDescription,dateDebut,dateFin;
        ImageView deleteMission,add_fabb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            misssionName = itemView.findViewById(R.id.misssionName);
            missionDescription = itemView.findViewById(R.id.missionDescription);
            dateDebut = itemView.findViewById(R.id.dateDebut);
            dateFin = itemView.findViewById(R.id.dateFin);
            deleteMission = itemView.findViewById(R.id.deleteMission);
            add_fabb = itemView.findViewById(R.id.add_fabb);

        }
    }
}
