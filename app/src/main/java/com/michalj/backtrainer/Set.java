package com.michalj.backtrainer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Set {
    @PrimaryKey int id;
    int pullups, plank, dips, bodyRow;
    double frontSquat, press, bicepCurl, militaryPress;


    public Set(int id , int pullups, int plank, double frontSquat, double press, double bicepCurl,
               double militaryPress, int dips, int bodyRow){
        this.id = id;
        this.pullups = pullups;
        this.plank = plank;
        this.frontSquat = frontSquat;
        this.press = press;
        this.bicepCurl = bicepCurl;
        this.militaryPress = militaryPress;
        this.dips = dips;
        this.bodyRow = bodyRow;
    }

    public static Set populateData() {
        return new Set(1,3,30,10,20,7.5, 10,4,6);
    }
}


