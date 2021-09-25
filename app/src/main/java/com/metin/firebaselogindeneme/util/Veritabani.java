package com.metin.firebaselogindeneme.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(@Nullable Context context) {
        super(context, "trueyogi.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"mantra\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"mantra\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");
        db.execSQL("CREATE TABLE \"mantra_history\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"codes\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");
        db.execSQL("CREATE TABLE \"mood_history\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"moodcode\"\tTEXT,\n" +
                "\t\"moodselecteddate\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");
        db.execSQL("CREATE TABLE \"mood_tracking\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"content\"\tTEXT,\n" +
                "\t\"iconurl\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mantra");
        db.execSQL("DROP TABLE IF EXISTS mantra_history");
        db.execSQL("DROP TABLE IF EXISTS mood_history");
        db.execSQL("DROP TABLE IF EXISTS mood_tracking");
        onCreate(db);
    }
}
