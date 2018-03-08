package com.michalj.backtrainer;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button stats = findViewById(R.id.stats);
        stats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Home.this,
                        Stats.class);
                startActivity(myIntent);
            }
        });

        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Home.this,
                        Settings.class);
                startActivity(myIntent);
            }
        });

        Button workout = findViewById(R.id.workout);
        workout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Home.this,
                        Workout.class);
                startActivity(myIntent);
            }
        });
    }
}
