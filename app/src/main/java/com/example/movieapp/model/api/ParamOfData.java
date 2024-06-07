package com.example.movieapp.model.api;

import com.google.gson.annotations.SerializedName;

public class ParamOfData {
    private String sortType;
    @SerializedName("pagination")
    private PageInfo pageInfo;

    public ParamOfData() {
    }

    public ParamOfData(String sortType, PageInfo pageInfo) {
        this.sortType = sortType;
        this.pageInfo = pageInfo;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "ParamOfData{" +
                "sortType='" + sortType + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
