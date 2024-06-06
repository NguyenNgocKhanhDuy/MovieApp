package com.example.movieapp;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.db.Movie;
import com.example.movieapp.services.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 items per row

        movieList = getMovies("Movies"); // Default category
        movieAdapter = new MovieAdapter(getContext(), movieList);
        recyclerView.setAdapter(movieAdapter);

        view.findViewById(R.id.tab_movies).setOnClickListener(v -> updateMovies("Movies"));
        view.findViewById(R.id.tab_tv_series).setOnClickListener(v -> updateMovies("Tv Series"));
        view.findViewById(R.id.tab_documentary).setOnClickListener(v -> updateMovies("Documentary"));
        view.findViewById(R.id.tab_sports).setOnClickListener(v -> updateMovies("Sports"));

        return view;
    }

    private void updateMovies(String category) {
        movieList = getMovies(category);
        movieAdapter.updateMovies(movieList);
        resetCategoryTextColor();
        switch (category) {
            case "Movies":
                ((TextView) getView().findViewById(R.id.tab_movies)).setTextColor(Color.WHITE);
                break;
            case "Tv Series":
                ((TextView) getView().findViewById(R.id.tab_tv_series)).setTextColor(Color.WHITE);
                break;
            case "Documentary":
                ((TextView) getView().findViewById(R.id.tab_documentary)).setTextColor(Color.WHITE);
                break;
            case "Sports":
                ((TextView) getView().findViewById(R.id.tab_sports)).setTextColor(Color.WHITE);
                break;
        }
    }
    private void resetCategoryTextColor() {
        // Reset text color for all categories
        ((TextView) getView().findViewById(R.id.tab_movies)).setTextColor(Color.parseColor("#888888"));
        ((TextView) getView().findViewById(R.id.tab_tv_series)).setTextColor(Color.parseColor("#888888"));
        ((TextView) getView().findViewById(R.id.tab_documentary)).setTextColor(Color.parseColor("#888888"));
        ((TextView) getView().findViewById(R.id.tab_sports)).setTextColor(Color.parseColor("#888888"));
    }
    private List<Movie> getMovies(String category) {
        // Retrieve the list of movies based on the category
        // This is just a placeholder. Replace with actual data retrieval logic.
        List<Movie> movies = new ArrayList<>();
        switch (category) {
            case "Movies":
                movies.add(new Movie(R.drawable.soul_poster,"Soul (2020)",""));
                movies.add(new Movie(R.drawable.knive_out_poster,"Knives Out (2019)","" ));
                movies.add(new Movie(R.drawable.onward_poster,"Onward (2020)", ""));
                movies.add(new Movie(R.drawable.mulan_poster,",Mulan (2020)",""));
                break;
            case "Tv Series":
                movies.add(new Movie(R.drawable.onward_poster,"Onward (2020)", ""));
                movies.add(new Movie(R.drawable.mulan_poster,"Mulan (2020)",""));
                break;
            case "Documentary":
                movies.add(new Movie(R.drawable.mulan_poster,"Mulan (2020)",""));
                break;
            case "Sports":
                movies.add(new Movie(R.drawable.the_flash_poster,"The FLash",""));
                break;
        }
        return movies;
    }
}

