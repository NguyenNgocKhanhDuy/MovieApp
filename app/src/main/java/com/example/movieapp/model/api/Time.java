package com.example.movieapp.model.api;

import java.time.LocalDateTime;

public class Time {
    private String time;

    public Time() {
    }

    public Time(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time{" +
                "time='" + time + '\'' +
                '}';
    }
}
