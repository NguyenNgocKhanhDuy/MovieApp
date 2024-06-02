package com.example.movieapp.model.api;

public class PageInfo {
    private int totalItems;
    private int totalItemsPerPage;
    private int currentPage;
    private int totalPages;

    public PageInfo() {
    }

    public PageInfo(int totalItems, int totalItemsPerPage, int currentPage, int totalPages) {
        this.totalItems = totalItems;
        this.totalItemsPerPage = totalItemsPerPage;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalItemsPerPage() {
        return totalItemsPerPage;
    }

    public void setTotalItemsPerPage(int totalItemsPerPage) {
        this.totalItemsPerPage = totalItemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "totalItems=" + totalItems +
                ", totalItemsPerPage=" + totalItemsPerPage +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                '}';
    }
}
