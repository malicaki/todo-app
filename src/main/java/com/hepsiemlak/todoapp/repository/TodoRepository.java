package com.hepsiemlak.todoapp.repository;

import com.hepsiemlak.todoapp.model.Todo;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CouchbaseRepository<Todo, Long> {

    List<Todo> findTodoByCategoryId(Long categoryId);
}
