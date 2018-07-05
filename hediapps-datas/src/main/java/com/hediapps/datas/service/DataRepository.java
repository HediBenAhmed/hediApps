package com.hediapps.datas.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hediapps.datas.domain.model.Data;

@RepositoryRestResource
@PreAuthorize("#oauth2.hasScope('openid')")
public interface DataRepository extends PagingAndSortingRepository<Data, String> {
}
