package com.hepsiemlak.todoapp.repository;

import com.hepsiemlak.todoapp.model.Category;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface CategoryRepository extends CouchbaseRepository<Category, Long> {

    List<Category> findCategoryByUserId(Long userId);

    @Query("#{#n1ql.selectEntity} WHERE ANY t IN todoList SATISFIES t.id = $todoId END AND #{#n1ql.filter}")
    Category findCategoryByTodoId(@Param("todoId") Long todoId);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ANY t IN todoList SATISFIES t.startDate >= $startDate AND t.startDate <= $endDate END AND user.id = $userId")
    List<Category> getAllTodoByCategoriesForToday(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate, @Param("userId") Long userId);
}
