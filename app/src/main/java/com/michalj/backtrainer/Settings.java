package com.michalj.backtrainer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String array[] = { "60", "90","2" };
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, R.layout.spinner_text, array);
        sp_adapter.setDropDownViewResource
                (R.layout.spinner_selector);
        final Spinner sp = findViewById(R.id.restTimeSpinner);
        sp.setAdapter(sp_adapter);


        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmation();
            }
        });

        Button restTime = findViewById(R.id.submit);
        restTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences sharedpref = getSharedPreferences("RestTime", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedpref.edit();
                edit.putInt("RestTime", Integer.parseInt(sp.getSelectedItem().toString()));
                edit.apply();
                Toast.makeText(getApplicationContext(), String.valueOf(sharedpref.getInt("RestTime",0)) , Toast.LENGTH_LONG).show();
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

/*
    Confirmation for reseting progress saved in db
    After positive answer function set default starting values for every exercise
*/

    protected void confirmation() {
        final ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertdialog.setTitle("warning");
        alertdialog.setMessage("Do you want to reset progress?");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    public void run() {
                        db.firstSetDao().nukeTable();
                        Set set = new Set(1,3,30,10,20,7.5, 10,4,6);
                        db.firstSetDao().insertWorkout(set);
                    }
                }).start();
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
