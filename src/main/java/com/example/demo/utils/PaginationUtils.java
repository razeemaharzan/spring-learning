package com.example.demo.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_PAGE = 0;
    private static final String DEFAULT_COLUMN = "id";
    private static final Sort DEFAULT_SORT = Sort.by(DEFAULT_COLUMN).ascending();

    public static Pageable pageable(int page) {
        PageRequest request = PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, DEFAULT_SORT);
        return request;
    }

    public static Pageable pageable(int page, int size, Sort.Direction direction, String... sortProperties) {
        PageRequest request = PageRequest.of(page, size, direction, sortProperties);
        return request;
    }
}
