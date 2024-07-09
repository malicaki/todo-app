package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.CategoryDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.Category;
import com.hepsiemlak.todoapp.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_validCategory_shouldReturnSavedCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Work");
        categoryDto.setDescription("Work related tasks");

        Category category = CategoryDto.toEntity(categoryDto);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto savedCategory = categoryService.save(categoryDto);

        assertNotNull(savedCategory);
        assertEquals("Work", savedCategory.getName());
        assertEquals("Work related tasks", savedCategory.getDescription());
    }

    @Test
    void save_invalidCategory_shouldThrowInvalidEntityException() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("");
        categoryDto.setDescription("");

        assertThrows(InvalidEntityException.class, () -> categoryService.save(categoryDto));
    }

    @Test
    void findAll_shouldReturnAllCategories() {
        Category category = new Category();
        category.setName("Work");

        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryDto> categories = categoryService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Work", categories.get(0).getName());
    }

    @Test
    void findById_validId_shouldReturnCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Work");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryDto categoryDto = categoryService.findById(1L);

        assertNotNull(categoryDto);
        //assertEquals(1L, categoryDto.getId());
        assertEquals("Work", categoryDto.getName());
    }

    @Test
    void findById_invalidId_shouldThrowEntityNotFoundException() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(1L));
    }

    @Test
    void delete_validId_shouldDeleteCategory() {
        categoryService.delete(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
