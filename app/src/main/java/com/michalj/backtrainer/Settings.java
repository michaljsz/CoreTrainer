package com.michalj.backtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmation();
            }
        });

        Button aboutme = findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Settings.this,
                        AboutMe.class);
                startActivity(myIntent);
            }
        });
    }
    protected void confirmation() {
        final ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertdialog.setTitle("warning");
        alertdialog.setMessage("Do you want to reset progress?");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.firstSetDao().nukeTable();
                Set set = new Set(1,3,30,10,20,7.5, 10,4,6);
                db.firstSetDao().insertWorkout(set);
                finish();
            }
        });

        alertdialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alertdialog.show();
        TextView msg = (TextView)dialog.findViewById(android.R.id.message);
        msg.setGravity(Gravity.CENTER);
        msg.setTextColor(Color.WHITE);
    }
}
