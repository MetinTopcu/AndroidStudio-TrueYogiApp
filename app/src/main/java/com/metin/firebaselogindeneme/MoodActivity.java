package com.metin.firebaselogindeneme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metin.firebaselogindeneme.dao.MoodHistoryDao;
import com.metin.firebaselogindeneme.dao.MoodTrackingDao;
import com.metin.firebaselogindeneme.models.MoodHistory;
import com.metin.firebaselogindeneme.models.MoodTracking;
import com.metin.firebaselogindeneme.util.MoodHistoryAdapter;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MoodActivity extends AppCompatActivity {

    private Context context;
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<MoodHistory> moodHistoryArrayList;
    private MoodHistoryAdapter adapter;
    private Veritabani vt;
    private int mood = 0;
    public static int tummood;

    private ArrayList<MoodTracking> moodTrackingArrayList;
    private MoodTracking moodTracking;

    public static ArrayList<MoodTracking> moodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        vt = new Veritabani(this);

        moodTrackingArrayList = new MoodTrackingDao().tumMoodTracking(vt);

        toolbar.setTitle("Mood History");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        moodHistoryArrayList = new MoodHistoryDao().tumMoodHistory(vt);

        moodlist = new ArrayList<>();

        MoodTracking m1 = new MoodTracking(1,"Rad","Süper,sırrını insanlarla paylaş.","rad");
        MoodTracking m2 = new MoodTracking(2,"Rad","Böyle hissetmeye devam et.","rad");
        MoodTracking m3 = new MoodTracking(3,"Rad","Güzel bir gün seni bekliyor.","rad");
        MoodTracking m4 = new MoodTracking(4,"Good","Mutluluk seni güzel gösteriyor!","good");
        MoodTracking m5 = new MoodTracking(5,"Good","Mutluluk bugün komşun ve sana çok yakın.","good");
        MoodTracking m6 = new MoodTracking(6,"Good","Hayalini kurduğun mutluluk bugün seninle.","good");
        MoodTracking m7 = new MoodTracking(7,"Meh","Daha iyi hissedebilirsin, günün hala bitmedi!","meh");
        MoodTracking m8 = new MoodTracking(8,"Meh","Her şey harika, yeter ki farkına var.","meh");
        MoodTracking m9 = new MoodTracking(9,"Meh","Günün en güzel saatleri henüz yeni başlıyor.","meh");
        MoodTracking m10 = new MoodTracking(10,"Bad","Hissetmeye başla, bugün güzel bir gün!","bad");
        MoodTracking m11 = new MoodTracking(11,"Bad","Sorun yok, her şey yolunda.","bad");
        MoodTracking m12 = new MoodTracking(12,"Bad","Kafanı rahatlat ve hissetmeye devam et.","bad");
        MoodTracking m13 = new MoodTracking(13,"Awful","Her ne yaşıyorsan senin için burada olduğumuzu bil!","awful");
        MoodTracking m14 = new MoodTracking(14,"Awful","Bugünü daha iyi yapmak için biraz meditasyona ne dersin?","awful");
        MoodTracking m15 = new MoodTracking(15,"Awful","Unutma, harika hissetmek senin elinde.","awful");

        moodlist.add(m1);
        moodlist.add(m2);
        moodlist.add(m3);
        moodlist.add(m4);
        moodlist.add(m5);
        moodlist.add(m6);
        moodlist.add(m7);
        moodlist.add(m8);
        moodlist.add(m9);
        moodlist.add(m10);
        moodlist.add(m11);
        moodlist.add(m12);
        moodlist.add(m13);
        moodlist.add(m14);
        moodlist.add(m15);

        adapter = new MoodHistoryAdapter(this,moodHistoryArrayList);

        rv.setAdapter(adapter);

        if (tummood >= 1  && tummood <= 5){
            alertGoster1();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alertGoster();
                startActivity(new Intent(MoodActivity.this, MoodTrackingActivity.class));
                finish();
            }
        });
    }

    public void alertGoster1(){
        LayoutInflater layout = LayoutInflater.from(this);
        View view = layout.inflate(R.layout.aler_tasarim1,null);

        final TextView textViewMoodCode = view.findViewById(R.id.textViewMoodCode);

        if (tummood == 1){
            Random rand = new Random();
            int number = rand.nextInt(3);
            moodTracking = moodlist.get(number);
            textViewMoodCode.setText(moodTracking.getContent());
        }
        else  if (tummood == 2){
            Random rand = new Random();
            int number = rand.nextInt(3)+3;
            moodTracking = moodlist.get(number);
            textViewMoodCode.setText(moodTracking.getContent());
        }
        else  if (tummood == 3){
            Random rand = new Random();
            int number = rand.nextInt(3)+6;
            moodTracking = moodlist.get(number);
            textViewMoodCode.setText(moodTracking.getContent());
        }
        else  if (tummood == 4){
            Random rand = new Random();
            int number = rand.nextInt(3)+9;
            moodTracking = moodlist.get(number);
            textViewMoodCode.setText(moodTracking.getContent());
        }
        else  if (tummood == 5){
            Random rand = new Random();
            int number = rand.nextInt(3)+12;
            moodTracking = moodlist.get(number);
            textViewMoodCode.setText(moodTracking.getContent());
        }

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("MoodCode");
        ad.setView(view);
        ad.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.create().show();
    }

    public void alertGoster(){
        LayoutInflater layout = LayoutInflater.from(this);
        View view = layout.inflate(R.layout.alert_tasarim,null);

        final Button btnawful = view.findViewById(R.id.btnawful2);
        final Button btngood = view.findViewById(R.id.btngood2);
        final Button btnrad = view.findViewById(R.id.btnrad2);
        final Button btnbad = view.findViewById(R.id.btnbad2);
        final Button btnmeh = view.findViewById(R.id.btnmeh2);

        btnawful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood =1;
            }
        });
        btngood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = 2;
            }
        });
        btnrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = 3;
            }
        });
        btnbad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = 4;
            }
        });
        btnmeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = 5;
            }
        });



        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Select Your Mode");
        ad.setView(view);
        ad.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mood == 0){
                    return;
                }
                else if (mood ==1){
                    String moodcode = "awful";
                    //DateFormat dateFormatDate = android.text.format.DateFormat.getDateFormat(MoodTrackingActivity.this);
                    //DateFormat dateFormatTime = android.text.format.DateFormat.getTimeFormat(MoodTrackingActivity.this);
                    //String dateStr = dateFormatDate.format(date);
                    //String timeStr = dateFormatTime.format(date);

                    //Calendar calNow = Calendar.getInstance();
                    //Date simdikiZaman = new Date();
                    //String localDateTimeString = simdikiZaman.toString();

                    //Date c = Calendar.getInstance().getTime();
                    //System.out.println("Current time => " + c);
                    //SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    //String formattedDate = df.format(c);

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getInstance().format(calendar.getTime());

                    new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                    alertGoster1();
                }
                else if (mood ==2){
                    String moodcode = "good";

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getInstance().format(calendar.getTime());

                    new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                    alertGoster1();
                }
                else if (mood ==3){
                    String moodcode = "rad";

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getInstance().format(calendar.getTime());

                    new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                    alertGoster1();
                }
                else if (mood ==4){
                    String moodcode = "bad";

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getInstance().format(calendar.getTime());

                    new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                    alertGoster1();
                }
                else if (mood ==5){
                    String moodcode = "meh";

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getInstance().format(calendar.getTime());

                    new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                    alertGoster1();
                }
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.create().show();
    }
}