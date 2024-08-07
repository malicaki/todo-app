package com.hepsiemlak.todoapp.controller;

import com.hepsiemlak.todoapp.controller.api.TodoApi;
import com.hepsiemlak.todoapp.dto.TodoDto;
import com.hepsiemlak.todoapp.service.abs.CategoryService;
import com.hepsiemlak.todoapp.service.abs.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoController implements TodoApi {

    @Autowired
    private TodoService todoService;

    @Override
    public ResponseEntity<TodoDto> createTodo(TodoDto todoDto) {
        return new ResponseEntity<>(todoService.save(todoDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TodoDto> updateTodo(TodoDto todoDto) {
        return new ResponseEntity<>(todoService.save(todoDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoDto> getTodo(Long todoId) {
        return  new ResponseEntity<>(todoService.findById(todoId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteTodo(Long id) {
        return null;
    }
}