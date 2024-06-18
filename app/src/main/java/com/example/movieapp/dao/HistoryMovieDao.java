package com.example.movieapp.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.movieapp.model.api.MovieDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryMovieDao {
    private SQLiteDatabase db;

    public HistoryMovieDao(SQLiteDatabase db) {
        this.db = db;
    }

    public void addHistoryMovie(String slug, String name, String img, String email) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("slug", slug);
        values.put("name", name);
        values.put("img", img);
        db.insert("HistoryMovie", null, values);
    }

    public List<MovieDetail> getAllHistoryMovies(String email) {
        List<MovieDetail> movies = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT slug, name, img FROM HistoryMovie WHERE email = ?", new String[]{email});
        while (cursor.moveToNext()) {
            String slug = cursor.getString(0);
            String name = cursor.getString(1);
            String img = cursor.getString(2);
            MovieDetail movieDetail = new MovieDetail();
            movieDetail.setSlug(slug);
            movieDetail.setName(name);
            movieDetail.setPosterURL(img);
            movies.add(movieDetail);
        }
        cursor.close();
        Collections.reverse(movies);

        return movies;
    }

    public void deleteHistoryMovie(String name, String email) {
        String[] selectionArgs = new String[]{email, name};
        int deletedRows = db.delete("HistoryMovie", "email = ? AND name = ?", selectionArgs);
        Log.d(TAG, "DEL: "+deletedRows);
    }
}
