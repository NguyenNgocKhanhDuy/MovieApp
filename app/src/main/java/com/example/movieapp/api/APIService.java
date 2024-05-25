package com.example.movieapp.api;

import com.example.movieapp.model.ListMovieStatusString;
import com.example.movieapp.model.ListMovieStatusBoolean;
import com.example.movieapp.model.MovieItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("https://phimapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

//    https://phimapi.com/danh-sach/phim-moi-cap-nhat?page=1
    @GET("danh-sach/phim-moi-cap-nhat")
    Call<ListMovieStatusBoolean> callNewUpdateMovie(@Query("page") int page);

//    https://phimapi.com/phim/khi-anh-chay-ve-phia-em
    @GET("phim/{slug}")
    Call<MovieItem> callMovieDetail(@Path("slug") String slug);

//    https://phimapi.com/v1/api/danh-sach/phim-le?page=1
//    https://phimapi.com/v1/api/danh-sach/phim-bo?page=1
//    https://phimapi.com/v1/api/danh-sach/hoat-hinh?page=1
//    https://phimapi.com/v1/api/danh-sach/tv-shows?page=1
    @GET("v1/api/danh-sach/{type}")
    Call<ListMovieStatusString> callMovieOfType(@Path("type") String type, @Query("page") int page);


//    https://phimapi.com/v1/api/tim-kiem?keyword={Từ khóa}&limit={number}
    @GET("v1/api/tim-kiem")
    Call<ListMovieStatusString> callMovieBySearch(@Query("keyword") String keyWord, @Query("limit") int limit);
}
