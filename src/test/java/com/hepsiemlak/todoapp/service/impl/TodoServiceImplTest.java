package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.TodoDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.Category;
import com.hepsiemlak.todoapp.model.Todo;
import com.hepsiemlak.todoapp.repository.CategoryRepository;
import com.hepsiemlak.todoapp.repository.TodoRepository;
import com.hepsiemlak.todoapp.service.abs.TodoService;
import com.hepsiemlak.todoapp.validator.TodoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save_invalidTodo_shouldThrowInvalidEntityException() {
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle("");
        todoDto.setDescription("");

        assertThrows(InvalidEntityException.class, () -> todoService.save(todoDto));
    }

    @Test
    void findAll_shouldReturnAllTodos() {
        Todo todo = new Todo();
        todo.setTitle("Complete project");

        when(todoRepository.findAll()).thenReturn(List.of(todo));

        List<TodoDto> todos = todoService.findAll();

        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals("Complete project", todos.get(0).getTitle());
    }

    @Test
    void findById_validId_shouldReturnTodo() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Complete project");

        List<Todo> todos = new ArrayList<>();
        todos.add(todo);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        when(categoryRepository.findCategoryByTodoId(1L)).thenReturn(new Category(10L,"test","test",1L,todos));

        TodoDto todoDto = todoService.findById(1L);

        assertNotNull(todoDto);
        //assertEquals(1L, todoDto.getId());
        assertEquals("Complete project", todoDto.getTitle());
        assertEquals(10L, todoDto.getCategoryId());
    }

    @Test
    void delete_validId_shouldDeleteTodo() {
        todoService.delete(1L);
        verify(todoRepository, times(1)).deleteById(1L);
    }
}
