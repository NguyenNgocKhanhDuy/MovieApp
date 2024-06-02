package com.example.movieapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.events.Event;

public class BaseViewModel extends ViewModel {

    protected final MutableLiveData<Event<String>> mSnackBarText = new MutableLiveData<>();
    private final LiveData<Event<String>> snackBarText = mSnackBarText;

    public LiveData<Event<String>> getSnackBarText() {
        return snackBarText;
    }
}
