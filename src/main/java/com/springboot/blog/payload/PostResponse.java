package com.springboot.blog.payload;

import java.util.List;

public class PostResponse {
    private List<PostDto> items;
    private int pageNumber;
    private int pageSize;
    private long totalPages;
    private long totalItems;
    private boolean isLastPage;

    public void setItems(List<PostDto> items) {
        this.items = items;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<PostDto> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public boolean isLastPage() {
        return isLastPage;
    }
}
