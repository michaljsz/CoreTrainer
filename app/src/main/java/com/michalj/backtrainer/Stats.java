package com.michalj.backtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Stats extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        TextView pull = findViewById(R.id.pullstat);
        TextView plank = findViewById(R.id.plankstat);
        TextView squat = findViewById(R.id.squatstat);
        TextView press = findViewById(R.id.pressstat);
        TextView bic = findViewById(R.id.bicepstat);
        ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        pull.setText(String.valueOf(db.firstSetDao().getPullups())+" reps");
        plank.setText(String.valueOf(db.firstSetDao().getPlank())+" sec");
        squat.setText(String.valueOf(db.firstSetDao().getFrontSquat())+" kg");
        press.setText(String.valueOf(db.firstSetDao().getPress())+" kg");
        bic.setText(String.valueOf(db.firstSetDao().getBicepCurl())+" kg");

        Button secondset = findViewById(R.id.secondset);
        secondset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Stats.this, Stats2.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            }
        });
    }
}
