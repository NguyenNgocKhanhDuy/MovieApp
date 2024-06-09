package com.example.movieapp.dao;

import com.example.movieapp.model.db.User;

import java.util.List;

public interface FirebaseCallback {
    void onDataReceived(User user);
    void onDataReceivedList(List<User> user);

    void onDataReceivedListMovie(List<String> slugs);
}
