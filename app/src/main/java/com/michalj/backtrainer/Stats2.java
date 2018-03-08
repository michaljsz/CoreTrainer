package com.michalj.backtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Stats2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats2);
        TextView pull = findViewById(R.id.pullstat);
        TextView plank = findViewById(R.id.plankstat);
        TextView militaryPress = findViewById(R.id.milstat);
        TextView dips = findViewById(R.id.dipsstat);
        TextView bodyRow = findViewById(R.id.rowstat);

        ExerciesDatabase db = ExerciesDatabase.getInstance(getApplicationContext());
        pull.setText(String.valueOf(db.firstSetDao().getPullups()));
        plank.setText(String.valueOf(db.firstSetDao().getPlank())+" sec");
        militaryPress.setText(String.valueOf(db.firstSetDao().getMilitaryPress())+" kg");
        dips.setText(String.valueOf(db.firstSetDao().getDips())+" reps");
        bodyRow.setText(String.valueOf(db.firstSetDao().getBodyRowk())+" reps");

        Button firstset = findViewById(R.id.firstset);
        firstset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Stats2.this, Stats.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                finish();
            }
        });
    }
}
