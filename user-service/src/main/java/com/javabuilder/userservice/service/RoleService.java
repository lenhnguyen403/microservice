/*
 * RoleService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.service;

import com.javabuilder.userservice.entity.Role;

/**
 * RoleService.java
 *
 * @author Nguyen
 */
public interface RoleService {
    Role createRole(String roleName);
}
