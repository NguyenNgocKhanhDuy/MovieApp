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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT NOT NULL)");

        db.execSQL("CREATE TABLE HistoryMovie (" +
                "idUser INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " +
                "img TEXT NOT NULL," +
                "PRIMARY KEY (idUser, name), " +
                "FOREIGN KEY (idUser) REFERENCES User(id) " +
                "ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
