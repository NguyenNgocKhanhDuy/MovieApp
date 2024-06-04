package com.example.movieapp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.db.Movie;
import com.example.movieapp.services.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment{
    private RecyclerView rcvMovies;
    private MovieAdapter movieAdapter;
    private SearchView searchView;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        setHasOptionsMenu(true);

        rcvMovies = view.findViewById(R.id.rcv_movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvMovies.setLayoutManager(linearLayoutManager);

        movieAdapter = new MovieAdapter(getListMovies());
        rcvMovies.setAdapter(movieAdapter);


        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL);
        rcvMovies.addItemDecoration(itemDecoration);


        return view;
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

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
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

        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        requireActivity().onBackPressed();
    }


}
