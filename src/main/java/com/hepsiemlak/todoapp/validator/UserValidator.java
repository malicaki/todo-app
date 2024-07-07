package com.hepsiemlak.todoapp.validator;

import com.hepsiemlak.todoapp.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validateUser(UserDto user) {
        List<String> errors = new ArrayList<>();
        if (user == null) {
            errors.add("Please fill the First name");
            errors.add("Please fill the Last name");
            errors.add("Please fill the User name");
            errors.add("Please fill the user Email");
            errors.add("Please fill the User Password");
            return errors;
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            errors.add("Please fill the First name");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            errors.add("Please fill the Last name");
        }
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            errors.add("Please fill the User name");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.add("Please fill the user Email");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errors.add("Please fill the User Password");
        }

        return errors;
    }

    public static List<String> validateUserCredentials(String email, String password) {
        List<String> errors = new ArrayList<>();
        if (email == null || email.isEmpty()) {
            errors.add("Email is empty");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Password is empty");
        }
        return errors;
    }
}
