package com.hediapps.authentication.service;

import com.hediapps.authentication.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
