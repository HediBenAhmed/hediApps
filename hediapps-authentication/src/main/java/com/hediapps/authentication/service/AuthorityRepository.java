package com.hediapps.authentication.service;

import com.hediapps.authentication.model.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    AuthorityEntity findByAuthority(String authority);
}
