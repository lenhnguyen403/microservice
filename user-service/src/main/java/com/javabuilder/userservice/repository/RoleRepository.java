/*
 * RoleRepository.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.repository;

import com.javabuilder.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepository.java
 *
 * @author Nguyen
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String roleName);
}
