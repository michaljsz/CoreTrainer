package com.michalj.backtrainer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Workout extends AppCompatActivity {

    private int buttonCount = 0;
    private boolean secondSet = false;
    private String[] exercises = {"pull up","plank","front squat","press","bicep curl","military press", "dips","body row"};
    private String[] units = {" reps", " seconds"," kg"," kg"," kg", " kg", " reps", " reps"};
    private String[] reps = {"","","5 x ","8 x ","10 x ","8 x ", "", ""};
    private int[] sets = {5,1,6,6,4,4,6,4};
    private int setCount = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        final ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        final double[] previous = {db.firstSetDao().getPullups(), db.firstSetDao().getPlank(),
                db.firstSetDao().getFrontSquat(), db.firstSetDao().getPress(), db.firstSetDao().getBicepCurl(),
                db.firstSetDao().getMilitaryPress(),db.firstSetDao().getDips(),db.firstSetDao().getBodyRowk()};

        final SharedPreferences sp = getSharedPreferences("RestTime", Context.MODE_PRIVATE);
        final int restTime = sp.getInt("RestTime",2);

        final TextView exercise = findViewById(R.id.exercise);
        exercise.setText(exercises[buttonCount]);

        final TextView formula = findViewById(R.id.reps);
        formula.setText(reps[buttonCount]+previous[buttonCount]+units[buttonCount]);

        final ProgressBar progres = findViewById(R.id.restTime);
        progres.setVisibility(View.GONE);

        final Button done = findViewById(R.id.done);
        done.setText("DONE");

        // timer used for counting remaining rest time between exercises
        final CountDownTimer countDownTimer = new CountDownTimer(restTime*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                done.setText(Long.toString(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
            }
        };
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                done.setText("DONE");
                progres.setVisibility(View.GONE);
                done.setEnabled(true);
                exercise.setText(exercises[buttonCount]);
                formula.setText(reps[buttonCount]+previous[buttonCount]+units[buttonCount]);
            }
        };


        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if ( setCount % sets[buttonCount] == 0 ) {
                    // determine if this is first or second workout type and which exercises to choose
                    if (db.firstSetDao().getId() % 2 == 0 && buttonCount == 1) {
                        buttonCount += 4;
                        secondSet = true;
                    } else {
                        buttonCount++;
                    }
                    setCount = 1;
                }
                if ( (buttonCount == 5 && secondSet == false ) || (buttonCount ==8 && secondSet == true )) {
                    // changes activity on completing the workout
                    Intent myIntent = new Intent(Workout.this, Congrats.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                    finish();
                } else {
                        // ensures rest between reps and exercises. Runnable handles setting next exercise/rep
                        handler.postDelayed(runnable, restTime * 1000);
                        done.setEnabled(false);
                        countDownTimer.start();
                        progres.setVisibility(View.VISIBLE);
                        exercise.setText("rest");
                }
                setCount++;
            }
        });
    }

/*
    Confirmation for exiting workout without saving data
     */
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
        TextView msg = dialog.findViewById(android.R.id.message);
        msg.setGravity(Gravity.CENTER);
        msg.setTextColor(Color.WHITE);

    }
}
