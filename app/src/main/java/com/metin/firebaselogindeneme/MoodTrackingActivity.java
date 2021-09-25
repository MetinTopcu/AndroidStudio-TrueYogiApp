package com.metin.firebaselogindeneme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.metin.firebaselogindeneme.dao.MoodHistoryDao;
import com.metin.firebaselogindeneme.models.MoodTracking;
import com.metin.firebaselogindeneme.notifications.DailyReceiver;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoodTrackingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnawful,btngood,btnrad,btnbad,btnmeh;
    private Veritabani vt;
    private MoodActivity modqwe = new MoodActivity();
    private MoodTracking moodTracking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracking);

        toolbar = findViewById(R.id.toolbar);
        btnawful = findViewById(R.id.btnawful);
        btngood = findViewById(R.id.btngood);
        btnrad = findViewById(R.id.btnrad);
        btnbad = findViewById(R.id.btnbad);
        btnmeh = findViewById(R.id.btnmeh);

        vt = new Veritabani(this);

        toolbar.setTitle("Select Your Mode");
        setSupportActionBar(toolbar);

        btnawful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                moodTracking = modqwe.moodlist.get(13);
                String moodcode = moodTracking.getTitle();
                modqwe.tummood = 5;
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
                startActivity(new Intent(MoodTrackingActivity.this,MoodActivity.class));
                finish();
            }
        });
        btngood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                moodTracking = modqwe.moodlist.get(4);
                String moodcode = moodTracking.getTitle();
                modqwe.tummood = 2;
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getInstance().format(calendar.getTime());

                new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                startActivity(new Intent(MoodTrackingActivity.this,MoodActivity.class));
                finish();
            }
        });
        btnrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                moodTracking = modqwe.moodlist.get(1);
                String moodcode = moodTracking.getTitle();
                modqwe.tummood = 1;
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getInstance().format(calendar.getTime());

                new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                startActivity(new Intent(MoodTrackingActivity.this,MoodActivity.class));
                finish();
            }
        });
        btnbad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                moodTracking = modqwe.moodlist.get(10);
                String moodcode = moodTracking.getTitle();
                modqwe.tummood = 4;
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getInstance().format(calendar.getTime());

                new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                startActivity(new Intent(MoodTrackingActivity.this,MoodActivity.class));
                finish();
            }
        });
        btnmeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                moodTracking = modqwe.moodlist.get(7);
                String moodcode = moodTracking.getTitle();
                modqwe.tummood = 3;
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getInstance().format(calendar.getTime());

                new MoodHistoryDao().moodAdd(vt,moodcode,currentDate);
                startActivity(new Intent(MoodTrackingActivity.this,MoodActivity.class));
                finish();
            }
        });
    }

    public void setAlarm() {
        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        Date currentTime = Calendar.getInstance().getTime();
        //Log.e("şuankizaman","saat="+currentTime.getHours());
        //Log.e("şuankizaman","dakika="+currentTime.getMinutes());

        calendar.set(Calendar.HOUR_OF_DAY, currentTime.getHours());
        calendar.set(Calendar.MINUTE, currentTime.getMinutes()+5);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(MoodTrackingActivity.this, DailyReceiver.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MoodTrackingActivity.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) MoodTrackingActivity.this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }
}