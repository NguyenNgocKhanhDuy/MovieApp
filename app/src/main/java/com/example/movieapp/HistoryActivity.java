package com.example.movieapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.api.Movie;
import com.example.movieapp.services.MovieAdapter;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rcvMovies;
    private MoviesAdapter moviesAdapter;
    private HistoryManager historyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rcvMovies = findViewById(R.id.rcv_movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMovies.setLayoutManager(linearLayoutManager);

        historyManager = new HistoryManager(this);
        List<Movie> movieList = historyManager.getHistory();

        moviesAdapter = new MoviesAdapter(movieList);
        rcvMovies.setAdapter(moviesAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvMovies.addItemDecoration(itemDecoration);
    }
}
