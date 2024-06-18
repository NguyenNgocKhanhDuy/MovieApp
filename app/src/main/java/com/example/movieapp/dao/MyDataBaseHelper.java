package com.example.movieapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Movie.db";
    private static final int DATABASE_VERSION = 1;


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteDB(Context context) {
        context.deleteDatabase("Movie.db");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (" +
                "email TEXT NOT NULL PRIMARY KEY)");

        db.execSQL("CREATE TABLE HistoryMovie (" +
                "email TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "img TEXT NOT NULL," +
                "PRIMARY KEY (email, name), " +
                "FOREIGN KEY (email) REFERENCES User(email) " +
                "ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
