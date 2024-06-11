package com.example.movieapp.model.db;

public class ImageMovie {
    private String name;
    private String url;
    private long timeStamp;

    public ImageMovie() {
    }

    public ImageMovie(String name, String url, long timeStamp) {
        this.name = name;
        this.url = url;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ImageMovie{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
