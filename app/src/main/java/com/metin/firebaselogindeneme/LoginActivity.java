package com.metin.firebaselogindeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textProfile, txtstr;
    public static int loginis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        textProfile = findViewById(R.id.textprofile);
        txtstr = findViewById(R.id.txtstr);

        String str1 = "The weather is very nice today.";
        String str2 = "Everything is great.";
        String str3 = "How was your day?";
        String str4 = "Start of a good day";

        if (loginis == 0){
            textProfile.setVisibility(View.INVISIBLE);
            txtstr.setVisibility(View.INVISIBLE);
        }
        else {
            Random rand = new Random();
            int number = rand.nextInt(4);
            switch (number){
                case 0:txtstr.setText(str1);
                break;
                case 1:txtstr.setText(str2);
                break;
                case 2:txtstr.setText(str3);
                break;
                case 3:txtstr.setText(str4);
                break;
                default:
                    break;
            }
        }

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);
    }

    public void Home(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void Profile(View view) {
        startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
    }

    public void journeygo(View view) {
        startActivity(new Intent(LoginActivity.this,PoseListActivity.class));
    }
}