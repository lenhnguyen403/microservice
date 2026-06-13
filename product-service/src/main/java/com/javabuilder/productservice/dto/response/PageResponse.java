/*
 * PageResponse.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.productservice.dto.response;

import java.util.List;

/**
 * PageResponse.java
 *
 * @author Nguyen
 */
public record PageResponse<T>(
        int currentPage,           // Trang hiện tại (1, 2, 3...)
        int pageSize,              // Số items mỗi trang (20, 50, 100...)
        long totalElements,        // Tổng số items trong DB (sau khi filter)
        int totalPages,            // Tổng số trang (totalElements / pageSize)
        List<T> content            // Danh sách data của trang hiện tại
) {
}
