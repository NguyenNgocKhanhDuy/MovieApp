package com.example.movieapp.model;

public class ListMovieStatusString {
    private String status;
    private DataMovieOfType data;

    public ListMovieStatusString() {
    }

    public ListMovieStatusString(String status, DataMovieOfType data) {
        this.status = status;
        this.data = data;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataMovieOfType getData() {
        return data;
    }

    public void setData(DataMovieOfType data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListMovieOfType{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
