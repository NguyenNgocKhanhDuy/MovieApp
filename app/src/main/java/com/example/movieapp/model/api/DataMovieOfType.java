package com.example.movieapp.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMovieOfType {
    private String titlePage;
    @SerializedName("items")
    private List<MovieDetail> movieDetails;
    private ParamOfData params;
    @SerializedName("APP_DOMAIN_CDN_IMAGE")
    private String imageDomain;

    public DataMovieOfType() {
    }

    public DataMovieOfType(String titlePage, List<MovieDetail> movieDetails, ParamOfData params, String imageDomain) {
        this.titlePage = titlePage;
        this.movieDetails = movieDetails;
        this.params = params;
        this.imageDomain = imageDomain;
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

    public String getImageDomain() {
        return imageDomain;
    }

    public void setImageDomain(String imageDomain) {
        this.imageDomain = imageDomain;
    }

    @Override
    public String toString() {
        return "DataMovieOfType{" +
                "titlePage='" + titlePage + '\'' +
                ", movieDetails=" + movieDetails +
                ", params=" + params +
                ", imageDomain='" + imageDomain + '\'' +
                '}';
    }
}
