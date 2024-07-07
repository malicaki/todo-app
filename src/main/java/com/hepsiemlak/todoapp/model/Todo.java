package com.hepsiemlak.todoapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.time.ZonedDateTime;

@Getter
@Setter
@Document
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private ZonedDateTime startDate;

    @Field
    private boolean done;

    @Field
    private boolean favorite;

    @Field
    private Category category;
}
