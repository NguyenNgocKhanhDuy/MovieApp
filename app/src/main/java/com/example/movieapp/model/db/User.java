package com.example.movieapp.model.db;

public class User {
    private String username;
    private String email;
    private String pass;
    private String slugMovieWatch;

    public User() {
    }

    public User(String username, String email, String pass, String slugMovieWatch) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.slugMovieWatch = slugMovieWatch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSlugMovieWatch() {
        return slugMovieWatch;
    }

    public void setSlugMovieWatch(String slugMovieWatch) {
        this.slugMovieWatch = slugMovieWatch;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", slugMovieWatch='" + slugMovieWatch + '\'' +
                '}';
    }
}
