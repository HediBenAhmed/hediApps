package com.hediapps.authentication.endpoint;

import java.security.Principal;

import com.hediapps.authentication.dto.UserDto;
import com.hediapps.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDto user) {
        authenticationService.save(user);
    }
}
