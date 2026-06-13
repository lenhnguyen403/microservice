/*
 * UserServiceException.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.exception;

import lombok.Getter;

/**
 * UserServiceException.java
 *
 * @author Nguyen
 */
@Getter
public class UserServiceException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
