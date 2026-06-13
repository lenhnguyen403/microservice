/*
 * AuthenticationService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service;

import com.javabuilder.userservice.dto.request.LoginRequest;
import com.javabuilder.userservice.dto.response.LoginResponse;

/**
 * AuthenticationService.java
 *
 * @author Nguyen
 */
public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
}
