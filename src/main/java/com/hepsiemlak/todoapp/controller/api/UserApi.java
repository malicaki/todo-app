package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface UserApi {


    @Operation(summary = "Create user", description = "Creates a new user")
    @PostMapping(value = APP_ROOT + "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> createUser(@RequestBody UserDto user);


    @Operation(summary = "Update user", description = "Updates an existing user")
    @PatchMapping(value = APP_ROOT + "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user);


    @Operation(summary = "User Details", description = "Returns the list of the users")
    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> getAllUsers();


    @Operation(summary = "User Details", description = "Returns the user by ID")
    @GetMapping(value = APP_ROOT + "/users/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(@PathVariable("id") Long id);


    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    @DeleteMapping(value = APP_ROOT + "/users/delete/{id:.+}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);
}
