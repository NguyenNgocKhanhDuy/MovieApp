package com.example.movieapp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Youtube {
    private static Youtube instance;

    public Youtube() {
    }

    public static Youtube getInstance() {
        if (instance == null) instance = new Youtube();
        return instance;
    }

    public String extractVideoId(String youtubeUrl) {
        String videoId = null;
        String regex = "^(?:https?:\\/\\/)?(?:www\\.)?(?:youtube\\.com\\/.*v=|youtu.be\\/)([a-zA-Z0-9_-]{11}).*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(youtubeUrl);
        if (matcher.matches()) {
            videoId = matcher.group(1);
        }
        return videoId;
    }
}
