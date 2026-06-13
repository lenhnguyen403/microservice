/*
 * ErrorResponse.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.exception;

import lombok.Builder;

/**
 * ErrorResponse.java
 *
 * @author Nguyen
 */
@Builder
public record ErrorResponse(
        int code,
        String message,
        String error,
        String path,
        long timestamp
) {
}
