/*
 * AuthenticationServiceImpl.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service.impl;

import com.javabuilder.userservice.dto.request.LoginRequest;
import com.javabuilder.userservice.dto.response.LoginResponse;
import com.javabuilder.userservice.entity.User;
import com.javabuilder.userservice.exception.ErrorCode;
import com.javabuilder.userservice.exception.UserServiceException;
import com.javabuilder.userservice.service.AuthenticationService;
import com.javabuilder.userservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuthenticationServiceImpl.java
 *
 * @author Nguyen
 */
@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        User user =  (User) authenticate.getPrincipal();
        if (user == null) {
            throw new UserServiceException(ErrorCode.USER_NOT_FOUND);
        }

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        String accessToken = jwtService.generateAccessToken(user.getId(), roles);
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .roles(roles)
                .build();
    }
}
