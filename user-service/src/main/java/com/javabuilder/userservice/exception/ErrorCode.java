/*
 * ErrorCode.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode.java
 *
 * @author Nguyen
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR(500, "Unexpected error occurred while processing request in backend service", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(404, "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(400, "User already exists", HttpStatus.BAD_REQUEST),
    TOKEN_GENERATION_FAILED(400, "Token generation failed", HttpStatus.BAD_REQUEST ),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

}
