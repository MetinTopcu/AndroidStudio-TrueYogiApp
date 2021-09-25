package com.metin.firebaselogindeneme.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metin.firebaselogindeneme.models.MoodHistory;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.util.ArrayList;

public class MoodHistoryDao {

    public ArrayList<MoodHistory> tumMoodHistory(Veritabani vt){
        ArrayList<MoodHistory> moodhistoryArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM mood_history",null);

        while (c.moveToNext()){
            MoodHistory mh = new MoodHistory(c.getInt(c.getColumnIndex("id"))
                    ,c.getString(c.getColumnIndex("moodcode")),
                    c.getString(c.getColumnIndex("moodselecteddate")));

            moodhistoryArrayList.add(mh);
        }

        return moodhistoryArrayList;
    }

    public void moodAdd(Veritabani vt,String moodcode,String moodselecteddate){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("moodcode",moodcode);
        values.put("moodselecteddate",moodselecteddate);

        db.insertOrThrow("mood_history",null,values);
        db.close();
    }
}
