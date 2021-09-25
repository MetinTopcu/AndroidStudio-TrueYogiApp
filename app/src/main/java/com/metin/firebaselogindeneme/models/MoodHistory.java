package com.metin.firebaselogindeneme.models;

import java.io.Serializable;

public class MoodHistory implements Serializable {

    private int id;
    private String moodcode;
    private String moodselecteddate;


    public MoodHistory() {
    }

    public MoodHistory(int id, String moodcode, String moodselecteddate) {
        this.id = id;
        this.moodcode = moodcode;
        this.moodselecteddate = moodselecteddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodcode() {
        return moodcode;
    }

    public void setMoodcode(String moodcode) {
        this.moodcode = moodcode;
    }

    public String getMoodselecteddate() {
        return moodselecteddate;
    }

    public void setMoodselecteddate(String moodselecteddate) {
        this.moodselecteddate = moodselecteddate;
    }

    /*
    public void notSil(Veritabani vt,int id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("mood_history","id=?",new String[]{String.valueOf(id)});
        db.close();
    }
     */
}
