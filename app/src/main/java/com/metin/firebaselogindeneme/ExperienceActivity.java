package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ExperienceActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnbeginner,btnintermediate,btnadvanced;
    public static String experience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        toolbar = findViewById(R.id.toolbar);
        btnbeginner = findViewById(R.id.btnbeginner);
        btnintermediate = findViewById(R.id.btnintermediate);
        btnadvanced = findViewById(R.id.btnadvanced);


        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);

        btnbeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experience = "Beginner";
                startActivity(new Intent(ExperienceActivity.this,PurposeActivity.class));
            }
        });
        btnintermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experience = "Intermediate";
                startActivity(new Intent(ExperienceActivity.this,PurposeActivity.class));
            }
        });
        btnadvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experience = "Advanced";
                startActivity(new Intent(ExperienceActivity.this,PurposeActivity.class));
            }
        });
    }
}