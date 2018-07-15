package com.hediapps.users.service;

import com.hediapps.users.domain.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    User findOneByEmail(@Param("email") String email);

}
