package com.example.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.services.MovieAdapter;
import com.example.movieapp.model.api.MovieDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<MovieDetail> movieHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rcv_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this, movieHistoryList);
        recyclerView.setAdapter(movieAdapter);

        loadHistory();
    }

    private void loadHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("movie_history", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        Gson gson = new Gson();

        Log.d("HistoryActivity", "Number of entries: " + allEntries.size());
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String movieDetailJson = (String) entry.getValue();
            MovieDetail movieDetail = gson.fromJson(movieDetailJson, MovieDetail.class);
            movieHistoryList.add(movieDetail);
            Log.d("HistoryActivity", "Loaded movie: " + movieDetail.getName());
        }

        movieAdapter.notifyDataSetChanged();
    }
}
