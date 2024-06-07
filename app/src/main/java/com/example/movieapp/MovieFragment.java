package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movieapp.api.APIService;
import com.example.movieapp.model.api.ListMovieStatusBoolean;
import com.example.movieapp.model.api.ListMovieStatusString;
import com.example.movieapp.model.api.Movie;
import com.example.movieapp.model.api.MovieDetail;
import com.example.movieapp.services.ImageSliderAdapter;
import com.example.movieapp.services.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<MovieDetail> movieList;
    private Button moreBtn;
    private String categories;
    private int page;
    private EditText searchBar;
    private Button searchBtn;
    private int limit;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 items per row

        moreBtn = view.findViewById(R.id.more);
//        searchBar = view.findViewById(R.id.search_bar);
//        searchBtn = view.findViewById(R.id.searchbtn);

        movieList = new ArrayList<>();
        categories = "all";
        page = 1;
        limit = 10;
//        movieList = getMovies("phim-le"); // Default category
        movieAdapter = new MovieAdapter(getContext(), movieList);
        recyclerView.setAdapter(movieAdapter);

        view.findViewById(R.id.tab_all).setOnClickListener(v -> {
            categories = "all";
            movieList.clear();
            updateMovies(categories, "", limit, view);
        });

        view.findViewById(R.id.tab_phimle).setOnClickListener(v -> {
            categories = "phim-le";
            movieList.clear();
            updateMovies(categories, "", limit, view);
        });

        view.findViewById(R.id.tab_phimbo).setOnClickListener(v -> {
            categories = "phim-bo";
            movieList.clear();
            updateMovies(categories, "", limit, view);
        });
        view.findViewById(R.id.tab_hoathinh).setOnClickListener(v -> {
            categories = "hoat-hinh";
            movieList.clear();
            updateMovies(categories, "", limit, view);
        });
        view.findViewById(R.id.tab_tvshows).setOnClickListener(v -> {
            categories = "tv-shows";
            movieList.clear();
            updateMovies(categories, "", limit, view);
        });

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                if (categories.equals("")){
                    limit += 10;
                    String keyword = searchBar.getText().toString().trim();
                    updateMovies(categories, keyword, limit, view);
                }else {
                    updateMovies(categories, "", limit, view);
                }
            }
        });

