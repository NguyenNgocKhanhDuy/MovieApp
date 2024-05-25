package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tv1, tv2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv1 = findViewById(R.id.tvItem);
        tv2 = findViewById(R.id.tvPage);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallAPI();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void clickCallAPI() {
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
//                    tv1.setText(movieItem.getMovieDetail().toString());
//                }

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
}