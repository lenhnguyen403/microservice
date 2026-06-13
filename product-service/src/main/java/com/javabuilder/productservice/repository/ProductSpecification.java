/*
 * ProductSpecification.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.productservice.repository;

import com.javabuilder.productservice.entity.Product;
import com.javabuilder.productservice.enums.ProductStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * ProductSpecification.java
 *
 * @author Nguyen
 */
public class ProductSpecification {
    public static Specification<Product> hasCategory(String categoryId){
        return ((root, query, criteriaBuilder) ->
                categoryId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"),categoryId));
    }

    public static Specification<Product> hasStatus(ProductStatus status) {
        return (root, query, cb) ->
                status == null ? cb.conjunction() : cb.equal(root.get("status"), status);
    }

    public static Specification<Product> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return cb.conjunction();
            if (minPrice != null && maxPrice == null) return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            if (minPrice == null) return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            return cb.between(root.get("price"), minPrice, maxPrice);
        };
    }
}
