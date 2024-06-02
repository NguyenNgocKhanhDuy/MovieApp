package com.example.movieapp;

import com.example.movieapp.model.db.User;

import java.util.List;

public interface FirebaseCallback {
    void onDataReceived(User user);
    void onDataReceivedList(List<User> user);
}
