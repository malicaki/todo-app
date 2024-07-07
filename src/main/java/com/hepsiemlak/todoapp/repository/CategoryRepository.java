package com.hepsiemlak.todoapp.repository;

import com.hepsiemlak.todoapp.model.Category;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface CategoryRepository extends CouchbaseRepository<Category, Long> {

    List<Category> findCategoryByUserId(Long userId);

    @Query("select t.category.id from Todo t where t.id = :todoId")
    Long findCategoryByTodoId(Long todoId);

    @Query("select c from Category c inner join Todo t on t.category.id = c.id where t.startDate >= :startDate and t.startDate <= :endDate and c.user.id = :userId")
    List<Category> getAllTodoByCategoriesForToday(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate, @Param("userId")Long userId);}
