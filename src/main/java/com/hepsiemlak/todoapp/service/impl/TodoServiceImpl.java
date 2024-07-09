package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.CategoryDto;
import com.hepsiemlak.todoapp.dto.TodoDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.ErrorCodes;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.Category;
import com.hepsiemlak.todoapp.model.Todo;
import com.hepsiemlak.todoapp.repository.CategoryRepository;
import com.hepsiemlak.todoapp.repository.TodoRepository;
import com.hepsiemlak.todoapp.service.abs.TodoService;
import com.hepsiemlak.todoapp.validator.TodoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public TodoDto save(TodoDto todoDto) {
        List<String> errors = TodoValidator.validateTodo(todoDto);
        if (!errors.isEmpty()) {
            log.error("Todo is not valid {}", todoDto);
            throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }
        return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        if (id == null) {
            log.error("Todo id is null");
            return null;
        }
        final Category category = categoryRepository.findCategoryByTodoId(id);

        final Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(value -> value.setCategoryId(category.getId()));

        final TodoDto todoDto = TodoDto.fromEntity(todo.get());
        todoDto.setCategoryId(category.getId());

        return Optional.of(todoDto).
                orElseThrow(() -> new EntityNotFoundException("No Todo found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<TodoDto> findByCategory(Long categoryId) {
        return todoRepository.findTodoByCategoryId(categoryId).stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Todo id is null");
            return;
        }
        todoRepository.deleteById(id);
    }
}
