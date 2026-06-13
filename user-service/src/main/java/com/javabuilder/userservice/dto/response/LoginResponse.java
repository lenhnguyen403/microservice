/*
 * LoginResponse.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.dto.response;

import lombok.Builder;

import java.util.Set;

/**
 * LoginResponse.java
 *
 * @author Nguyen
 */
@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken,
        Set<String> roles
) {
}
