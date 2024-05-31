package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.media.RouteListingPreference;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieapp.api.APIService;
import com.example.movieapp.model.MovieItem;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tv1;
    private ExoPlayer player;
    private PlayerView playerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


//    }
//        btn = findViewById(R.id.btn);
//        tv1 = findViewById(R.id.tvItem);

//        playerView = findViewById(R.id.video);
//        player = new ExoPlayer.Builder(this).build();
//        playerView.setPlayer(player);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickCallAPI();
//            }
//        });
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
//        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_home:
////                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
//                        return true;
//                    case R.id.action_movie:
//                        // Điều hướng sang MovieActivity nếu có
//                        // startActivity(new Intent(MainActivity.this, MovieActivity.class));
//                        return true;
//                    case R.id.action_user:
//                        startActivity(new Intent(MainActivity.this, Login.class));
//                        return true;
//                }
//                return false;
//            }
//        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_movie) {
                    Toast.makeText(MainActivity.this, "Movie", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_user) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
                return true;
            }
        });

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        player.release();
//    }



//    private void clickCallAPI() {
//    https://phimapi.com/danh-sach/phim-moi-cap-nhat?page=1
//        APIService.apiService.callNewUpdateMovie(1).enqueue(new Callback<ListMovieStatusBoolean>() {
//            @Override
//            public void onResponse(Call<ListMovieStatusBoolean> call, Response<ListMovieStatusBoolean> response) {
//                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//                ListMovieStatusBoolean listMovieStatusBoolean = response.body();
//                if (listMovieStatusBoolean != null && listMovieStatusBoolean.isStatus()){
//                    tv1.setText(listMovieStatusBoolean.getMovie().get(9).toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListMovieStatusBoolean> call, Throwable throwable) {
//                Toast.makeText(MainActivity.this, "NO OK", Toast.LENGTH_LONG).show();
//            }
//        });


//    https://phimapi.com/phim/khi-anh-chay-ve-phia-em
//        APIService.apiService.callMovieDetail("khi-anh-chay-ve-phia-em").enqueue(new Callback<MovieItem>() {
//            @Override
//            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
//                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//                MovieItem movieItem = response.body();
//                if (movieItem != null && movieItem.isStatus()){
//                    tv1.setText(movieItem.getMovieDetail().getName()+", Eposide: "+movieItem.getEpisodes().get(0).getEpisodeItem().get(0).getName());
//
//
//                    Uri videoUri = Uri.parse(movieItem.getEpisodes().get(0).getEpisodeItem().get(0).getLinkM3U8());
//
//                    DefaultHttpDataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory().setUserAgent(Util.getUserAgent(MainActivity.this, "MovieApp"));
//                    MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUri));
//
//                    player.setMediaSource(mediaSource);
//
//                    player.prepare();
//                    player.play();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieItem> call, Throwable throwable) {
//                Toast.makeText(MainActivity.this, "NO OK", Toast.LENGTH_LONG).show();
//            }
//        });


//    https://phimapi.com/v1/api/danh-sach/phim-le?page=1
//    https://phimapi.com/v1/api/danh-sach/phim-bo?page=1
//    https://phimapi.com/v1/api/danh-sach/hoat-hinh?page=1
//    https://phimapi.com/v1/api/danh-sach/tv-shows?page=1
//        APIService.apiService.callMovieOfType("phim-bo", 1).enqueue(new Callback<ListMovieStatusString>() {
//            @Override
//            public void onResponse(Call<ListMovieStatusString> call, Response<ListMovieStatusString> response) {
//                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//                ListMovieStatusString listMovieStatusString = response.body();
//
//                if (listMovieStatusString != null && listMovieStatusString.isStatus().equals("success")){
//                    tv1.setText(listMovieStatusString.getData().getParams().getPageInfo().toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ListMovieStatusString> call, Throwable throwable) {
//                Toast.makeText(MainActivity.this, "NO OK", Toast.LENGTH_LONG).show();
//            }
//        });

//    https://phimapi.com/v1/api/tim-kiem?keyword={Từ khóa}&limit={number}
//        APIService.apiService.callMovieBySearch("khi anh chay", 10).enqueue(new Callback<ListMovieStatusString>() {
//            @Override
//            public void onResponse(Call<ListMovieStatusString> call, Response<ListMovieStatusString> response) {
//                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//
//                ListMovieStatusString listMovieStatusString = response.body();
//
//                if (listMovieStatusString != null && listMovieStatusString.isStatus().equals("success")){
//                    tv1.setText(listMovieStatusString.getData().getMovieDetails().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListMovieStatusString> call, Throwable throwable) {
//                Toast.makeText(MainActivity.this, "NO OK", Toast.LENGTH_LONG).show();
//            }
//        });
    }
