package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.api.APIService;
import com.example.movieapp.dao.UserDao;
import com.example.movieapp.model.api.MovieItem;
import com.example.movieapp.model.db.ImageMovie;
import com.example.movieapp.services.MovieAdapter;
import com.example.movieapp.model.api.MovieDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView back;
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
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadHistory();
    }

    private void loadHistory() {
//        SharedPreferences sharedPreferences = getSharedPreferences("movie_history", MODE_PRIVATE);
//        Map<String, ?> allEntries = sharedPreferences.getAll();
//        Gson gson = new Gson();
//
//        Log.d("HistoryActivity", "Number of entries: " + allEntries.size());
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            String movieDetailJson = (String) entry.getValue();
//            MovieDetail movieDetail = gson.fromJson(movieDetailJson, MovieDetail.class);
//            movieHistoryList.add(movieDetail);
//            Log.d("HistoryActivity", "Loaded movie: " + movieDetail.getName());
//        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserDao.getInstance().getListHistoryMovie(user, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ImageMovie> imageMovies = new ArrayList<>();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    ImageMovie imageMovie = itemSnapshot.getValue(ImageMovie.class);
                    if (imageMovie != null) {
                        imageMovies.add(imageMovie);
                    }
                }
                Collections.sort(imageMovies, new Comparator<ImageMovie>() {
                    @Override
                    public int compare(ImageMovie o1, ImageMovie o2) {
                        return Long.compare(o2.getTimeStamp(), o1.getTimeStamp());
                    }
                });

                for (ImageMovie i: imageMovies) {
                    MovieDetail movieDetail = new MovieDetail();
                    movieDetail.setName(i.getName());
                    movieDetail.setPosterURL(i.getUrl());
                    movieHistoryList.add(movieDetail);
                }
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
