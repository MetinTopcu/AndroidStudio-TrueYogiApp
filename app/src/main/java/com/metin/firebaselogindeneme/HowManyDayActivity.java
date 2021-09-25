package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HowManyDayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btn12,btn34,btn5ormore;
    public static String howmanyday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_day);

        toolbar = findViewById(R.id.toolbar);
        btn12 = findViewById(R.id.btn12);
        btn34 = findViewById(R.id.btn34);
        btn5ormore = findViewById(R.id.btn5ormore);

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);


        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howmanyday = "1-2";
                startActivity(new Intent(HowManyDayActivity.this,RegisterActivity.class));
            }
        });
        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howmanyday = "3-4";
                startActivity(new Intent(HowManyDayActivity.this,RegisterActivity.class));
            }
        });
        btn5ormore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howmanyday = "5 or more";
                startActivity(new Intent(HowManyDayActivity.this,RegisterActivity.class));
            }
        });
    }
}