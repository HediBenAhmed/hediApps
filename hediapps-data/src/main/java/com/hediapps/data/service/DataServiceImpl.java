package com.hediapps.data.service;

import java.util.List;
import java.util.UUID;

import com.hediapps.data.model.DataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("dataService")
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final DataRepository dataRepository;

    @Override
    public DataEntity getById(String id) {
        return dataRepository.findById(id).get();
    }

    @Override
    public String save(DataEntity dataEntity) {
        return dataRepository.save(dataEntity).getId();
    }

    @Override
    public List<DataEntity> getAll() {
        return (List<DataEntity>) dataRepository.findAll();
    }
}
