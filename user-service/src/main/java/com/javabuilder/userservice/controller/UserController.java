/*
 * UserController.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.controller;

import com.javabuilder.userservice.dto.request.CreateUserRequest;
import com.javabuilder.userservice.dto.response.ApiResponse;
import com.javabuilder.userservice.dto.response.CreateUserResponse;
import com.javabuilder.userservice.dto.response.UserDetailResponse;
import com.javabuilder.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

/**
 * UserController.java
 *
 * @author Nguyen
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    ApiResponse<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse data = userService.createUser(request);
        return ApiResponse.<CreateUserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("User Created Successfully")
                .data(data)
                .build();
    }

    @PreAuthorize("hasAnyRole('SELLER', 'CUSTOMER')")
    @GetMapping("/me")
    ApiResponse<UserDetailResponse> getMyInfo(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        UserDetailResponse data = userService.myInfo(userId);

        return ApiResponse.<UserDetailResponse>builder()
                .code(HttpStatus.OK.value())
                .message("User Info Retrieved Successfully")
                .data(data)
                .build();
    }
}
