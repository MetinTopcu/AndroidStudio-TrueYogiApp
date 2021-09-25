package com.metin.firebaselogindeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PoseListActivity extends AppCompatActivity {
    private Button btnPoseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_list);

        btnPoseList=findViewById(R.id.btnPoseList);
        final EditText sure = findViewById(R.id.editTxtSure);
        btnPoseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String girilen= sure.getText().toString();
                Intent yeniInstant=new Intent(PoseListActivity.this, PostListDevamActivity.class);

                yeniInstant.putExtra("süre",girilen);
                startActivity(yeniInstant);
            }
        });
    }
}