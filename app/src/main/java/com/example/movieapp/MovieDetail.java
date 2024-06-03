package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.MoviesAdapter;
import com.example.movieapp.model.api.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail extends Fragment {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieDuration;
    private TextView movieRating;
    private TextView movieReleaseDate;
    private TextView movieGenre;
    private TextView movieSynopsis;
    private RecyclerView recyclerRelatedMovies;
    private MoviesAdapter relatedMoviesAdapter;
    private List<Movie> relatedMovieList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        moviePoster = view.findViewById(R.id.movie_poster);
        movieTitle = view.findViewById(R.id.movie_title);
        movieDuration = view.findViewById(R.id.movie_duration);
        movieRating = view.findViewById(R.id.movie_rating);
        movieReleaseDate = view.findViewById(R.id.movie_release_date);
        movieGenre = view.findViewById(R.id.movie_genre);
        movieSynopsis = view.findViewById(R.id.movie_synopsis);
//        recyclerRelatedMovies = view.findViewById(R.id.recycler_related_movies);

        recyclerRelatedMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Sample data for related movies
        relatedMovieList = new ArrayList<>();
//        relatedMovieList.add(new Movie("Star Wars: The Rise of Skywalker", "2019", R.drawable.star_wars_rise_of_skywalker));
//        relatedMovieList.add(new Movie("Star Wars: The Force Awakens", "2015", R.drawable.star_wars_force_awakens));
//        relatedMovieList.add(new Movie("Rogue One: A Star Wars Story", "2016", R.drawable.rogue_one));

        relatedMoviesAdapter = new MoviesAdapter(relatedMovieList);
        recyclerRelatedMovies.setAdapter(relatedMoviesAdapter);

        // Set movie details (these values would typically come from a data source)
        moviePoster.setImageResource(R.drawable.star_wars_last_jedi);
        movieTitle.setText("Star Wars: The Last Jedi");
        movieDuration.setText("152 minutes");
        movieRating.setText("7.0 (IMDb)");
        movieReleaseDate.setText("December 9, 2017");
        movieGenre.setText("Action, Sci-Fi");
        movieSynopsis.setText("Rey (Daisy Ridley) finally manages to find the legendary Jedi knight, Luke Skywalker (Mark Hamill) on an island with a magical aura. The heroes of The Force Awakens including Leia, Finn...");
        // Play button click listener
        View playButton;
        playButton = view.findViewById(R.id.play_button);
        playButton.setOnClickListener(v -> {
            // Code to start video playback or navigate to video player
            Toast.makeText(getContext(), "Play button clicked", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
