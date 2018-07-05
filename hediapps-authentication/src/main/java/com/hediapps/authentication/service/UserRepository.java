package com.hediapps.authentication.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hediapps.authentication.domain.model.User;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	User findOneByEmail(@Param("email") String email);

}
