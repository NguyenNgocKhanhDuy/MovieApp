package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("modified")
    private Time time;
    @SerializedName("_id")
    private String id;
    private String name;
    private String slug;
    @SerializedName("origin_name")
    private String originName;
    @SerializedName("poster_url")
    private String posterURL;
    @SerializedName("thumb_url")
    private String thumbURL;
    private int year;

    public Movie() {
    }

    public Movie(Time time, String id, String name, String slug, String originName, String posterURL, String thumbURL, int year) {
        this.time = time;
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.originName = originName;
        this.posterURL = posterURL;
        this.thumbURL = thumbURL;
        this.year = year;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "time=" + time +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", originName='" + originName + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", thumbURL='" + thumbURL + '\'' +
                ", year=" + year +
                '}';
    }
}
