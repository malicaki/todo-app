package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.TodoDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface TodoApi {


    @Operation(summary = "Create To do", description = "Creates a new to do")
    @PostMapping(value = APP_ROOT + "/todos/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto);


    @Operation(summary = "Update Todo", description = "Updates an existing Todo")
    @PatchMapping(value = APP_ROOT + "/todos/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto user);


    @Operation(summary = "Todo Details", description = "Returns the list of the Todos")
    @GetMapping(value = APP_ROOT + "/todos/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodos();


    @Operation(summary = "Todo Details", description = "Returns the Todo by ID")
    @GetMapping(value = APP_ROOT + "/todos/{todoId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> getTodo(@PathVariable("todoId") Long todoId);


    @Operation(summary = "Delete Todo", description = "Deletes a Todo by ID")
    @DeleteMapping(value = APP_ROOT + "/todos/delete/{id:.+}")
    ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id);
}
