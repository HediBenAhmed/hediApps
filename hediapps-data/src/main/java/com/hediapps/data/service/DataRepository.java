package com.hediapps.data.service;

import com.hediapps.data.domain.model.Data;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DataRepository extends PagingAndSortingRepository<Data, String> {
}