//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                movieList.clear();
//                limit = 10;
//                categories = "";
//                String keyword = searchBar.getText().toString().trim();
//                updateMovies(categories, keyword, limit, view);
//            }
//        });
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieList.clear();
                limit = 10;
                categories = "";
                updateMovies(categories, query, limit, view);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        updateMovies(categories, "", limit, view);
        return view;
    }

    private void updateMovies(String category, String keyword, int limit,  View view) {
//        movieList = getMovies(category);
        getMovies(category, keyword, limit);
        movieAdapter.updateMovies(movieList);
        resetCategoryTextColor(view);
        switch (category) {
            case "all":
                ((TextView) view.findViewById(R.id.tab_all)).setTextColor(Color.WHITE);
                break;
            case "phim-le":
                ((TextView) view.findViewById(R.id.tab_phimle)).setTextColor(Color.WHITE);
                break;
            case "phim-bo":
                ((TextView) view.findViewById(R.id.tab_phimbo)).setTextColor(Color.WHITE);
                break;
            case "hoat-hinh":
                ((TextView) view.findViewById(R.id.tab_hoathinh)).setTextColor(Color.WHITE);
                break;
            case "tv-shows":
                ((TextView) view.findViewById(R.id.tab_tvshows)).setTextColor(Color.WHITE);
                break;
        }
    }
    private void resetCategoryTextColor(View view) {
        // Reset text color for all categories
        ((TextView) view.findViewById(R.id.tab_all)).setTextColor(Color.parseColor("#888888"));
        ((TextView) view.findViewById(R.id.tab_phimle)).setTextColor(Color.parseColor("#888888"));
        ((TextView) view.findViewById(R.id.tab_phimbo)).setTextColor(Color.parseColor("#888888"));
        ((TextView) view.findViewById(R.id.tab_hoathinh)).setTextColor(Color.parseColor("#888888"));
        ((TextView) view.findViewById(R.id.tab_tvshows)).setTextColor(Color.parseColor("#888888"));
    }
    private void getMovies(String category, String keyword, int limit) {
        // Retrieve the list of movies based on the category
        // This is just a placeholder. Replace with actual data retrieval logic.
//        List<MovieDetail> movies = new ArrayList<>();
        if (category.equals("all")){
            APIService.apiService.callNewUpdateMovie(page).enqueue(new Callback<ListMovieStatusBoolean>() {
                @Override
                public void onResponse(Call<ListMovieStatusBoolean> call, Response<ListMovieStatusBoolean> response) {
                    ListMovieStatusBoolean listMovie = response.body();
                    Log.d(TAG, "Page: "+page+"LIST ALL: "+listMovie);
                    if (listMovie != null && listMovie.isStatus()){
                        movieList.addAll(listMovie.getMovie());
                        movieAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ListMovieStatusBoolean> call, Throwable throwable) {
                    Log.d(TAG, "FAIL");
                }
            });
        } else if (category.equals("")) {
            APIService.apiService.callMovieBySearch(keyword, limit).enqueue(new Callback<ListMovieStatusString>() {
                @Override
                public void onResponse(Call<ListMovieStatusString> call, Response<ListMovieStatusString> response) {
                    ListMovieStatusString listMovie = response.body();
                    Log.d(TAG, "LIMIT: "+limit+"LIST: "+listMovie);

                    if (listMovie != null && listMovie.isStatus().equals("success")){
                        String imgDomain = listMovie.getData().getImageDomain();
                        if (limit <= 10) {
                            for (MovieDetail m: listMovie.getData().getMovieDetails()) {
                                m.setPosterURL(imgDomain+"/"+m.getPosterURL());
                                m.setThumbURL(imgDomain+"/"+m.getThumbURL());
                                movieList.add(m);
                                movieAdapter.notifyDataSetChanged();
                            }
                        }else {
                            if (listMovie.getData().getMovieDetails().size() >= limit){
                                Log.d(TAG, "SIZE 1: "+listMovie.getData().getMovieDetails().size());
                                Log.d(TAG, "SIZE 2: "+movieList.size());
                                Log.d(TAG, "SIZE 3: "+limit);
                                for (int i = movieList.size(); i < listMovie.getData().getMovieDetails().size(); i++) {
                                    listMovie.getData().getMovieDetails().get(i).setThumbURL(imgDomain+"/"+listMovie.getData().getMovieDetails().get(i).getThumbURL());
                                    listMovie.getData().getMovieDetails().get(i).setPosterURL(imgDomain+"/"+listMovie.getData().getMovieDetails().get(i).getPosterURL());
                                    movieList.add(listMovie.getData().getMovieDetails().get(i));
                                    movieAdapter.notifyDataSetChanged();
                                }
                            }else {
                                Toast.makeText(getContext(), "Không còn kết quả hiển thị thêm + "+listMovie.getData().getMovieDetails().size(), Toast.LENGTH_SHORT).show();
                            }

//                            if (listMovie.getData().getMovieDetails().size() >= limit) {
//                            }else {
//                                for (int i = movieList.size(); i < listMovie.getData().getMovieDetails().size(); i++) {
//                                    listMovie.getData().getMovieDetails().get(i).setThumbURL(imgDomain+"/"+movieList.get(i).getThumbURL());
//                                    listMovie.getData().getMovieDetails().get(i).setPosterURL(imgDomain+"/"+movieList.get(i).getPosterURL());
//                                    movieList.add(listMovie.getData().getMovieDetails().get(i));
//                                    movieAdapter.notifyDataSetChanged();
//                                }
//                            }
                        }
//                        for (MovieDetail m: listMovie.getData().getMovieDetails()) {
//                            m.setPosterURL(imgDomain+"/"+m.getPosterURL());
//                            m.setThumbURL(imgDomain+"/"+m.getThumbURL());
//                            movieList.add(m);
//                            movieAdapter.notifyDataSetChanged();
//                        }
                    }
                }

                @Override
                public void onFailure(Call<ListMovieStatusString> call, Throwable throwable) {
                    Log.d(TAG, "FAIL: SEARCH");
                }
            });
        } else {
            APIService.apiService.callMovieOfType(category, page).enqueue(new Callback<ListMovieStatusString>() {
                @Override
                public void onResponse(Call<ListMovieStatusString> call, Response<ListMovieStatusString> response) {
                    ListMovieStatusString listMovie = response.body();
                    Log.d(TAG, "Page: "+page+"LIST: "+listMovie);

                    if (listMovie != null && listMovie.isStatus().equals("success")){
                        String imgDomain = listMovie.getData().getImageDomain();
                        for (MovieDetail m: listMovie.getData().getMovieDetails()) {
                            m.setPosterURL(imgDomain+"/"+m.getPosterURL());
                            m.setThumbURL(imgDomain+"/"+m.getThumbURL());
                            movieList.add(m);
                            movieAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ListMovieStatusString> call, Throwable throwable) {
                    Log.d(TAG, "FAIL: "+category);
                }
            });
        }

    }

    private void searchMovie(String keyword, int limit) {

    }
}

