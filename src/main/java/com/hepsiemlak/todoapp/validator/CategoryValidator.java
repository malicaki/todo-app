package com.hepsiemlak.todoapp.validator;

import com.hepsiemlak.todoapp.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryValidator {

    public static List<String> validateCategory(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();
        if (categoryDto == null) {
            errors.add("Please fill the name");
            errors.add("Please fill the description");
            return  errors;
        }
        if (categoryDto.getName().isEmpty()) {
            errors.add("Please fill the name");
        }
        if (categoryDto.getDescription().isEmpty()) {
            errors.add("Please fill the description");
        }
        return errors;
    }
}
