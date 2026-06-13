/*
 * ProductRepository.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.productservice.repository;

import com.javabuilder.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProductRepository.java
 *
 * @author Nguyen
 */
public interface ProductRepository extends JpaRepository<Product,String>,
        JpaSpecificationExecutor<Product> {

}
