package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton sunpositionbtn,mooncyclebtn,momentbtn,moodtrackingbtn,mantrabtn;
    private  MoodActivity moodActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        sunpositionbtn = findViewById(R.id.sunpositionbtn);
        mooncyclebtn = findViewById(R.id.mooncyclebtn);
        momentbtn = findViewById(R.id.momentbtn);
        moodtrackingbtn = findViewById(R.id.moodtrackingbtn);
        mantrabtn = findViewById(R.id.mantrabtn);

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);

        sunpositionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SunPositionActivity.class));
            }
        });

        mooncyclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WeatherMoonCycleActivity.class));
            }
        });

        momentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MomentActivity.class));
            }
        });

        moodtrackingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodActivity.tummood = 0;
                startActivity(new Intent(MainActivity.this,MoodActivity.class));
            }
        });

        mantrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MantraActivity.class));
            }
        });
    }
}