/*
 * UserService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service;

import com.javabuilder.userservice.dto.request.CreateUserRequest;
import com.javabuilder.userservice.dto.response.CreateUserResponse;
import com.javabuilder.userservice.dto.response.UserDetailResponse;

/**
 * UserService.java
 *
 * @author Nguyen
 */
public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    UserDetailResponse myInfo(String userId);
}
