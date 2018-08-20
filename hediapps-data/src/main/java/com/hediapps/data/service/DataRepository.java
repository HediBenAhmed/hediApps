package com.hediapps.data.service;

import com.hediapps.data.model.DataEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataRepository extends PagingAndSortingRepository<DataEntity, String> {
}
