package com.example.api_web_ban_hang.filter;

public class PaginationFilter {
    public final int PAGE_SIZE_DEFAULT = 15;
    public int current_page;
    public int page_size;
    public PaginationFilter(int currentPage, int pageSize) {
        this.current_page = Math.max(currentPage, 1); // current_page nhỏ nhất là 1
        this.page_size = Math.min(pageSize, PAGE_SIZE_DEFAULT); // page_size lớn nhất là PAGE_SIZE_DEFAULT
    }
}
