package com.hediapps.authentication.service;

import com.hediapps.authentication.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    void save(UserDto user);
}
