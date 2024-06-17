package com.example.movieapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieapp.model.api.MovieDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryMovieDao {
    private SQLiteDatabase db;

    public HistoryMovieDao(SQLiteDatabase db) {
        this.db = db;
    }

    public void addHistoryMovie(String name, String img) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("img", img);
        db.insert("HistoryMovie", null, values);
    }

    public List<MovieDetail> getAllHistoryMovies() {
        List<MovieDetail> movies = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM HistoryMovie", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String img = cursor.getString(2);
            MovieDetail movieDetail = new MovieDetail();
            movieDetail.setName(name);
            movieDetail.setPosterURL(img);
            movies.add(movieDetail);
        }
        cursor.close();
        Collections.reverse(movies);
        return movies;
    }

    public void deleteHistoryMovie(String name) {
        db.delete("HistoryMovie", "name = ?", new String[]{String.valueOf(name)});
    }
}