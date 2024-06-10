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
import com.example.movieapp.services.MovieAdapter;
import com.example.movieapp.model.api.MovieDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
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
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    String slug = itemSnapshot.getValue(String.class);
                    if (slug != null) {
                        APIService.apiService.callMovieDetail(slug).enqueue(new Callback<MovieItem>() {
                            @Override
                            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                                MovieItem movieItem = response.body();
                                Log.d(TAG, "OK: "+ movieItem);
                                if (movieItem != null && movieItem.isStatus()){
                                    movieHistoryList.add(movieItem.getMovieDetail());
                                    movieAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onFailure(Call<MovieItem> call, Throwable throwable) {
                                Log.d(TAG, "No OK");
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
