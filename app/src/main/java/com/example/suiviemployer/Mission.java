package com.example.suiviemployer;

import android.widget.EditText;

import java.util.Date;

public class Mission {
    String discription;
    String missonName;
    String date_depart;
    String date_fin;

    public Mission(String missonName, String discription, String date_fin, String date_depart) {
        this.missonName = missonName;
        this.discription = discription;
        this.date_depart = date_depart;
        this.date_fin = date_fin;
    }
    public Mission(){}

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getMissonName() {
        return missonName;
    }

    public void setMissonName(String missonName) {
        this.missonName = missonName;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
}