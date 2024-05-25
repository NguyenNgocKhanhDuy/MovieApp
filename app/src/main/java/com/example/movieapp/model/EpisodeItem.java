package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class EpisodeItem {
    private String name;
    private String slug;
    @SerializedName("filename")
    private String fileName;
    @SerializedName("link_embed")
    private String linkEmbed;
    @SerializedName("link_m3u8")
    private String linkM3U8;

    public EpisodeItem() {
    }

    public EpisodeItem(String name, String slug, String fileName, String linkEmbed, String linkM3U8) {
        this.name = name;
        this.slug = slug;
        this.fileName = fileName;
        this.linkEmbed = linkEmbed;
        this.linkM3U8 = linkM3U8;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLinkEmbed() {
        return linkEmbed;
    }

    public void setLinkEmbed(String linkEmbed) {
        this.linkEmbed = linkEmbed;
    }

    public String getLinkM3U8() {
        return linkM3U8;
    }

    public void setLinkM3U8(String linkM3U8) {
        this.linkM3U8 = linkM3U8;
    }

    @Override
    public String toString() {
        return "EpisodeItem{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", fileName='" + fileName + '\'' +
                ", linkEmbed='" + linkEmbed + '\'' +
                ", linkM3U8='" + linkM3U8 + '\'' +
                '}';
    }
}
