package com.example.suiviemployer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContent;
    List<Employer> employerList;

    public ListAdapter(Activity mContent, List<Employer> employerList){
        super(mContent,R.layout.list_item,employerList);
        this.mContent = mContent;
        this.employerList = employerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContent.getLayoutInflater();
        View  listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView tvFullName = listItemView.findViewById(R.id.tvFullName);
        TextView tvEmail = listItemView.findViewById(R.id.tvEmail);
        TextView tvPhone = listItemView.findViewById(R.id.tvphone);

        Employer employer = employerList.get(position);

        tvFullName.setText(employer.getFullname());
        tvEmail.setText(employer.getEmail());
        tvPhone.setText(employer.getPhone());

        return  listItemView;

    }
}
