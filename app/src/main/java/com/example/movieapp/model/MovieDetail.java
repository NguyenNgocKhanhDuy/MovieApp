package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {
    private Time created;
    private Time modified;

    @SerializedName("_id")
    private String id;
    private String name;
    private String slug;
    @SerializedName("origin_name")
    private String originName;
    private String content;
    private String type;
    private String status;
    @SerializedName("poster_url")
    private String posterURL;
    @SerializedName("thumb_url")
    private String thumbURL;
    @SerializedName("is_copyright")
    private boolean isCopyRight;
    @SerializedName("sub_docquyen")
    private boolean subDocQuyen;
    @SerializedName("chieurap")
    private boolean chieuRap;
    @SerializedName("trailer_url")
    private String trailerURL;
    private String time;
    @SerializedName("episode_current")
    private String episodeCurrent;
    @SerializedName("episode_total")
    private int episodeTotal;
    private String quality;
    private String lang;
    private String notify;
    private int year;
    private int view;
    private List<String> actor;
    private List<String> director;
    private List<Category> category;
    private List<Country> country;

    public MovieDetail() {
    }

    public MovieDetail(Time created, Time modified, String id, String name, String slug, String originName, String content, String type, String status, String posterURL, String thumbURL, boolean isCopyRight, boolean subDocQuyen, boolean chieuRap, String trailerURL, String time, String episodeCurrent, int episodeTotal, String quality, String lang, String notify, int year, int view, List<String> actor, List<String> director, List<Category> category, List<Country> country) {
        this.created = created;
        this.modified = modified;
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.originName = originName;
        this.content = content;
        this.type = type;
        this.status = status;
        this.posterURL = posterURL;
        this.thumbURL = thumbURL;
        this.isCopyRight = isCopyRight;
        this.subDocQuyen = subDocQuyen;
        this.chieuRap = chieuRap;
        this.trailerURL = trailerURL;
        this.time = time;
        this.episodeCurrent = episodeCurrent;
        this.episodeTotal = episodeTotal;
        this.quality = quality;
        this.lang = lang;
        this.notify = notify;
        this.year = year;
        this.view = view;
        this.actor = actor;
        this.director = director;
        this.category = category;
        this.country = country;
    }

    public Time getCreated() {
        return created;
    }

    public void setCreated(Time created) {
        this.created = created;
    }

    public Time getModified() {
        return modified;
    }

    public void setModified(Time modified) {
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public boolean isCopyRight() {
        return isCopyRight;
    }

    public void setCopyRight(boolean copyRight) {
        isCopyRight = copyRight;
    }

    public boolean isSubDocQuyen() {
        return subDocQuyen;
    }

    public void setSubDocQuyen(boolean subDocQuyen) {
        this.subDocQuyen = subDocQuyen;
    }

    public boolean isChieuRap() {
        return chieuRap;
    }

    public void setChieuRap(boolean chieuRap) {
        this.chieuRap = chieuRap;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEpisodeCurrent() {
        return episodeCurrent;
    }

    public void setEpisodeCurrent(String episodeCurrent) {
        this.episodeCurrent = episodeCurrent;
    }

    public int getEpisodeTotal() {
        return episodeTotal;
    }

    public void setEpisodeTotal(int episodeTotal) {
        this.episodeTotal = episodeTotal;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
                "created=" + created +
                ", modified=" + modified +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", originName='" + originName + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", thumbURL='" + thumbURL + '\'' +
                ", isCopyRight=" + isCopyRight +
                ", subDocQuyen=" + subDocQuyen +
                ", chieuRap=" + chieuRap +
                ", trailerURL='" + trailerURL + '\'' +
                ", time='" + time + '\'' +
                ", episodeCurrent='" + episodeCurrent + '\'' +
                ", episodeTotal=" + episodeTotal +
                ", quality='" + quality + '\'' +
                ", lang='" + lang + '\'' +
                ", notify='" + notify + '\'' +
                ", year=" + year +
                ", view=" + view +
                ", actor=" + actor +
                ", director=" + director +
                ", category=" + category +
                ", country=" + country +
                '}';
    }
}
