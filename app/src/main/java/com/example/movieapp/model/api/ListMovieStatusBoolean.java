package com.example.movieapp.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMovieStatusBoolean {
    private boolean status;
    @SerializedName("items")
    private List<MovieDetail> movie;
    @SerializedName("pagination")
    private PageInfo pageInfo;

    public ListMovieStatusBoolean() {
    }

    public ListMovieStatusBoolean(boolean status, List<MovieDetail> movie, PageInfo pageInfo) {
        this.status = status;
        this.movie = movie;
        this.pageInfo = pageInfo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<MovieDetail> getMovie() {
        return movie;
    }

    public void setMovie(List<MovieDetail> movie) {
        this.movie = movie;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "ListMoviePerPage{" +
                "status=" + status +
                ", movie=" + movie +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
