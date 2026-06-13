/*
 * GlobalExceptionHandler.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

/**
 * GlobalExceptionHandler.java
 *
 * @author Nguyen
 */
@RestControllerAdvice
@Slf4j(topic = "GLOBAL-EXCEPTION")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest request) {
        log.error("Exception occurred: ", e);
        ErrorResponse response = buildErrorCodeResponse(ErrorCode.INTERNAL_ERROR, request);

        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponse> handleUserServiceException(UserServiceException e, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .code(e.getErrorCode().getCode())
                .error(e.getErrorCode().getHttpStatus().getReasonPhrase())
                .message(e.getMessage())
                .timestamp(new Date().getTime())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(e.getErrorCode().getCode())
                .body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errors =
                fieldErrors.stream().map(FieldError::getDefaultMessage).toList();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date().getTime())
                .code(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(errors.size() > 1 ? String.valueOf(errors) : errors.getFirst())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    private ErrorResponse buildErrorCodeResponse(ErrorCode errorCode, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date().getTime())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .error(errorCode.getHttpStatus().getReasonPhrase())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
    }
}
