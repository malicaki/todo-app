package com.hepsiemlak.todoapp.dto;

import com.hepsiemlak.todoapp.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private Long id;

    private String title;

    private String description;

    private ZonedDateTime startDate;

    private boolean done;

    private boolean favorite;

    private Long categoryId;

    public static Todo toEntity(TodoDto todoDto) {
        if (todoDto == null) {
            return null;
        }

        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.isDone());
        todo.setFavorite(todoDto.isFavorite());
        todo.setStartDate(todoDto.getStartDate() != null ? todoDto.getStartDate() : ZonedDateTime.now());
        todo.setCategoryId(todoDto.getCategoryId());

        return todo;
    }


    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .done(todo.isDone())
                .favorite(todo.isFavorite())
                .build();
    }
}
