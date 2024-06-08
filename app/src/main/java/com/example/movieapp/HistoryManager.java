package com.example.movieapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.movieapp.model.api.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private static final String PREFS_NAME = "movie_history";
    private static final String HISTORY_KEY = "history";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public HistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveMovie(Movie movie) {
        List<Movie> history = getHistory();
        history.add(movie);
        String json = gson.toJson(history);
        sharedPreferences.edit().putString(HISTORY_KEY, json).apply();
    }

    public List<Movie> getHistory() {
        String json = sharedPreferences.getString(HISTORY_KEY, null);
        Type type = new TypeToken<List<Movie>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }
}
