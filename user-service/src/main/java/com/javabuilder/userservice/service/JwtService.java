/*
 * JwtService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service;

import java.util.Set;

/**
 * JwtService.java
 *
 * @author Nguyen
 */
public interface JwtService {
    String generateAccessToken(String userId, Set<String> roles);
    String generateRefreshToken(String userId);
}
