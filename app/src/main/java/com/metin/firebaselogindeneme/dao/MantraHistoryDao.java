package com.metin.firebaselogindeneme.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metin.firebaselogindeneme.models.MantraHistory;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.util.ArrayList;

public class MantraHistoryDao {

    public ArrayList<MantraHistory> tumMantrahistory(Veritabani vt){
        ArrayList<MantraHistory> mantrahistoryArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM mantra_history",null);

        while (c.moveToNext()){
            MantraHistory mh = new MantraHistory(c.getInt(c.getColumnIndex("id"))
                    ,c.getString(c.getColumnIndex("codes")));

            mantrahistoryArrayList.add(mh);
        }

        return mantrahistoryArrayList;
    }
}
