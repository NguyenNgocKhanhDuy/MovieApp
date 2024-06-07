package com.example.movieapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.api.APIService;
import com.example.movieapp.model.api.Category;
import com.example.movieapp.model.api.MovieItem;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
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
import com.google.android.flexbox.FlexboxLayout;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_watching_movie extends AppCompatActivity {
    private FlexboxLayout flexboxLayout;
    private int numberOfButtons;
    private TextView tvEpisode, tvName;
    private final String STATE_RESUME_WINDOW = "resumeWindow";
    private final String STATE_RESUME_POSITION = "resumePosition";
    private final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";

    private PlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private MediaItem mVideoSource;
    private boolean mExoPlayerFullscreen = false;
    private FrameLayout mFullScreenButton;
    private ImageView mFullScreenIcon;
    private Dialog mFullScreenDialog;

    private int mResumeWindow;
    private long mResumePosition;
    private String movieURL;
    private int episode;
    private String slug;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching_movie);

        if (savedInstanceState != null) {
            mResumeWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW);
            mResumePosition = savedInstanceState.getLong(STATE_RESUME_POSITION);
            mExoPlayerFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN);
        }

         flexboxLayout = findViewById(R.id.flexbox_button_container);
        tvEpisode = findViewById(R.id.episodeCurrent);
        tvName = findViewById(R.id.name);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        slug = bundle.getString("slug");
        episode = 0;
        tvEpisode.setText("Tập "+(episode+1));

        APIService.apiService.callMovieDetail(slug).enqueue(new Callback<MovieItem>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                MovieItem movieItem = response.body();
                Log.d(TAG, "Movie: "+movieItem.getMovieDetail());
                if (movieItem != null && movieItem.isStatus()){
                    tvName.setText(movieItem.getMovieDetail().getName());
                    numberOfButtons = movieItem.getEpisodes().get(0).getEpisodeItem().size();
                    createButtons(numberOfButtons);
                }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable throwable) {
                Log.d(TAG, "FAIL");
            }
        });
        callAPI(slug, episode);

    }

    private void callAPI(String slug, int episode) {
        APIService.apiService.callMovieDetail(slug).enqueue(new Callback<MovieItem>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                MovieItem movieItem = response.body();
                Log.d(TAG, "Movie: "+movieItem.getMovieDetail());
                if (movieItem != null && movieItem.isStatus()){
                    movieURL = movieItem.getEpisodes().get(0).getEpisodeItem().get(episode).getLinkM3U8();
                    tvEpisode.setText(movieItem.getEpisodes().get(0).getEpisodeItem().get(episode).getName());
                    Log.d(TAG, "EPOSIDE: "+episode+"URL: "+movieURL);

                    if (mExoPlayer == null) {
                        initializePlayer();
                    } else {
                        updateMediaItem(movieURL);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable throwable) {
                Log.d(TAG, "FAIL");
            }
        });
    }

    private void updateMediaItem(String newUrl) {
        mExoPlayer.stop();
        mExoPlayer.clearMediaItems();

        MediaItem newMediaItem = new MediaItem.Builder().setUri(Uri.parse(newUrl)).setMimeType(MimeTypes.APPLICATION_M3U8).build();

        mExoPlayer.setMediaItem(newMediaItem);
        mExoPlayer.prepare();
        mExoPlayer.setPlayWhenReady(true);
    }

    private void initializePlayer() {
        if (mExoPlayerView == null) {
            mExoPlayerView = findViewById(R.id.exoplayer);
            initFullscreenDialog();
            initFullscreenButton();

            String streamUrl = movieURL;
            String userAgent = Util.getUserAgent(Activity_watching_movie.this, getApplicationInfo().packageName);

            DefaultHttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSource.Factory().setUserAgent(userAgent);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(Activity_watching_movie.this, httpDataSourceFactory);
            Uri daUri = Uri.parse(streamUrl);

            mVideoSource = new MediaItem.Builder().setUri(daUri).setMimeType(MimeTypes.APPLICATION_M3U8).build();
        }

        initExoPlayer();

        if (mExoPlayerFullscreen) {
            ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
            mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(Activity_watching_movie.this, R.drawable.ic_fullscreen_shrink));
            mFullScreenDialog.show();
        }
    }


    private void createButtons(int numberOfButtons) {
        for (int i = 0; i < numberOfButtons; i++) {
            Button button = new Button(this);

            // Thiết lập LayoutParams cho FlexboxLayout
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            // Thiết lập margin
            params.setMargins(
                    (int) getResources().getDimension(R.dimen.margin_left), // Left margin
                    0, // Top margin
                    0, // Right margin
                    (int) getResources().getDimension(R.dimen.margin_bottom) // Bottom margin
            );

            // Thiết lập flexBasisPercent
            params.setFlexBasisPercent(0.25f); // 25%

            // Thiết lập LayoutParams cho Button
            button.setLayoutParams(params);

            // Thiết lập các thuộc tính khác cho Button
            button.setText("Tập " + (i + 1));
            button.setTag(i);
            button.setTextColor(getResources().getColor(R.color.background));
            button.setBackgroundResource(R.color.Item_checked);

            // Thiết lập sự kiện OnClickListener
            button.setOnClickListener(view -> {
                Button clickedButton = (Button) view;
                String buttonText = clickedButton.getText().toString();
                Log.d(TAG, "BTN : "+buttonText);
                int index = (int) clickedButton.getTag();
                episode = index;
//                initializePlayer();
                callAPI(slug, episode);
                Log.d(TAG, "BTN ID : "+ index);
            });

            // Thêm Button vào FlexboxLayout
            flexboxLayout.addView(button);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_RESUME_WINDOW, mResumeWindow);
        outState.putLong(STATE_RESUME_POSITION, mResumePosition);
        outState.putBoolean(STATE_PLAYER_FULLSCREEN, mExoPlayerFullscreen);

        super.onSaveInstanceState(outState);
    }

    private void initFullscreenDialog() {

        mFullScreenDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    private void openFullscreenDialog() {

        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(Activity_watching_movie.this, R.drawable.ic_fullscreen_shrink));
        mExoPlayerFullscreen = true;
        mFullScreenDialog.show();
    }

    private void closeFullscreenDialog() {

        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        ((FrameLayout) findViewById(R.id.main_media_frame)).addView(mExoPlayerView);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(Activity_watching_movie.this, R.drawable.ic_fullscreen_expand));
    }

    private void initFullscreenButton() {

        PlayerControlView controlView = mExoPlayerView.findViewById(R.id.exo_controller);
        mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });
    }

    private void initExoPlayer() {


        TrackSelector trackSelector = new DefaultTrackSelector(Activity_watching_movie.this, new AdaptiveTrackSelection.Factory());

        mExoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).setLoadControl(new DefaultLoadControl()).build();

        mExoPlayerView.setPlayer(mExoPlayer);


        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;

        if (haveResumePosition) {
            mExoPlayer.seekTo(mResumeWindow, mResumePosition);
        }

        mExoPlayer.setMediaItem(mVideoSource);
        mExoPlayer.prepare();
        mExoPlayer.setPlayWhenReady(true);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mExoPlayerView == null) {
//            mExoPlayerView = findViewById(R.id.exoplayer);
//            initFullscreenDialog();
//            initFullscreenButton();
//        }
//
//        initializePlayer();
//    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mExoPlayerView != null && mExoPlayer != null) {
            mResumeWindow = mExoPlayer.getCurrentWindowIndex();
            mResumePosition = Math.max(0, mExoPlayer.getContentPosition());
            mExoPlayer.release();
            mExoPlayer = null;
        }

        if (mFullScreenDialog != null)
            mFullScreenDialog.dismiss();
    }
}