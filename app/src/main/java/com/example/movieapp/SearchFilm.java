package com.example.movieapp;



import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.db.Movie;
import com.example.movieapp.services.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFilm extends AppCompatActivity {
    private RecyclerView rcvMovies;
    private MovieAdapter movieAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilm);

        rcvMovies = findViewById(R.id.rcv_movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMovies.setLayoutManager(linearLayoutManager);

        movieAdapter = new MovieAdapter(getListMovies());
        rcvMovies.setAdapter(movieAdapter);

        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(this
        , DividerItemDecoration.VERTICAL);
        rcvMovies.addItemDecoration(itemDecoration);

    }

    private List<Movie> getListMovies() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(R.drawable.img2,"Doctor Who","America" ));
        list.add(new Movie(R.drawable.img3,"The Flash","America" ));
        list.add(new Movie(R.drawable.img2,"Doctor Who","America" ));
        list.add(new Movie(R.drawable.img3,"The Flash","America" ));
        list.add(new Movie(R.drawable.img2,"Doctor Who","America" ));
        list.add(new Movie(R.drawable.img3,"The Flash","America" ));
        list.add(new Movie(R.drawable.img2,"Doctor Who","America" ));
        list.add(new Movie(R.drawable.img3,"The Flash","America" ));
        list.add(new Movie(R.drawable.img2,"Doctor Who","America" ));


        return  list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
