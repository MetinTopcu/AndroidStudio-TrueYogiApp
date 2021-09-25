package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LessonTimeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnfirstminutes,btnsecondminutes,btnthirdminutes;
    public static String lessontime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_time);

        toolbar = findViewById(R.id.toolbar);
        btnfirstminutes = findViewById(R.id.btnfirstminutes);
        btnsecondminutes = findViewById(R.id.btnsecondminutes);
        btnthirdminutes = findViewById(R.id.btnthirdminutes);

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);

        btnfirstminutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessontime = "15 minutes";
                startActivity(new Intent(LessonTimeActivity.this,HowManyDayActivity.class));
            }
        });
        btnsecondminutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessontime = "15-30 minutes";
                startActivity(new Intent(LessonTimeActivity.this,HowManyDayActivity.class));
            }
        });
        btnthirdminutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessontime = "More than 30";
                startActivity(new Intent(LessonTimeActivity.this,HowManyDayActivity.class));
            }
        });
    }
}