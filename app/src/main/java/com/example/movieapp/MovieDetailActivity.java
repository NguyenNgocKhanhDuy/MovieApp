package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.api.APIService;
import com.example.movieapp.model.api.Category;
import com.example.movieapp.model.api.Movie;
import com.example.movieapp.model.api.MovieItem;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView back;
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieDuration;
    private TextView movieRating;
    private TextView movieReleaseDate;
    private TextView movieGenre;
    private TextView movieSynopsis;
//    private RecyclerView recyclerRelatedMovies;
    private MoviesAdapter relatedMoviesAdapter;
    private List<Movie> relatedMovieList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);

        back = findViewById(R.id.back);
        moviePoster = findViewById(R.id.movie_poster);
        movieTitle = findViewById(R.id.movie_title);
        movieDuration = findViewById(R.id.movie_duration);
        movieRating = findViewById(R.id.movie_rating);
        movieReleaseDate = findViewById(R.id.movie_release_date);
        movieGenre = findViewById(R.id.movie_genre);
        movieSynopsis = findViewById(R.id.movie_synopsis);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        recyclerRelatedMovies = view.findViewById(R.id.recycler_related_movies);

//        recyclerRelatedMovies.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));

        // Sample data for related movies
//        relatedMovieList = new ArrayList<>();
//        relatedMovieList.add(new Movie("Star Wars: The Rise of Skywalker", "2019", R.drawable.star_wars_rise_of_skywalker));
//        relatedMovieList.add(new Movie("Star Wars: The Force Awakens", "2015", R.drawable.star_wars_force_awakens));
//        relatedMovieList.add(new Movie("Rogue One: A Star Wars Story", "2016", R.drawable.rogue_one));

//        relatedMoviesAdapter = new MoviesAdapter(relatedMovieList);
//        recyclerRelatedMovies.setAdapter(relatedMoviesAdapter);

        // Set movie details (these values would typically come from a data source)

        Bundle bundle = getIntent().getBundleExtra("bundle");
        String slug = bundle.getString("slug");

        APIService.apiService.callMovieDetail(slug).enqueue(new Callback<MovieItem>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                MovieItem movieItem = response.body();
                Log.d(TAG, "Movie: "+movieItem.getMovieDetail());
                if (movieItem != null && movieItem.isStatus()){
                    Glide.with(MovieDetailActivity.this).load(movieItem.getMovieDetail().getThumbURL()).into(moviePoster);
                    movieTitle.setText(movieItem.getMovieDetail().getName());
                    movieDuration.setText(movieItem.getMovieDetail().getTime());
                    movieRating.setText("No Rating");

                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(movieItem.getMovieDetail().getCreated().getTime());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fortmatterDate = zonedDateTime.format(formatter);
                    movieReleaseDate.setText(fortmatterDate);

                    List<Category> categories = movieItem.getMovieDetail().getCategory();
                    String s = "";

                    for (int i = 0; i < categories.size(); i++) {
                        if (i != categories.size()-1){
                            s += categories.get(i).getName() +", ";
                        }else {
                            s += categories.get(i).getName();
                        }
                    }

                    movieGenre.setText(s);
                    movieSynopsis.setText(movieItem.getMovieDetail().getContent());
                }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable throwable) {
                Log.d(TAG, "FAIL");
            }
        });

//        moviePoster.setImageResource(R.drawable.star_wars_last_jedi);
//        movieTitle.setText("Star Wars: The Last Jedi");
//        movieDuration.setText("152 minutes");
//        movieRating.setText("7.0 (IMDb)");
//        movieReleaseDate.setText("December 9, 2017");
//        movieGenre.setText("Action, Sci-Fi");
//        movieSynopsis.setText("Rey (Daisy Ridley) finally manages to find the legendary Jedi knight, Luke Skywalker (Mark Hamill) on an island with a magical aura. The heroes of The Force Awakens including Leia, Finn...");
        // Play button click listener
        View playButton;
        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(v -> {
            // Code to start video playback or navigate to video player
            Toast.makeText(MovieDetailActivity.this, "Play button clicked", Toast.LENGTH_SHORT).show();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}