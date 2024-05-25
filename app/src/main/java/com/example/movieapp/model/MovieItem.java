package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieItem {
    private boolean status;
    @SerializedName("movie")
    private MovieDetail movieDetail;
    private List<Episodes> episodes;

    public MovieItem() {
    }

    public MovieItem(boolean status, MovieDetail movieDetail, List<Episodes> episodes) {
        this.status = status;
        this.movieDetail = movieDetail;
        this.episodes = episodes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MovieDetail getMovieDetail() {
        return movieDetail;
    }

    public void setMovieDetail(MovieDetail movieDetail) {
        this.movieDetail = movieDetail;
    }

    public List<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episodes> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "status=" + status +
                ", movieDetail=" + movieDetail +
                ", episodes=" + episodes +
                '}';
    }
}
