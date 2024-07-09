package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.CategoryDto;
import com.hepsiemlak.todoapp.dto.TodoDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface CategoryApi {


    @Operation(summary = "Create category", description = "Creates a new category")
    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto);


    @Operation(summary = "Update Category", description = "Updates an existing Category")
    @PatchMapping(value = APP_ROOT + "/categories/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto user);


    @Operation(summary = "Category Details", description = "Returns the list of the categories")
    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategories();


    @Operation(summary = "Todo Details by category ID", description = "Returns the list of the Todo of a selected category")
    @GetMapping(value = APP_ROOT + "/categories/todos/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(@PathVariable("id") Long id);


    @Operation(summary = "List of all categories and Todo for today", description = "Returns the list of the Todo of a selected category")
    @GetMapping(value = APP_ROOT + "/categories/todos/today/{userId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(@PathVariable("userId") Long userId);


    @Operation(summary = "Category Details by user ID", description = "Returns the list of the categories of a selected user")
    @GetMapping(value = APP_ROOT + "/categories/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategoriesByUserId(@PathVariable("id") Long id);


    @Operation(summary = "Category Details", description = "Returns the category details by ID")
    @GetMapping(value = APP_ROOT + "/categories/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id);


    @Operation(summary = "Delete category", description = "Deletes a category by ID")
    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id:.+}")
    ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id);
}
