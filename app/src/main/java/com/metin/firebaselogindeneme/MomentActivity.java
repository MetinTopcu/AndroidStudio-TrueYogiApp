package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MomentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton relaxbtn,energybtn,sleepbtn,focusbtn;
    public static int which = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);

        toolbar = findViewById(R.id.toolbar);
        relaxbtn = findViewById(R.id.relaxbtn);
        energybtn = findViewById(R.id.energybtn);
        sleepbtn = findViewById(R.id.sleepbtn);
        focusbtn = findViewById(R.id.focusbtn);

        toolbar.setTitle("Moment");
        setSupportActionBar(toolbar);

        relaxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 1;
                startActivity(new Intent(MomentActivity.this, ChooseToolActivity.class));
            }
        });

        energybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 2;
                startActivity(new Intent(MomentActivity.this, ChooseToolActivity.class));
            }
        });

        sleepbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 3;
                startActivity(new Intent(MomentActivity.this, ChooseToolActivity.class));
            }
        });

        focusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 4;
                startActivity(new Intent(MomentActivity.this, ChooseToolActivity.class));
            }
        });
    }
}