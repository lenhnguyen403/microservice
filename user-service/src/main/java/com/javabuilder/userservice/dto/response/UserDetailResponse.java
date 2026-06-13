/*
 * UserDetailResponse.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.javabuilder.userservice.enums.Gender;
import com.javabuilder.userservice.enums.UserStatus;
import lombok.Builder;

import java.time.LocalDate;

/**
 * UserDetailResponse.java
 *
 * @author Nguyen
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDetailResponse(
        String email,
        String firstName,
        String lastName,
        String phone,
        String avatar,
        Gender gender,
        LocalDate birthDate,
        UserStatus userStatus
) {
}
