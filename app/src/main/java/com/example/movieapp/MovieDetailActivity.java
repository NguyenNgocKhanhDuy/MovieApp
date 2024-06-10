package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.movieapp.api.APIService;
import com.example.movieapp.dao.UserDao;
import com.example.movieapp.model.api.Category;
import com.example.movieapp.model.api.Movie;
import com.example.movieapp.model.api.MovieDetail;
import com.example.movieapp.model.api.MovieItem;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import com.google.gson.Gson;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
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
    private TextView movieSynopsis, movieActor;

//    private MovieItem currentMovie;

//    private WebView trailer;
//    private FrameLayout videoContainer;

//    private ExoPlayer player;
//    private final String STATE_RESUME_WINDOW = "resumeWindow";
//    private final String STATE_RESUME_POSITION = "resumePosition";
//    private final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";
//
//    private PlayerView mExoPlayerView;
//    private SimpleExoPlayer mExoPlayer;
//    private MediaItem mVideoSource;
//    private boolean mExoPlayerFullscreen = false;
//    private FrameLayout mFullScreenButton;
//    private ImageView mFullScreenIcon;
//    private Dialog mFullScreenDialog;
//
//    private int mResumeWindow;
//    private long mResumePosition;
//    private String movieURL;
////    private PlayerView mExoPlayerView;
//
//    private ImageButton btnFullscreen;
//    private boolean isFullscreen = false;
//
//
////    private RecyclerView recyclerRelatedMovies;
//    private MoviesDetailAdapter relatedMoviesAdapter;
//    private List<Movie> relatedMovieList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);

//        if (savedInstanceState != null) {
//            mResumeWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW);
//            mResumePosition = savedInstanceState.getLong(STATE_RESUME_POSITION);
//            mExoPlayerFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN);
//        }

//        videoContainer = findViewById(R.id.videoContainer);
//        trailer = findViewById(R.id.trailer);
        back = findViewById(R.id.back);
        moviePoster = findViewById(R.id.movie_poster);
        movieTitle = findViewById(R.id.movie_title);
        movieDuration = findViewById(R.id.movie_duration);
        movieRating = findViewById(R.id.movie_rating);
        movieReleaseDate = findViewById(R.id.movie_release_date);
        movieGenre = findViewById(R.id.movie_genre);
        movieSynopsis = findViewById(R.id.movie_synopsis);
        movieActor = findViewById(R.id.actor);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);



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
            @SuppressLint("SuspiciousIndentation")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                MovieItem movieItem = response.body();
                Log.d(TAG, "Movie: "+movieItem.getMovieDetail());
                if (movieItem != null && movieItem.isStatus()){

                    String trailerUrl = movieItem.getMovieDetail().getTrailerURL();
                    if (trailerUrl.equals("")) {

//                    currentMovie = movieItem;
//                    String trailerUrl = movieItem.getMovieDetail().getTrailerURL();
//                    if (trailerUrl.equals("")) {

                        Glide.with(MovieDetailActivity.this).load(movieItem.getMovieDetail().getThumbURL()).into(moviePoster);
                        youTubePlayerView.setVisibility(View.GONE);
                    }else {
                        getLifecycle().addObserver(youTubePlayerView);

                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                String videoId = Youtube.getInstance().extractVideoId(trailerUrl);
                                youTubePlayer.loadVideo(videoId, 0);
                            }
                        });
                        moviePoster.setVisibility(View.GONE);
                    }
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

                    String actor = "";
                    List<String> actors = movieItem.getMovieDetail().getActor();
                    for (int i = 0; i < actors.size(); i++) {
                        if (i != actors.size()-1){
                            actor += actors.get(i) +", ";
                        }else {
                            actor += actors.get(i);
                        }
                    }
                    movieActor.setText(actor);


//                    movieURL = movieItem.getEpisodes().get(0).getEpisodeItem().get(0).getLinkM3U8();
//                    Log.d(TAG, "Test 1: "+movieURL);


//                    player = new ExoPlayer.Builder(MovieDetailActivity.this).build();
//                    playerView.setPlayer(player);
//
//                    Uri videoUri = Uri.parse(movieItem.getEpisodes().get(0).getEpisodeItem().get(0).getLinkM3U8());
//
//                    DefaultHttpDataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory().setUserAgent(Util.getUserAgent(MovieDetailActivity.this, "MovieApp"));
//                    MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUri));
//                    player.setMediaSource(mediaSource);
//                    player.prepare();
//                    player.play();

//                    if (mExoPlayerView == null) {
//                        mExoPlayerView = findViewById(R.id.exoplayer);
//                        initFullscreenDialog();
//                        initFullscreenButton();
//
//                        Log.d(TAG, "Test 2: "+movieURL);
//                        String streamUrl = movieURL;
//                        Log.d(TAG, "Test 3: "+streamUrl);
//                        String userAgent = Util.getUserAgent(MovieDetailActivity.this, getApplicationInfo().packageName);
//
//                        DefaultHttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSource.Factory().setUserAgent(userAgent);
//                        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(MovieDetailActivity.this, httpDataSourceFactory);
//                        Uri daUri = Uri.parse(streamUrl);
//
//                        mVideoSource = new MediaItem.Builder().setUri(daUri).setMimeType(MimeTypes.APPLICATION_M3U8).build();
//                    }
//
//                    initExoPlayer();
//
//                    if (mExoPlayerFullscreen) {
//                        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
//                        mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_fullscreen_shrink));
//                        mFullScreenDialog.show();
//                    }


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

        Button playButton;
        playButton = findViewById(R.id.play_button);

