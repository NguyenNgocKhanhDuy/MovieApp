package com.example.movieapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.movieapp.model.api.MovieDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private static  HistoryManager instance;

    private static final String PREFS_NAME = "movie_history";
    private static final String HISTORY_KEY = "history";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public HistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }


    public void saveMovie(MovieDetail movie) {
        List<MovieDetail> history = getWatchedMovies();

        // Kiểm tra và xóa nếu phim đã tồn tại trong lịch sử
        for (int i = 0; i < history.size(); i++) {
            if (history.get(i).getId().equals(movie.getId())) {
                history.remove(i);
                break;
            }
        }

        //Them vao dau danh sach
        history.add(0,movie);

        // Lưu lại danh sách vào SharedPreferences
        String json = gson.toJson(history);
        sharedPreferences.edit().putString(HISTORY_KEY, json).apply();
    }

    public List<MovieDetail> getWatchedMovies() {
        String json = sharedPreferences.getString(HISTORY_KEY, null);
        Type type = new TypeToken<List<MovieDetail>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

}
