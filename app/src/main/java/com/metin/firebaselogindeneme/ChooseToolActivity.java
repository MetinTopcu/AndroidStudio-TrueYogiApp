package com.metin.firebaselogindeneme;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.metin.firebaselogindeneme.models.YoutubeVideos;
import com.metin.firebaselogindeneme.util.VideoAdapter;

import java.util.Vector;


public class ChooseToolActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos = new Vector<YoutubeVideos>();
    private ImageButton btnonlymusic,btnyoga,btnmeditation,btnbreath;
    private MomentActivity momentActivity = new MomentActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tool);

        toolbar = findViewById(R.id.toolbar);
        btnonlymusic = findViewById(R.id.btnonlymusic);
        btnyoga = findViewById(R.id.btnyoga);
        btnmeditation = findViewById(R.id.btnmeditation);
        btnbreath = findViewById(R.id.btnbreath);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar.setTitle("Choose A Tool");
        setSupportActionBar(toolbar);


        btnonlymusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (momentActivity.which == 1){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2OEL4P1Rz04\" frameborder=\"0\" allowfullscreen></iframe>") );
                }
                else if (momentActivity.which == 2){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fzNMd3Tu1Zw\" frameborder=\"0\" allowfullscreen></iframe>") );
                }

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

                recyclerView.setAdapter(videoAdapter);
            }
        });
        btnyoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (momentActivity.which == 1){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sTANio_2E0Q\" frameborder=\"0\" allowfullscreen></iframe>") );
                }
                else if (momentActivity.which == 2){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F47hdaNXwT4\" frameborder=\"0\" allowfullscreen></iframe>") );
                }

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

                recyclerView.setAdapter(videoAdapter);
            }
        });
        btnmeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (momentActivity.which == 1){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/W8a3T8pI9Ns\" frameborder=\"0\" allowfullscreen></iframe>") );
                }
                else if (momentActivity.which == 2){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kQ1lh2tC2w\" frameborder=\"0\" allowfullscreen></iframe>") );
                }

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

                recyclerView.setAdapter(videoAdapter);
            }
        });
        btnbreath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (momentActivity.which == 1){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1Dv-ldGLnIY\" frameborder=\"0\" allowfullscreen></iframe>") );
                }
                else if (momentActivity.which == 2){
                    youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/w2Mi0a5dDhc\" frameborder=\"0\" allowfullscreen></iframe>") );
                }

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

                recyclerView.setAdapter(videoAdapter);
            }
        });
    }
}