/*
 * ApiResponse.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * ApiResponse.java
 *
 * @author Nguyen
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Chi serialize cac field k null
public record ApiResponse<T> (
    int code,
    String message,
    T data
){}
