package com.hepsiemlak.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;


import java.io.Serializable;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category{

    @Id
    @GeneratedValue
    private Long id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private Long userId;

    @Field
    private List<Todo> todoList;
}