package com.metin.firebaselogindeneme.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metin.firebaselogindeneme.models.MoodTracking;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.util.ArrayList;

public class MoodTrackingDao {

    public ArrayList<MoodTracking> tumMoodTracking(Veritabani vt){
        ArrayList<MoodTracking> moodtrackingArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM mood_tracking",null);

        while (c.moveToNext()){
            MoodTracking mt = new MoodTracking(c.getInt(c.getColumnIndex("id"))
                    ,c.getString(c.getColumnIndex("title"))
                    ,c.getString(c.getColumnIndex("content"))
                    ,c.getString(c.getColumnIndex("iconurl")));

            moodtrackingArrayList.add(mt);
        }

        return moodtrackingArrayList;
    }
}
