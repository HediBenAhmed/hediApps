package com.hediapps.data.endpoint;

import java.util.List;
import java.util.UUID;

import com.hediapps.data.model.DataEntity;
import com.hediapps.data.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.util.UriComponentsBuilder.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final DataService dataService;

    @GetMapping("/{id}")
    public DataEntity getById(@PathVariable("id") String id) {
        return dataService.getById(id);
    }

    @PostMapping
    public ResponseEntity saveData(@RequestBody DataEntity data) {

        String id = dataService.save(data);
        return created(fromUriString("/").pathSegment(id).build().toUri()).build();
    }

    @GetMapping
    public List<DataEntity> getAll() {
        return dataService.getAll();
    }

}
