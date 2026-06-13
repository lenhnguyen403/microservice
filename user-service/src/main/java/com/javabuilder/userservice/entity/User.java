/*
 * User.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.javabuilder.userservice.entity;

import com.javabuilder.userservice.enums.Gender;
import com.javabuilder.userservice.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User.java
 *
 * @author Nguyen
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String email;

    String password;

    String firstName;

    String lastName;

    String phone;

    String avatar;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "birth_date")
    LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    UserStatus userStatus = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @Builder.Default
    Set<UserHasRole> userHasRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    Set<Address> addresses = new HashSet<>();

    public void addRole(Role role){
        this.userHasRoles.add(
                UserHasRole.builder()
                        .user(this)
                        .role(role)
                        .build()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = this.userHasRoles.stream()
                .map(UserHasRole::getRole).toList();

        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userStatus == UserStatus.ACTIVE;
    }
}
