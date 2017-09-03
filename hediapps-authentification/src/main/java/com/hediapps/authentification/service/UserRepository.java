package com.hediapps.authentification.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hediapps.domain.model.User;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	@Override
	@PreAuthorize("#oauth2.hasScope('write')")
	public <S extends User> S save(S entity);

	User findOneByEmail(@Param("email") String email);

}
