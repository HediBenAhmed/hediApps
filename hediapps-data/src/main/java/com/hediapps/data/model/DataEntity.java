package com.hediapps.data.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "data")
public class Data {
    public enum DataType {
    }

    @Id
    private String id;

    private String name;
    private DataType dataType;

    private Map<String, Long> values;
}
