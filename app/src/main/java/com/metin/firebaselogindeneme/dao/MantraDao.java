package com.metin.firebaselogindeneme.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metin.firebaselogindeneme.models.Mantra;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.util.ArrayList;

public class MantraDao {

    public ArrayList<Mantra> tumMantra(Veritabani vt){
        ArrayList<Mantra> mantraArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM mantra",null);

        while (c.moveToNext()){
            Mantra m = new Mantra(c.getInt(c.getColumnIndex("id"))
            ,c.getString(c.getColumnIndex("mantra")));

            mantraArrayList.add(m);
        }

        return mantraArrayList;
    }
}
