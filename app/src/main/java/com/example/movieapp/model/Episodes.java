package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Episodes {
    @SerializedName("server_data")
    private List<EpisodeItem> episodeItem;

    public Episodes(List<EpisodeItem> episodeItem) {
        this.episodeItem = episodeItem;
    }

    public List<EpisodeItem> getEpisodeItem() {
        return episodeItem;
    }

    public void setEpisodeItem(List<EpisodeItem> episodeItem) {
        this.episodeItem = episodeItem;
    }

    @Override
    public String toString() {
        return "Episodes{" +
                "episodeItem=" + episodeItem +
                '}';
    }
}
