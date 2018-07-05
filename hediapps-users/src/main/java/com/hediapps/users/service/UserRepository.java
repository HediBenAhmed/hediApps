package com.hediapps.users.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hediapps.users.domain.model.User;

@RepositoryRestResource
@PreAuthorize("#oauth2.hasScope('openid')")
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	User findOneByEmail(@Param("email") String email);

}
