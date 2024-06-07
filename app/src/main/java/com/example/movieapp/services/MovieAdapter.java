package com.example.movieapp.services;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.MovieDetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.api.MovieDetail;
import com.example.movieapp.model.db.Movie;

import java.util.ArrayList;
import java.util.List;

//import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieDetail> movieList;
    private Context context;

    public MovieAdapter(Context context, List<MovieDetail> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                String slug = v.getTag().toString();
                Log.d(TAG, "SLUG: "+slug);
                bundle.putString("slug", slug);
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);
            }
        });
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieDetail movie = movieList.get(position);
        holder.movieTitle.setText(movie.getName());
        Glide.with(context)
                .load(movie.getPosterURL())
                .into(holder.moviePoster);
        holder.setMoviePosterClickListener(movie.getSlug(), context);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateMovies(List<MovieDetail> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieTitle = itemView.findViewById(R.id.movie_title);
        }

        public void setMoviePosterClickListener(final String slug, Context context) {
            moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    Bundle bundle = new Bundle();
                    Log.d(TAG, "SLUG: " + slug);
                    bundle.putString("slug", slug);
                    intent.putExtra("bundle", bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}

