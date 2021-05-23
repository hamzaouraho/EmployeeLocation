package com.example.suiviemployer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapterMission extends ArrayAdapter {
    private Activity mContent;
    List<Mission> missionListMission;
    public static final String EXTRA_HAMZA = "com.example.suiviemployer.EXTRA_HAMZA";

    public ListAdapterMission(Activity mContent, List<Mission> missionListMission){
        super(mContent,R.layout.mission_design,missionListMission);
        this.mContent = mContent;
        this.missionListMission = missionListMission;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContent.getLayoutInflater();
        View  listItemViewMission = inflater.inflate(R.layout.mission_design,null,true);

        TextView misssionName = listItemViewMission.findViewById(R.id.misssionName);
        TextView missionDescription = listItemViewMission.findViewById(R.id.missionDescription);
        TextView dateDebut = listItemViewMission.findViewById(R.id.dateDebut);
        TextView dateFin = listItemViewMission.findViewById(R.id.dateFin);


        Mission mission = missionListMission.get(position);

        misssionName.setText(mission.getMissonName());
        missionDescription.setText(mission.getDiscription());
        dateDebut.setText(mission.getDate_depart());
        dateFin.setText(mission.getDate_fin());

        return  listItemViewMission;

    }
}
