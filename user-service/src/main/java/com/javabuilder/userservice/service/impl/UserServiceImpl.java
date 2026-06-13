/*
 * UserServiceImpl.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service.impl;

import com.javabuilder.userservice.dto.request.CreateUserRequest;
import com.javabuilder.userservice.dto.response.CreateUserResponse;
import com.javabuilder.userservice.dto.response.UserDetailResponse;
import com.javabuilder.userservice.entity.Role;
import com.javabuilder.userservice.entity.User;
import com.javabuilder.userservice.exception.ErrorCode;
import com.javabuilder.userservice.exception.UserServiceException;
import com.javabuilder.userservice.mapper.UserMapper;
import com.javabuilder.userservice.repository.UserRepository;
import com.javabuilder.userservice.service.RoleService;
import com.javabuilder.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl.java
 *
 * @author Nguyen
 */
@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUser(CreateUserRequest request) {
        // 1. Convert DTO sang Entity
        User user = userMapper.toUser(request);

        // 2. Ma hoa password
        user.setPassword(passwordEncoder.encode(request.password()));

        // 3. Tao hoac lay role Customer
        Role role = roleService.createRole(com.javabuilder.userservice.enums.Role.CUSTOMER.name());

        // 4. Gan role cho user
        user.addRole(role);

        // 5. Luu user vao db
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) { // ngoai le xay ra khi mail trung
            log.error("User already exists");
            throw new UserServiceException(ErrorCode.USER_ALREADY_EXISTS);
        }

        // 6. Convert Entity sang ResponseDto
        return userMapper.toCreateUserResponse(user);
    }

    @Override
    public UserDetailResponse myInfo(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserServiceException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserDetailResponse(user);
    }


}
