package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.movieapp.api.APIService;
import com.example.movieapp.dao.HistoryMovieDao;
import com.example.movieapp.dao.MyDataBaseHelper;
import com.example.movieapp.model.api.ListMovieStatusBoolean;
import com.example.movieapp.model.api.Movie;
import com.example.movieapp.model.api.MovieDetail;
import com.example.movieapp.services.ImageSliderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        FrameLayout history = view.findViewById(R.id.history);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        MyDataBaseHelper helper = new MyDataBaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        HistoryMovieDao historyMovieDao = new HistoryMovieDao(db);
        if (user != null) {
            ImageView img = view.findViewById(R.id.slug_movie_home);
            ImageButton play = view.findViewById(R.id.play);

            List<MovieDetail> list = historyMovieDao.getAllHistoryMovies(user.getEmail());
            if (list.size() > 0) {
                MovieDetail movieDetail = list.get(0);

                Glide.with(getContext())
                        .load(movieDetail.getPosterURL())
                        .into(img);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                        Bundle bundle = new Bundle();
                        Log.d(TAG, "SLUG: " + movieDetail.getSlug());
                        bundle.putString("slug", movieDetail.getSlug());
                        intent.putExtra("bundle", bundle);
                        getContext().startActivity(intent);
                    }
                });
            }else {
                history.setVisibility(View.GONE);
            }
        }else {
            history.setVisibility(View.GONE);
        }

        List<MovieDetail> imageUrls = new ArrayList<>();

        APIService.apiService.callNewUpdateMovie(1).enqueue(new Callback<ListMovieStatusBoolean>() {
            @Override
            public void onResponse(Call<ListMovieStatusBoolean> call, Response<ListMovieStatusBoolean> response) {
                ListMovieStatusBoolean listMovie = response.body();
                if (listMovie != null && listMovie.isStatus()){
                    for (MovieDetail movie: listMovie.getMovie()) {
                        imageUrls.add(movie);
                    }
                    Log.d(TAG, "Log 1: "+imageUrls.toString());

                    ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), imageUrls);
                    viewPager.setAdapter(adapter);


                    // Adjust offscreenPageLimit to keep more items loaded
                    viewPager.setOffscreenPageLimit(10);

                    // Set initial position to the middle to allow for smooth infinite scrolling
                    viewPager.setCurrentItem(adapter.getItemCount() / 2, false);
                    // Add page change callback to reset position if needed
                    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            // Logic to handle page selection
                            // Example logic to prevent swiping out of bounds
                            if (position == 0 || position == adapter.getItemCount() - 1) {
                                // Don't allow swiping out of bounds
                                viewPager.setCurrentItem(position, false);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ListMovieStatusBoolean> call, Throwable throwable) {
               Log.d(TAG, "FAIL");
            }
        });


        return view;
    }
}
