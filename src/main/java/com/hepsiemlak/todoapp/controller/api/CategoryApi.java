package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.CategoryDto;
import com.hepsiemlak.todoapp.dto.TodoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface CategoryApi {

    @Operation(summary = "Create category", description = "Creates a new category", responses = {
            @ApiResponse(responseCode = "201", description = "The newly created Category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
    })
    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto);

    @Operation(summary = "Update Category", description = "Updates an existing Category", responses = {
            @ApiResponse(responseCode = "201", description = "The updated Category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
    })
    @PatchMapping(value = APP_ROOT + "/categories/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto user);

    @Operation(summary = "Category Details", description = "Returns the list of the categories", responses = {
            @ApiResponse(responseCode = "200", description = "List of the categories", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
    })
    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategories();

    @Operation(summary = "Todo Details by category ID", description = "Returns the list of the Todo of a selected category", responses = {
            @ApiResponse(responseCode = "200", description = "List of the Todos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDto.class)))
    })
    @GetMapping(value = APP_ROOT + "/categories/todos/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(
            @PathVariable("id") Long id
    );

    @Operation(summary = "List of all categories and Todo for today", description = "Returns the list of the Todo of a selected category", responses = {
            @ApiResponse(responseCode = "200", description = "List of the Todos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDto.class)))
    })
    @GetMapping(value = APP_ROOT + "/categories/todos/today/{userId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(
            @PathVariable("userId") Long userId
    );

    @Operation(summary = "Category Details by user ID", description = "Returns the list of the categories of a selected user", responses = {
            @ApiResponse(responseCode = "200", description = "List of the categories", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
    })
    @GetMapping(value = APP_ROOT + "/categories/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategoriesByUserId(
            @PathVariable("id") Long id
    );

    @Operation(summary = "Category Details", description = "Returns the category details by ID", responses = {
            @ApiResponse(responseCode = "200", description = "The Category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping(value = APP_ROOT + "/categories/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> getCategory(
            @PathVariable("id") Long id
    );

    @Operation(summary = "Delete category", description = "Deletes a category by ID", responses = {
            @ApiResponse(responseCode = "200", description = "The category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id:.+}")
    ResponseEntity<Void> deleteCategory(
            @PathVariable("id") Long id
    );
}
