package com.hediapps.data.service;

import java.util.List;
import java.util.UUID;

import com.hediapps.data.model.DataEntity;

public interface DataService {
    DataEntity getById(String id);
    String save(DataEntity dataEntity);
    List<DataEntity> getAll();
}
