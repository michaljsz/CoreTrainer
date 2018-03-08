package com.michalj.backtrainer;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        TextView counter = findViewById(R.id.workoutcount);
        final ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        counter.setText("You completed "+String.valueOf(db.firstSetDao().getId())+" workouts \n\n" +
                "Select exercises which were easy ");

        final Switch pullUp = findViewById(R.id.pullSwitch);
        final Switch plank = findViewById(R.id.plankSwitch);
        final Switch first = findViewById(R.id.firstSwitch);
        final Switch second = findViewById(R.id.secondSwitch);
        final Switch third = findViewById(R.id.thirdSwitch);
        if ( db.firstSetDao().getId() % 2 != 0) {
            first.setText("front squat");
            second.setText("press");
            third.setText("bicep curl");
        } else {
            first.setText("military press");
            second.setText("dips");
            third.setText("body row");
        }

        final Set set = db.firstSetDao().getSet();

        Button done = findViewById(R.id.congratsDone);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ( db.firstSetDao().getId() % 2 != 0) {
                    if ( pullUp.isChecked() ) {
                        set.pullups+=1;
                    }
                    if ( plank.isChecked() ) {
                        set.plank+=5;
                    }
                    if ( first.isChecked() ) {
                        set.frontSquat+=1.25;
                    }
                    if ( second.isChecked() ) {
                        set.press+=1.25;
                    }
                    if ( third.isChecked() ) {
                        set.bicepCurl+=0.75;
                    }
                } else {
                    if ( pullUp.isChecked() ) {
                        set.pullups+=1;
                    }
                    if ( plank.isChecked() ) {
                        set.plank+=5;
                    }
                    if ( first.isChecked() ) {
                        set.militaryPress+=1.25;
                    }
                    if ( second.isChecked() ) {
                        set.dips+=1;
                    }
                    if ( third.isChecked() ) {
                        set.bodyRow+=1;
                    }
                }
                set.id+=1;
                db.firstSetDao().insertWorkout(set);
                finish();
            }
        });
    }
    public void onBackPressed() {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertdialog.setTitle("warning");
        alertdialog.setMessage("Do you want to exit workout? \n" +
                " Progress won't be saved");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
