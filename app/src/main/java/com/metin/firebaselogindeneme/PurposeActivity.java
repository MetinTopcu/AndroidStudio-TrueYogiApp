package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PurposeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnbuildstrength,btngetinshape,btnrelaxyourmind,btnimproveflexibility,btnimproveposture;
    public static String purpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose);

        toolbar = findViewById(R.id.toolbar);
        btnbuildstrength = findViewById(R.id.btnbuildstrength);
        btngetinshape = findViewById(R.id.btngetinshape);
        btnrelaxyourmind = findViewById(R.id.btnrelaxyourmind);
        btnimproveflexibility = findViewById(R.id.btnimproveflexibility);
        btnimproveposture = findViewById(R.id.btnimproveposture);

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);

        btnbuildstrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "Build strength";
                startActivity(new Intent(PurposeActivity.this,LessonTimeActivity.class));
            }
        });
        btngetinshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "Get in shape";
                startActivity(new Intent(PurposeActivity.this,LessonTimeActivity.class));
            }
        });
        btnrelaxyourmind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "Relax your mind";
                startActivity(new Intent(PurposeActivity.this,LessonTimeActivity.class));
            }
        });
        btnimproveflexibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "Improve flexibility";
                startActivity(new Intent(PurposeActivity.this,LessonTimeActivity.class));
            }
        });
        btnimproveposture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "Improve posture";
                startActivity(new Intent(PurposeActivity.this,LessonTimeActivity.class));
            }
        });
    }
}