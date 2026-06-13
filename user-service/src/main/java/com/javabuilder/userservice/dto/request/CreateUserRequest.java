/*
 * CreateUserRequest.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * CreateUserRequest.java
 *
 * @author Nguyen
 */
public record CreateUserRequest(
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Length(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
