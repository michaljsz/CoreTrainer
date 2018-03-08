package com.michalj.backtrainer;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface SetDao {

    @Insert
    void insertWorkout(Set set);

    @Query("SELECT id FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    int getId();
    @Query("SELECT pullups FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    int getPullups();
    @Query("SELECT plank FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    int getPlank();
    @Query("SELECT frontSquat FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    double getFrontSquat();
    @Query("SELECT press FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    double getPress();
    @Query("SELECT bicepCurl FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    double getBicepCurl();
    @Query("SELECT militaryPress FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    double getMilitaryPress();
    @Query("SELECT dips FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    int getDips();
    @Query("SELECT bodyRow FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    int getBodyRowk();

    @Query("SELECT * FROM `Set` WHERE id=(SELECT max(id) FROM `Set`)")
    Set getSet();


    @Query("DELETE FROM `Set`")
    void nukeTable();


}
