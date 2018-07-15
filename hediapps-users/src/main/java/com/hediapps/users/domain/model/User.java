package com.hediapps.users.domain.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String password;
    private String email;

    private Set<Role> authorities = new HashSet<Role>();

    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
}