//        View playButton;
//        playButton = findViewById(R.id.play_button);
//        playButton.setOnClickListener(v -> {
//        Button playButton = findViewById(R.id.play_button);

        playButton.setOnClickListener(v -> {
//            if (currentMovie != null) {
//                // Lưu thông tin phim vào lịch sử xem
                saveToHistory(slug);
                // Code to start video playback or navigate to video player
                Toast.makeText(MovieDetailActivity.this, "Play button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MovieDetailActivity.this, Activity_watching_movie.class);
                bundle.putString("slug", slug);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
//            }else {
//                Log.d(TAG, "NULL");
//            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void saveToHistory(String slug) {
        // Lấy SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("movie_history", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();

        // Chuyển đổi MovieDetail thành JSON
//        Gson gson = new Gson();
//        String movieDetailJson = gson.toJson(movieDetail);

        // Lưu JSON vào SharedPreferences
//        if (sharedPreferences.contains(movieDetail.getSlug())){
//            editor.remove(movieDetail.getSlug());
//
//            editor.putString(movieDetail.getSlug(), movieDetailJson);
//        }else {
//        }
//        editor.putString(movieDetail.getSlug(), movieDetailJson);
//        editor.apply();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserDao.getInstance().insertUserHistoryMovie(user, slug);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//
//        outState.putInt(STATE_RESUME_WINDOW, mResumeWindow);
//        outState.putLong(STATE_RESUME_POSITION, mResumePosition);
//        outState.putBoolean(STATE_PLAYER_FULLSCREEN, mExoPlayerFullscreen);
//
//        super.onSaveInstanceState(outState);
//    }

//    private void initFullscreenDialog() {
//
//        mFullScreenDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
//            public void onBackPressed() {
//                if (mExoPlayerFullscreen)
//                    closeFullscreenDialog();
//                super.onBackPressed();
//            }
//        };
//    }
//
//    private void openFullscreenDialog() {
//
//        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
//        mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_fullscreen_shrink));
//        mExoPlayerFullscreen = true;
//        mFullScreenDialog.show();
//    }
//
//    private void closeFullscreenDialog() {
//
//        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
//        ((FrameLayout) findViewById(R.id.main_media_frame)).addView(mExoPlayerView);
//        mExoPlayerFullscreen = false;
//        mFullScreenDialog.dismiss();
//        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_fullscreen_expand));
//    }
//
//    private void initFullscreenButton() {
//
//        PlayerControlView controlView = mExoPlayerView.findViewById(R.id.exo_controller);
//        mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
//        mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
//        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mExoPlayerFullscreen)
//                    openFullscreenDialog();
//                else
//                    closeFullscreenDialog();
//            }
//        });
//    }
//
//    private void initExoPlayer() {
//
//
//        TrackSelector trackSelector = new DefaultTrackSelector(MovieDetailActivity.this, new AdaptiveTrackSelection.Factory());
//
//        mExoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).setLoadControl(new DefaultLoadControl()).build();
//
//        mExoPlayerView.setPlayer(mExoPlayer);
//
//
//        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;
//
//        if (haveResumePosition) {
//            mExoPlayer.seekTo(mResumeWindow, mResumePosition);
//        }
//
//        mExoPlayer.setMediaItem(mVideoSource);
//        mExoPlayer.prepare();
//        mExoPlayer.setPlayWhenReady(true);
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (mExoPlayerView == null) {
//            mExoPlayerView = findViewById(R.id.exoplayer);
//            initFullscreenDialog();
//            initFullscreenButton();
//
//            Log.d(TAG, "Test 2: "+movieURL);
//            String streamUrl = movieURL;
//            Log.d(TAG, "Test 3: "+streamUrl);
//            String userAgent = Util.getUserAgent(this, getApplicationInfo().packageName);
//
//            DefaultHttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSource.Factory().setUserAgent(userAgent);
//            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, httpDataSourceFactory);
//            Uri daUri = Uri.parse(streamUrl);
//
//            mVideoSource = new MediaItem.Builder().setUri(daUri).setMimeType(MimeTypes.APPLICATION_M3U8).build();
//        }
//
//        initExoPlayer();
//
//        if (mExoPlayerFullscreen) {
//            ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
//            mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_fullscreen_shrink));
//            mFullScreenDialog.show();
//        }
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (mExoPlayerView != null && mExoPlayer != null) {
//            mResumeWindow = mExoPlayer.getCurrentWindowIndex();
//            mResumePosition = Math.max(0, mExoPlayer.getContentPosition());
//            mExoPlayer.release();
//            mExoPlayer = null;
//        }
//
//        if (mFullScreenDialog != null)
//            mFullScreenDialog.dismiss();
//    }


}

