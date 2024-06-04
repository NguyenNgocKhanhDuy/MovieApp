package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tv1;
    private ExoPlayer player;
    private PlayerView playerView;

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
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

        mNavigationView = findViewById(R.id.bottom_nav);
     mViewPager = findViewById(R.id.view_pager);
     setUpViewPager();
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    mViewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.action_movie) {
                    mViewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.action_user) {
                    mViewPager.setCurrentItem(2);
                }
                return true;
            }
        });

    }
    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                                break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_movie).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_user).setChecked(true);
//                        Intent intent = new Intent(MainActivity.this,Setting.class);
//                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
