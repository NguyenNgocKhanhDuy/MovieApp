package com.example.movieapp.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMovieOfType {
    private String titlePage;
    @SerializedName("items")
    private List<MovieDetail> movieDetails;
    private ParamOfData params;

    public DataMovieOfType() {
    }

    public DataMovieOfType(String titlePage, List<MovieDetail> movieDetails, ParamOfData params) {
        this.titlePage = titlePage;
        this.movieDetails = movieDetails;
        this.params = params;
    }

    public String getTitlePage() {
        return titlePage;
    }

    public void setTitlePage(String titlePage) {
        this.titlePage = titlePage;
    }

    public List<MovieDetail> getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(List<MovieDetail> movieDetails) {
        this.movieDetails = movieDetails;
    }

    public ParamOfData getParams() {
        return params;
    }

    public void setParams(ParamOfData params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "DataMovieOfType{" +
                "titlePage='" + titlePage + '\'' +
                ", movieDetails=" + movieDetails +
                ", params=" + params +
                '}';
    }
}
